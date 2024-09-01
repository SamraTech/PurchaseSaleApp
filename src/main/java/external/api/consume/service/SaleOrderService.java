package external.api.consume.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import external.api.consume.model.SaleOrder;
import external.api.consume.model.SaleOrderDtl;
import external.api.consume.model.Shipping;
import external.api.consume.repository.SaleOrderDtlRepository;
import external.api.consume.repository.SaleOrderRepository;

@Service
public class SaleOrderService {
	@Autowired
	private SaleOrderRepository repository;
	@Autowired
	private SaleOrderDtlRepository saledtlrepository;

	public SaleOrder saveSaleOrder(SaleOrder order) {
		// TODO Auto-generated method stub
		SaleOrder save = repository.save(order);

		return save;

	}

	public List<SaleOrder> getAll() {
		// TODO Auto-generated method stub
		List<SaleOrder> saleorder = repository.findAll();
		return saleorder;
	}

	public SaleOrder getSaleOrderById(Integer id) {
		// TODO Auto-generated method stub
		Optional<SaleOrder> findById = repository.findById(id);
		return findById.get();
	}

	public SaleOrderDtl savedtlSaleOrder(SaleOrderDtl order) {
		// TODO Auto-generated method stub
		SaleOrderDtl save = saledtlrepository.save(order);

		return save;

	}

	public List<SaleOrderDtl> getSaleOrderDtl(Integer orderid) {

		List<SaleOrderDtl> saleOrderDtl = saledtlrepository.getSaleOrderDtl(orderid);

		return saleOrderDtl;
	}

	@Transactional
	public void updateStatus(Integer id, String status) {
		// TODO Auto-generated method stub
		repository.updateStatus(id, status);

	}

	public void deleteBySaleDtl(Integer id) {
		
		saledtlrepository.deleteById(id);	
	}

	public Integer countSaleDtlByOrderId(Integer orid) {
		// TODO Auto-generated method stub
		Integer countdtl = saledtlrepository.countDtlBySaleId(orid);
	
	return countdtl;
	}
	public Map<Integer,String> getIdAndCode(String status){
		List<Object[]> idAndCode = repository.getIdAndCode(status);
		Map<Integer, String> idcode = idAndCode.stream().collect(Collectors.toMap(obj->(Integer.valueOf(obj[0].toString())),obj->obj[1].toString()));
		//System.out.println(idcode.get(0));
		
		return idcode;
	}



}
