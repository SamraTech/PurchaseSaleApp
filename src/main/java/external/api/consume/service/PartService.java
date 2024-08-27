package external.api.consume.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import external.api.consume.model.Part;
import external.api.consume.repository.OrderMethodRepository;
import external.api.consume.repository.PartRepository;
import external.api.consume.repository.UomRepository;

@Service
public class PartService {
	@Autowired
	private PartRepository repository;
	@Autowired
	private UomRepository uomRepository;
	@Autowired
	private OrderMethodRepository orderRepository;
	

	public String savePart(Part part) {
		Part save = repository.save(part);

		// TODO Auto-generated method stub
		return "part saved" + save.getPartId();
	}

	public Map<Integer, String> getUomIdAndModel() {
		//Map<Integer, String> map = new HashMap<Integer, String>();
		List<Object[]> uomIdAndModel = uomRepository.getUomIdAndModel();
		/*
		 * for (Object obj[] : uomIdAndModel) {
		 * map.put(Integer.valueOf(obj[0].toString()), obj[1].toString()); }
		 * System.out.println("from UOm to Part"); System.out.println(map);
		 */
		//java 8 features used here
		Map<Integer, String> map = uomIdAndModel.stream().collect(Collectors.toMap(obj->Integer.valueOf(obj[0].toString()),obj->obj[1].toString()));
		
		return map;
	}
	public Map<Integer, String> getOrderIdAndCodeSale(String mode) {
		//Map<Integer, String> map = new HashMap<Integer, String>();
		List<Object[]> uomIdAndModel = orderRepository.getIdAndCodeSale(mode);
		/*
		 * for (Object obj[] : uomIdAndModel) {
		 * map.put(Integer.valueOf(obj[0].toString()), obj[1].toString()); }
		 * System.out.println("from UOm to Part"); System.out.println(map);
		 */
		//java 8 features used here
		Map<Integer, String> map = uomIdAndModel.stream().collect(Collectors.toMap(obj->Integer.valueOf(obj[0].toString()),obj->obj[1].toString()));
		System.out.println(map);
		return map;
	}
	public Map<Integer, String> getOrderIdAndCodePurchase(String mode) {
		//Map<Integer, String> map = new HashMap<Integer, String>();
		List<Object[]> uomIdAndModel = orderRepository.getIdAndCodePurchase(mode);
		/*
		 * for (Object obj[] : uomIdAndModel) {
		 * map.put(Integer.valueOf(obj[0].toString()), obj[1].toString()); }
		 * System.out.println("from UOm to Part"); System.out.println(map);
		 */
		//java 8 features used here
		Map<Integer, String> map = uomIdAndModel.stream().collect(Collectors.toMap(obj->Integer.valueOf(obj[0].toString()),obj->obj[1].toString()));
		System.out.println(map);
		return map;
	}
	public Map<Integer, String> getOrderIdAndCode(String mode) {
		//Map<Integer, String> map = new HashMap<Integer, String>();
		List<Object[]> uomIdAndModel = orderRepository.getIdAndCode(mode);
		/*
		 * for (Object obj[] : uomIdAndModel) {
		 * map.put(Integer.valueOf(obj[0].toString()), obj[1].toString()); }
		 * System.out.println("from UOm to Part"); System.out.println(map);
		 */
		//java 8 features used here
		Map<Integer, String> map = uomIdAndModel.stream().collect(Collectors.toMap(obj->Integer.valueOf(obj[0].toString()),obj->obj[1].toString()));
		System.out.println(map);
		return map;
	}
	public Map<Integer, String> getPartIdAndCode() {
		//Map<Integer, String> map = new HashMap<Integer, String>();
		List<Object[]> partIdCode =repository.getPartIdAndCode();
		/*
		 * for (Object obj[] : uomIdAndModel) {
		 * map.put(Integer.valueOf(obj[0].toString()), obj[1].toString()); }
		 * System.out.println("from UOm to Part"); System.out.println(map);
		 */
		//java 8 features used here
		Map<Integer, String> map = partIdCode.stream().collect(Collectors.toMap(obj->Integer.valueOf(obj[0].toString()),obj->obj[1].toString()));
		System.out.println(map);
		return map;
	}

	public List<Part> getPart() {
		// TODO Auto-generated method stub
		List<Part> part = repository.findAll();
		return part;
		
	}






}
