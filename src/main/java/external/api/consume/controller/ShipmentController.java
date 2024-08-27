package external.api.consume.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import external.api.consume.model.Shipment;
import external.api.consume.service.ShipmentService;

@Controller
@RequestMapping("/ship")
//@CrossOrigin(origins="http://localhost:4200")
public class ShipmentController {
	@Autowired
	private ShipmentService ser;
////rest api Shipment Call--------------------
	/*
	 * @GetMapping("/") public ResponseEntity<List<Shipment>> getShipment(Model
	 * model) {
	 * 
	 * List<Shipment> shipment = ser.getShipment();
	 * System.out.println(shipment.get(0)); model.addAttribute("shipment",
	 * shipment); return new ResponseEntity<List<Shipment>>(shipment,
	 * HttpStatus.OK);
	 * 
	 * }
	 */
	//web call all Shipment--------------------
	@GetMapping("/all")
	public String getShipment(Model model) {

		List<Shipment> shipment = ser.getShipment();
		System.out.println(shipment.get(0));
		model.addAttribute("shipment", shipment);
		return "ShipmentDataPage";

	}

	@GetMapping("/{id}")
	public ResponseEntity<Shipment> getShipmentById(@PathVariable Integer id) {
		Shipment shipment = ser.getShipmentById(id);
		return new ResponseEntity<Shipment>(shipment, HttpStatus.OK);

	}
//For Rest Api Calling Method
	/*
	 * @PostMapping("/save") public ResponseEntity<String> saveShipment(@RequestBody
	 * Shipment ship) { Integer shipmentId = ser.saveShipment(ship);
	 * System.out.println(" angular call controller"); return new
	 * ResponseEntity<String>("Data Save Successfully",HttpStatus.CREATED); }
	 */
	//for web page caling method
	@PostMapping("/save")
	public String saveShipment(@ModelAttribute Shipment ship,Model model) {
		Integer shipmentId = ser.saveShipment(ship);
		model.addAttribute("shipment","shipment id is save="+shipmentId);
		System.out.println("angular call controller");
		return "RegisterShipment";
	}

	@GetMapping("/delete/{id}")
	public ResponseEntity<String> deleteShipmentById(@PathVariable Integer id) {
		String deleteShipmentById = ser.deleteShipmentById(id);
		return new ResponseEntity<String>(deleteShipmentById, HttpStatus.OK);
	}
	@PutMapping("/update")
	public ResponseEntity<String> updateShipment(@RequestBody Shipment ship) {
		String updateShipment = ser.updateShipment(ship);
		return new ResponseEntity<String>(updateShipment, HttpStatus.OK);
	}
	@GetMapping("/register")
	public String showPage(){
		return "RegisterShipment";
	}
	
	
}
