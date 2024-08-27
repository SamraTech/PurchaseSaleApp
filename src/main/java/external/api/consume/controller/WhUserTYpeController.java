package external.api.consume.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import external.api.consume.model.WhUserType;
import external.api.consume.service.WhUserTypeService;

@Controller
@RequestMapping("/user")
public class WhUserTYpeController {

	@Autowired
	private WhUserTypeService service;
//----this method for api calling
	//@PostMapping("/save")
	/*
	 * public ResponseEntity<String> addWhUser(@RequestBody WhUserType user) {
	 * System.out.println("i am in controller"); String saveWhUser =
	 * service.saveWhUser(user);
	 * 
	 * return new ResponseEntity<String>(saveWhUser, HttpStatus.CREATED);
	 * 
	 * }
	 */
	@PostMapping("/save")
	public String addWhUser(@ModelAttribute WhUserType user,Model model) {
		System.out.println("i am in controller");
		String saveWhUser = service.saveWhUser(user);
		model.addAttribute("saveUser", saveWhUser);
		return "WHUserType";

	}
//get list of apli entry using rest call-------
	/*
	 * @GetMapping("/") public ResponseEntity<List<WhUserType>> getWhUsrType() {
	 * List<WhUserType> whUserTypelist = service.getWhUserType(); return new
	 * ResponseEntity<List<WhUserType>>(whUserTypelist, HttpStatus.FOUND); }
	 */
	@GetMapping("/all")
	public String getWhUsrType(Model model) {
		List<WhUserType> whUserTypelist = service.getWhUserType();
		model.addAttribute("whUserTypelist", whUserTypelist);
		return "WHUserData";
	}
///for api calling method this is------
	/*
	 * @GetMapping("/WhUserById/{id}") public ResponseEntity<WhUserType>
	 * getWhUsrType(@PathVariable("id") Integer id) { WhUserType whUserTypeById =
	 * service.getWhUserTypeById(id); return new
	 * ResponseEntity<WhUserType>(whUserTypeById, HttpStatus.FOUND); }
	 */
	@GetMapping("/edit")
	public String getWhUsrType(@RequestParam("id") Integer id,Model model) {
		System.out.println(" i am in edit mode");
		WhUserType whUserTypeById = service.getWhUserTypeById(id);
		System.out.println(whUserTypeById.getUsr_Code());
		model.addAttribute("whUserTypeById", whUserTypeById);
		
		return "UdateWHData";
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<String> addWhUser(@RequestBody WhUserType user, @PathVariable Integer id) {
		System.out.println("i am in controller");

		String saveWhUser = service.saveWhUser(user);
		String updateWhUserypeById = service.updateWhUserypeById(user, id);

		return new ResponseEntity<String>(updateWhUserypeById, HttpStatus.OK);

	}
	//api call for delete methods-----------
	/*
	 * @DeleteMapping("/delete/{id}") public ResponseEntity<String>
	 * deleteByWhUserId(@PathVariable("id") Integer id) { String deleteByWhUserId =
	 * service.deleteByWhUserId(id); return new
	 * ResponseEntity<String>(deleteByWhUserId, HttpStatus.OK); }
	 */
	
	@RequestMapping("/delete")
	public String deleteByWhUserId(@RequestParam Integer id,Model model) {
		System.out.println("i am in delete now"+id);
		String deleteByWhUserId = service.deleteByWhUserId(id);
		model.addAttribute("deleteByWhUserId", deleteByWhUserId);
		model.addAttribute("whUserTypelist", service.getWhUserType());
		return "WHUserData";
	}
	@GetMapping("/register")
	public String showPage(){
		System.out.println("mistak in page");
		return "WHUserType";
	}

}
 