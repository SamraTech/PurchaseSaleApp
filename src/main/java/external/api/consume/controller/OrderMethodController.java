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

import external.api.consume.model.OrderMethod;
import external.api.consume.service.OrderMethodService;

@Controller
@RequestMapping("/ordermethod")
public class OrderMethodController {
	@Autowired
	private OrderMethodService service;

	/*for rest api method ------
	 * @PostMapping("/save") ResponseEntity<String> saveOrderMethod(@RequestBody
	 * OrderMethod ordermethod) {
	 * 
	 * String saveOrderMethod = service.saveOrderMethod(ordermethod); return new
	 * ResponseEntity<String>(saveOrderMethod, HttpStatus.CREATED); }
	 */
	//for web reuest method----
	@PostMapping("/save")
	String saveOrderMethod(@ModelAttribute OrderMethod ordermethod,Model model) {

		String saveOrderMethod = service.saveOrderMethod(ordermethod);
		model.addAttribute("saveOrder",saveOrderMethod);
			return 	"OrderMethod";
	}
	@GetMapping("/")
	ResponseEntity<List<OrderMethod>> getOrderMethod() {

		List<OrderMethod> orderMethod = service.getOrderMethod();
		return new ResponseEntity<List<OrderMethod>>(orderMethod, HttpStatus.OK);
	}
	@GetMapping("/{id}")
	ResponseEntity<OrderMethod> getOrderMethodById(@PathVariable Integer id) {

		OrderMethod orderMethod = service.getOrderMethodById(id);
		return new ResponseEntity<OrderMethod>(orderMethod, HttpStatus.OK);
	}
	@DeleteMapping("/delete/{id}")
	ResponseEntity<String> deleteOrderMethodById(@PathVariable Integer id) {
    System.out.println("i am in delete method");
		String deleteOrderMethodById = service.deleteOrderMethodById(id);
		return new ResponseEntity<String>(deleteOrderMethodById, HttpStatus.OK);
	}
	
	@PutMapping("/update/{id}")
	ResponseEntity<String> updateOrderMethod(@RequestBody OrderMethod ordermethod, @PathVariable Integer id) {

		String orderMethod = service.updateOrderMethod(ordermethod,id);
		return new ResponseEntity<String>(orderMethod, HttpStatus.OK);
	}
	@GetMapping("/register")
	String showPage(){
		return "OrderMethod";
	}
}
