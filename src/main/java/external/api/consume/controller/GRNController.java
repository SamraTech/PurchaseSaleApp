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

import external.api.consume.model.GRN;
import external.api.consume.model.GRNDtls;
import external.api.consume.model.PurchaseOrder;
import external.api.consume.model.PurchaseOrderDtl;
import external.api.consume.service.GRNService;
import external.api.consume.service.PurchaseOrderDtlService;
import external.api.consume.service.PurchaseOrderService;

@Controller
@RequestMapping("/grn")
public class GRNController {
	@Autowired
	private PurchaseOrderService ordservice;

	@Autowired
	private GRNService grnservice;
	@Autowired
	private PurchaseOrderDtlService dtlservice;

	private void addDynamicUiComponent(Model model) {

		model.addAttribute("idcode", ordservice.getIdAndCode("INVOICE"));

	}

	@GetMapping("/register")
	public String show(Model model) {
		model.addAttribute("grn", new GRN());
		addDynamicUiComponent(model);
		return "GRNRegister";
	}

	@PostMapping("/save")
	public String saveGrn(@ModelAttribute GRN grn, Model model) {
		model.addAttribute("grn", new GRN());
		String saveGrn = grnservice.saveGrn(grn);
		model.addAttribute("savegrn", saveGrn);
		ordservice.updateStatus(grn.getOrder().getId(), "RECEIVED");
		createGrnDetails(grn);
		return "GRNRegister";
	}

//create grn 
	private void createGrnDetails(GRN grn) {
		// get pO from Grn
		// get PurchaseDtl of order id
		// create model class for GrnDtl
		Integer id = grn.getOrder().getId();
		List<PurchaseOrderDtl> dtls = dtlservice.getpurchasedtl(id);
		for (PurchaseOrderDtl dtl : dtls) {
			GRNDtls grndtl = new GRNDtls();
			grndtl.setItemcode(dtl.getPart().getPartCode());
			grndtl.setBaseCost(dtl.getPart().getPartCost());
			grndtl.setQty(dtl.getQty());
			grndtl.setItemval(grndtl.getBaseCost() * grndtl.getQty());
			grndtl.setGrn(grn);
			grnservice.saveGrnDtl(grndtl);
		}
		
		
	}
	@GetMapping("/grnlist")
	public String showGrn(Model model) {
		List<GRN> grn = grnservice.getGrn();
		model.addAttribute("grnlist", grn);
		return "GRNDataPage";
	}
	@GetMapping("/viewpart")
	public String grndtl(@RequestParam Integer id,Model model) {
		List<GRNDtls> grnDtls = grnservice.getGRNDtl(id);
		 GRN grn = grnservice.getGRnById(id);
		model.addAttribute("grnDtls", grnDtls);
		model.addAttribute("grn", grn);
		return "GRNDtlPage";
	}
	@GetMapping("/updatestatus")
	public String updateStatus(@RequestParam Integer grnid,@RequestParam Integer grndtlid,String status) {
		grnservice.updateStatus(grndtlid,status);
		
		return "redirect:viewpart?id="+grnid;
	}
	

}
