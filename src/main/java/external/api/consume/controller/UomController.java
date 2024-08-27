package external.api.consume.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import external.api.consume.model.UomType;
import external.api.consume.service.UomService;

@Controller
@RequestMapping("/uom")
public class UomController {
	@Autowired
	private UomService service;

	/*
	 * @PostMapping("/save") ResponseEntity<String> saveUom(@RequestBody UomType
	 * type) { System.out.println(type.getUom_Decrp() + type.getUom_Id()); String
	 * saveUom = service.saveUom(type); return new ResponseEntity<String>(saveUom,
	 * HttpStatus.CREATED); }
	 */
	@PostMapping("/save")
	String saveUom(@ModelAttribute UomType type,Model model) {
		System.out.println(type.getUom_Decrp() + type.getUom_Id());
		String saveUom = service.saveUom(type);
		
		model.addAttribute("saveUom",saveUom);
		return "register";
	}
///to call rest api ----------------------
	/*
	 * @GetMapping("/") ResponseEntity<List<UomType>> getUom() { List<UomType> uom =
	 * service.getUom(); return new ResponseEntity<List<UomType>>(uom,
	 * HttpStatus.OK); }
	 */


	@GetMapping("/all")
	public String getUom(Model model) {
		List<UomType> uom = service.getUom();
		model.addAttribute("uomlist", uom);
		return "UomDataPage";
	}
	// rest call for edit data and view -------------------
	/*
	 * @GetMapping("/{id}") ResponseEntity<UomType> getUom(@PathVariable Integer id)
	 * { UomType uomByID = service.getUomByID(id); return new
	 * ResponseEntity<UomType>(uomByID, HttpStatus.OK); }
	 */
	@GetMapping("/edit")
	public String getUom(@RequestParam Integer id,Model model) {
		UomType uomByID = service.getUomByID(id);
		model.addAttribute("uombyId",uomByID);
		return "UomEditPage";
	}

	@DeleteMapping("/delete/{id}")
	ResponseEntity<String> deleteByUomId(@PathVariable Integer id) {
		String deleteByUomId = service.deleteByUomId(id);
		return new ResponseEntity<String>(deleteByUomId, HttpStatus.OK);
	}

	@PutMapping("/update")
	ResponseEntity<String> deleteByUomId(@RequestBody UomType type) {
		String updateUom = service.saveUomUpdate(type);
		return new ResponseEntity<String>(updateUom, HttpStatus.OK);
	}
	@GetMapping("/show")
	public String showRegisterPage(){
		System.out.println(" i am in page");
		return "register";
	}
	@GetMapping("/validate")
	public @ResponseBody String validate(@RequestParam String uommodel,@RequestParam  Integer id) {
		System.out.println("in validate");
		String msg="";
		/*
		 * //String UomModelCount = service.validateUomModelCount(uommodel);
		 */	
		if(id!=0&& service.validateUomModelEditIdExits(uommodel, id)) { 
			
			System.out.println("i am in edit mode"+id);
			msg="Uommodel"+uommodel+"already exists";
		}
		else if(id==0&&service.validateUomModelCount(uommodel)) {
			System.out.println("i am in model mode"+id);
			msg="Uommodel"+uommodel+"already exists";
		}
		
		
								
			
			return msg;
		}
		
		
			
	}


