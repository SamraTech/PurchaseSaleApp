package external.api.consume.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import external.api.consume.CustomerInvoicePdf;
import external.api.consume.model.SaleOrder;
import external.api.consume.model.SaleOrderDtl;
import external.api.consume.service.PartService;
import external.api.consume.service.SaleOrderService;
import external.api.consume.service.ShipmentService;
import external.api.consume.service.WhUserTypeService;

@Controller
@RequestMapping("/saleorder")
public class SaleOrderController {
	@Autowired
	private SaleOrderService saleservice;
	@Autowired
	private WhUserTypeService userservice;
	@Autowired
	private ShipmentService shpservice;
	@Autowired
	private PartService prtservice;

	@GetMapping("/register")
	public String register(Model model) {
		SaleOrder order=new SaleOrder();
		order.setStatus("SALE-OPEN");
		model.addAttribute("saleorder",order);
		model.addAttribute("whUser",userservice.getIdAndCode());
		model.addAttribute("shipmentIdAndCode",shpservice.getIdAndCode("Yes"));
		
		return "RegisterSaleOrder";
	}

	@PostMapping("/save")
	public String saveSaleOrder(@ModelAttribute SaleOrder order, Model model) {

		SaleOrder saveSaleOrder = saleservice.saveSaleOrder(order);
		Integer id = saveSaleOrder.getId();
		String msg="Sale Order Save With id is="+id;
		model.addAttribute("saleorder",new SaleOrder());
		model.addAttribute("saleord",msg);
		
		return "RegisterSaleOrder";
	}
	@GetMapping("/all")
	public String getSaleOrder(Model model) {
		List<SaleOrder> saleorder = saleservice.getAll();
		model.addAttribute("saleorder", saleorder);
		return "SaleOrderData";
	}
	@GetMapping("/addparts")
	public String addParts(@RequestParam Integer id,Model model) {
		
	SaleOrder saleOrderById = saleservice.getSaleOrderById(id);	
	  model.addAttribute("saleorder", saleOrderById);
	  model.addAttribute("order", new SaleOrderDtl());
	  
	  model.addAttribute("prtidcode",prtservice.getPartIdAndCode());
	 // List<SaleOrderDtl> saleOrderDtl = saleservice.getSaleOrderDtl(id);
	  List<SaleOrderDtl> saleOrderDtl = saleservice.getSaleOrderDtl(id);
		
		model.addAttribute("saleOrderDtl",saleOrderDtl);
	 
		return "SaleOrderParts";	
	}
	@PostMapping("/saleorderdtl")
	
	public String saveSaleOrder(@ModelAttribute SaleOrderDtl order,Model model) {
	
		saleservice.savedtlSaleOrder(order);
		
		saleservice.updateStatus(order.getSaleorder().getId(),"SALE-READY");
		return "redirect:addparts?id="+order.getSaleorder().getId();
	}
	@GetMapping("/removepart")
	public String removePart(@RequestParam Integer id,@RequestParam Integer orid) {
		
		saleservice.deleteBySaleDtl(id);
		Integer countdtl = saleservice.countSaleDtlByOrderId(orid);
		if(countdtl==0) {
			saleservice.updateStatus(orid,"SALE-OPEN");	
		}
		
		return "redirect:addparts?id="+orid;
	}
	@GetMapping("/confirm")
	public String placeOrder(@RequestParam Integer id) {
		
		saleservice.updateStatus(id,"SALE-CONFIRM");
		return "redirect:addparts?id="+id;
	}
	
	@GetMapping("/invoice")
	public String invoice(@RequestParam Integer id) {
		
		saleservice.updateStatus(id,"SALE-INVOICE");
		return "redirect:all";
	}

	@GetMapping("/printinvoice")
	public ModelAndView printInvoice(@RequestParam Integer id) {
		ModelAndView view=new ModelAndView();
		SaleOrder saleOrderById = saleservice.getSaleOrderById(id);	
		 List<SaleOrderDtl> saleOrderDtl = saleservice.getSaleOrderDtl(id);
			
			view.addObject("saleOrderDtl",saleOrderDtl);
		//view.addAttribute("saleorder", saleOrderById);
		view.addObject("saleOrderById", saleOrderById);
		view.setView(new CustomerInvoicePdf());
		return view;
	}
	

}
