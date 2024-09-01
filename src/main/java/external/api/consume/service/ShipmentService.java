package external.api.consume.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import external.api.consume.model.Shipment;
import external.api.consume.repository.ShipmentRepo;

@Service
public class ShipmentService {
	@Autowired
	private ShipmentRepo repo;
	

	public Page<Shipment> getShipment(Pageable page) {
		return repo.findAll(page);

	}

	public Shipment getShipmentById(Integer id) {
		Optional<Shipment> shipment = repo.findById(id);
		return shipment.get();
		// TODO Auto-generated method stub

	}

	public Integer saveShipment(Shipment ship) {
		Shipment save = repo.save(ship);
		return save.getShpmnt_Id();
		// TODO Auto-generated method stub

	}

	public String deleteShipmentById(Integer id) {
		String msg = "";
		// TODO Auto-generated method stub
		if (id.equals(repo.getById(id).getShpmnt_Id())) {
			repo.deleteById(id);
			msg = "Record deleted Successfully";
			return msg;
		} else {
			msg = "id does not exit";
			return msg;
		}

	}

	public String updateShipment(Shipment ship) {
		// TODO Auto-generated method stub
		String msg = "";
		// TODO Auto-generated method stub
			   Shipment save = repo.save(ship);
			   if (save.getShpmnt_Id()==ship.getShpmnt_Id()) {
				   msg = "Record updated Successfully";
					return msg;
			}
			
			   else {
				   msg = "Record not found";
					return msg;
			}
		
		}	
	public Map<Integer,String> getIdAndCode(String enabled) {
		//Map<Integer,String> map=new HashMap<Integer, String>();
		List<Object[]> idAndShipmentCode = repo.getIdAndShipmentCode(enabled);
		Map<Integer, String> mp = idAndShipmentCode.stream().collect(Collectors.toMap(obj->Integer.valueOf(obj[0].toString()), obj->obj[1].toString()));
		System.out.println(mp);
		
		return mp;
	
	}
	
	
	
	
	/*
	 * public List<Shipment> getShipment(Pageable page) { // TODO Auto-generated
	 * method stub return null; }
	 */
		
	}
