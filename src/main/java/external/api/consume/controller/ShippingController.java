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

import external.api.consume.model.SaleOrderDtl;
import external.api.consume.model.Shipping;
import external.api.consume.model.ShippingDtls;
import external.api.consume.service.SaleOrderService;
import external.api.consume.service.ShippingService;

@Controller
@RequestMapping("/shipping")
public class ShippingController {
	@Autowired
	private SaleOrderService ordservice;
	@Autowired
	private ShippingService shpservice;
	//private List<ShippingDtls> List<ShippingDtls> shipDtl;

	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("idcode", ordservice.getIdAndCode("SALE-INVOICE"));
		model.addAttribute("ship", new Shipping());

		return "RegisterShipping";
	}

	@PostMapping("/save")
	public String saveShipping(@ModelAttribute Shipping ship, Model model) {
		model.addAttribute("idcode", ordservice.getIdAndCode("SALE-INVOICE"));
		model.addAttribute("ship", new Shipping());
		shpservice.saveShipping(ship);
		ordservice.updateStatus(ship.getOrder().getId(), "SALE-SHIPPED");
		createShippingDtl(ship);
		return "RegisterShipping";
	}

	private void createShippingDtl(Shipping ship) {

		Integer id = ship.getOrder().getId();

		List<SaleOrderDtl> saleOrderDtl = ordservice.getSaleOrderDtl(id);

		for (SaleOrderDtl dtl : saleOrderDtl) {
			ShippingDtls dtls = new ShippingDtls();
			dtls.setItemCode(dtl.getPart().getPartCode());
			dtls.setBaseCost(dtl.getPart().getPartCost());
			dtls.setQty(dtl.getQty());
			dtls.setItemvalue(dtl.getPart().getPartCost() * dtl.getQty());
			dtls.setShip(ship);
			shpservice.saveShipDtl(dtls);
		}
		

	}
	@GetMapping("/shiplist")
	public String getShipping(Model model) {
		List<Shipping> shipping = shpservice.getShipping();
		model.addAttribute("shiplist",shipping);
		return "ShippingData";
	}
	@GetMapping("/viewpart")
	public String getshipDtl(@RequestParam Integer id,Model model) {
		List<ShippingDtls> shipDtl = shpservice.getShipDtl(id);
		Shipping shippingById = shpservice.getShippingById(id);
		model.addAttribute("shipdtl", shipDtl);
		model.addAttribute("shipById", shippingById);
		return "ShippingDtlData";
	}
	@GetMapping("/updatestatus")
	public String updateStatus(@RequestParam Integer dtlid,@RequestParam Integer shipid,@RequestParam String status) {
		shpservice.updateStatus(dtlid,status);
		
		return "redirect:viewpart?id="+shipid;
		
	}
	
	
}
