package external.api.consume.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import external.api.consume.VendorInvoicePdfView;
import external.api.consume.model.PurchaseOrder;
import external.api.consume.model.PurchaseOrderDtl;
import external.api.consume.service.PartService;
import external.api.consume.service.PurchaseOrderDtlService;
import external.api.consume.service.PurchaseOrderService;
import external.api.consume.service.ShipmentService;
import external.api.consume.service.WhUserTypeService;

@Controller
@RequestMapping("/order")
public class PurchaseOrderController {
	@Autowired
	private PurchaseOrderService service;
	@Autowired
	private ShipmentService shipservice;
	@Autowired
	private PartService partService;
	@Autowired
	private PurchaseOrderDtlService purchasedtlservice;
	@Autowired
	private WhUserTypeService userService;

	@GetMapping("/register")
	public String showRegisterPage(Model model) {
		model.addAttribute("shipmentIdAndCode", shipservice.getIdAndCode("Yes"));
		model.addAttribute("whUser",userService.getIdAndCode());

		PurchaseOrder order = new PurchaseOrder();
		order.setStatus("OPEN");
		model.addAttribute("order", order);
		return "PurchaseOrder";
	}

	@PostMapping("/save")
	public String savePurchaseOrder(@ModelAttribute("order") PurchaseOrder order, Model model) {
		Integer PurchaseOrderId = service.savePurchaseOrder(order);
		model.addAttribute("save with id is=", PurchaseOrderId);
		return "PurchaseOrder";
	}

	@GetMapping("/all")
	public String getPurchaseOrder(Model model) {
		List<PurchaseOrder> purchaseOrder = service.getPurchaseOrder();
		//System.out.println(purchaseOrder.get(0).getUser().getUsr_Code());
		model.addAttribute("purchaseOrder", purchaseOrder);
		return "PurchaseOrderDataPage";
	}

	private void addDynamicUiComponents(Model model) {
		model.addAttribute("parts", partService.getPartIdAndCode());
		model.addAttribute("purchasedtl", new PurchaseOrderDtl());

	}
	/// write on private method to add part
	// dynamically and pass to other method/////

	@GetMapping("/parts")
	public String getPurchaseOrderDataPage(@RequestParam Integer id, Model model) {
		PurchaseOrder purchaseOrder = service.getPurchaseOrderBId(id);
		model.addAttribute("purchaseOrder", purchaseOrder);
		addDynamicUiComponents(model);
		// Map<Integer, String> partIdAndCode = partService.getPartIdAndCode();
		// System.out.println(partIdAndCode);
		List<PurchaseOrderDtl> purchasedtl = purchasedtlservice.getpurchasedtl(id);
		model.addAttribute("pchasedtl", purchasedtl);

		return "PurchaseOrderParts";
	}

	@PostMapping("/add")
	public String addPurchaseDtl(@ModelAttribute PurchaseOrderDtl dtl, Model model) {
		System.out.println("in Add link");
		String savePurchaseOrderDtl = purchasedtlservice.savePurchaseOrderDtl(dtl);
		model.addAttribute("purchasedtl", new PurchaseOrderDtl());
		model.addAttribute("purchasedtlsave", savePurchaseOrderDtl);
		service.updateStatus(dtl.getOrder().getId(),"PICKING");
		// List<Object[]> partQty =
		// purchasedtlservice.getPartQty(dtl.getOrder().getId());

		// System.out.println(dtl.getOrder().getId());

		return "redirect:parts?id=" + dtl.getOrder().getId();

	}
	@GetMapping("/removepart")
	public String removePart(@RequestParam Integer id,@RequestParam Integer orderid) {
		System.out.println("in remove method controller");
		purchasedtlservice.removePart(id);
		Integer countPurDtlId = purchasedtlservice.countPurDtlId(orderid);
		if(countPurDtlId==0) {
			System.out.println("in Count for dtl");
			service.updateStatus(orderid,"OPEN");	
		}
		return "redirect:parts?id="+orderid;
	}
	@GetMapping("/confirmorder")
	public String placeOrder(@RequestParam Integer orderid) {
		
		service.updateStatus(orderid,"ORDERED");
		return "redirect:parts?id="+orderid;
	}
	@GetMapping("/generateInvoice")
	public String generateInvoice(@RequestParam Integer id) {
		service.updateStatus(id,"INVOICE");
		return "redirect:all";
		
	}
	@GetMapping("/printInvoice")
	public ModelAndView showInvoice(@RequestParam Integer id) {
		ModelAndView m=new ModelAndView();
		m.addObject("purchaseDetails",purchasedtlservice.getpurchasedtl(id));
		m.addObject("purchaseOrder",service.getPurchaseOrderBId(id));
		m.setView(new VendorInvoicePdfView());
		
		
		return m;
		
	}
	

}
