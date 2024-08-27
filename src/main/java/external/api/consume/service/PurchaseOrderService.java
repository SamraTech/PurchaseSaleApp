package external.api.consume.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import external.api.consume.model.PurchaseOrder;
import external.api.consume.repository.PurchaseOrderRepository;

@Service
public class PurchaseOrderService {
	@Autowired
	private PurchaseOrderRepository repository;

	public Integer savePurchaseOrder(PurchaseOrder order) {
		// TODO Auto-generated method stub
		PurchaseOrder save = repository.save(order);

		return save.getId();

	}

	public List<PurchaseOrder> getPurchaseOrder() {
		// TODO Auto-generated method stub
		List<PurchaseOrder> purchaseOrder = repository.findAll();
		return purchaseOrder;
	}

	public PurchaseOrder getPurchaseOrderBId(Integer id) {
		// TODO Auto-generated method stub
		Optional<PurchaseOrder> purchaseOrder = repository.findById(id);
		return purchaseOrder.get();
	}
	@Transactional
	public void updateStatus(Integer orderid,String status) {
		repository.updateStatus(status, orderid);
	}
	public Map<Integer,String> getIdAndCode(String status){
		List<Object[]> idAndCode = repository.getIdAndCode(status);
	
		Map<Integer, String> idcode = idAndCode.stream().collect(Collectors.toMap(obj->Integer.valueOf(obj[0].toString()), obj->obj[1].toString()));
		System.out.println(idcode);
		return idcode;
}
}