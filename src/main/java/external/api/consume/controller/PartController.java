package external.api.consume.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import external.api.consume.model.Part;
import external.api.consume.service.PartService;

@Controller
@RequestMapping("/part")
public class PartController {
	@Autowired
	private PartService service;

	@GetMapping("/register")
	public String showReg(Model model) {
		model.addAttribute("part", new Part());
		model.addAttribute("uommodel", service.getUomIdAndModel());
		/*
		 * model.addAttribute("ordermethodsale",service.getOrderIdAndCodeSale("Sale"));
		 * model.addAttribute("ordermethodpurchase",service.getOrderIdAndCodePurchase(
		 * "Purchase"));
		 *///model.addAttribute("ordermethodsale",service.getOrderIdAndCode("Sale"));
		
		model.addAttribute("ordermethodpurchase",service.getOrderIdAndCode("Purchase"));
		
		
		return "PartRegister";
	}

	@PostMapping("/save")
	public String savePart(@ModelAttribute ("part") Part part, Model model) {
		String savePart = service.savePart(part);

		model.addAttribute("part", new Part());
		model.addAttribute("savePart", savePart);
		model.addAttribute("uommodel", service.getUomIdAndModel());
		/*
		 * model.addAttribute("ordermethodsale",service.getOrderIdAndCodeSale("Sale"));
		 * model.addAttribute("ordermethodpurchase",service.getOrderIdAndCodePurchase(
		 * "Purchase"));
		 */
		model.addAttribute("ordermethodsale",service.getOrderIdAndCode("Sale"));
		
		model.addAttribute("ordermethodpurchase",service.getOrderIdAndCode("Purchase"));
		

		return "PartRegister";
	}
	@GetMapping("/all")
	public String getPart(Model model) {
		List<Part> part = service.getPart();
		model.addAttribute("part",part);
		return "PartDataPage";
	}

}
