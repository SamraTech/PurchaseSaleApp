package external.api.consume.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import external.api.consume.model.OrderMethod;
import external.api.consume.repository.OrderMethodRepository;
//import external.api.consume.util.ListToMapUtil;

@Service
public class OrderMethodService {
	@Autowired
	private OrderMethodRepository repository;
	Map<Integer, String> map = new HashMap<Integer, String>();

	public String saveOrderMethod(OrderMethod ordermethod) {

		OrderMethod save = repository.save(ordermethod);
		return "Order method save" + save.getOrder_method_Id();
	}

	public List<OrderMethod> getOrderMethod() {

		List<OrderMethod> orderMethod = repository.findAll();
		// TODO Auto-generated method stub
		return orderMethod;
	}

	public OrderMethod getOrderMethodById(Integer id) {
		// TODO Auto-generated method stub
		Optional<OrderMethod> orderMethod = repository.findById(id);

		return orderMethod.get();
	}

	public String deleteOrderMethodById(Integer id) {
		// TODO Auto-generated method stub
		String msg = "null";
		if (repository.findById(id).get().getOrder_method_Id().equals(id)) {
			System.out.println(" i am in if block");

			repository.deleteById(id);
			msg = "deleted=" + id;
		} else {
			msg = "id not found=" + id;
			System.out.println(" i am in else block");
		}

		return msg;
	}

	public String updateOrderMethod(OrderMethod ordermethod, Integer id) {
		String msg = "null";
		if (repository.findById(id).get().getOrder_method_Id().equals(id)) {
			System.out.println(" i am in if block");

			OrderMethod save = repository.save(ordermethod);
			msg = "updated" + save.getOrder_method_Id();
		} else {
			msg = "cannot update id not found=" + id;
			System.out.println(" i am in else block");
		}

		return msg;
	}

	public Map<Integer, String>  getIdAndCodeSale(String mode) {
		System.out.println("in Service");
		List<Object[]> idAndCode = repository.getIdAndCodeSale(mode);
		
		 for(Object obj[]:idAndCode) {
		 map.put(Integer.valueOf(obj[0].toString()),obj[1].toString()); }
		/*
		 * System.out.println(map); Map<Integer, String> convertListToMap =
		 * ListToMapUtil.convertListToMap(idAndCode); Map<Integer, String> map8 =
		 * idAndCode.stream() .collect(Collectors.toMap(obj ->
		 * Integer.valueOf(obj.toString()), obj -> obj.toString()));
		 * System.out.println(convertListToMap);
		 */
	//	System.out.println(map8);
		////dont write return type if calling Util class
		return map;
	}
	public Map<Integer, String>  getIdAndCodePurchase(String mode) {
		System.out.println("in Service");
		List<Object[]> idAndCode = repository.getIdAndCodePurchase(mode);
		
		 for(Object obj[]:idAndCode) {
		 map.put(Integer.valueOf(obj[0].toString()),obj[1].toString()); }
		/*
		 * System.out.println(map); Map<Integer, String> convertListToMap =
		 * ListToMapUtil.convertListToMap(idAndCode); Map<Integer, String> map8 =
		 * idAndCode.stream() .collect(Collectors.toMap(obj ->
		 * Integer.valueOf(obj.toString()), obj -> obj.toString()));
		 * System.out.println(convertListToMap);
		 */
	//	System.out.println(map8);
		////dont write return type if calling Util class
		return map;
	}
	public Map<Integer, String>  getIdAndCode(String mode) {
		System.out.println("in Service");
		List<Object[]> idAndCode = repository.getIdAndCodePurchase(mode);
		
		 for(Object obj[]:idAndCode) {
		 map.put(Integer.valueOf(obj[0].toString()),obj[1].toString()); }
		/*
		 * System.out.println(map); Map<Integer, String> convertListToMap =
		 * ListToMapUtil.convertListToMap(idAndCode); Map<Integer, String> map8 =
		 * idAndCode.stream() .collect(Collectors.toMap(obj ->
		 * Integer.valueOf(obj.toString()), obj -> obj.toString()));
		 * System.out.println(convertListToMap);
		 */
	//	System.out.println(map8);
		////dont write return type if calling Util class
		return map;
	}


}
