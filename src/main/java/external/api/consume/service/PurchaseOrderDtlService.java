package external.api.consume.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import external.api.consume.model.PurchaseOrderDtl;

@Service
public class PurchaseOrderDtlService {
	@Autowired
	private external.api.consume.repository.PurchaseOrderDtl repodtl;

	public String savePurchaseOrderDtl(PurchaseOrderDtl dtl) {
		PurchaseOrderDtl save = repodtl.save(dtl);
		
		
		return "dtl save with id="+save.getId();
	}
	public List<Object[]> getPartQty(Integer poId){
		List<Object[]> prtqty = repodtl.getprtCodeCosQtyBYPurchaseOrderId(poId);
		
		return prtqty;
		
		
	}
	public List<PurchaseOrderDtl> getpurchasedtl(Integer poId){
		List<PurchaseOrderDtl> purdtl = repodtl.getpurchaseDtlBYpurOrderId(poId);
		purdtl.forEach(data->System.out.println(data));
		return purdtl;
		
		
	}
	public void removePart(Integer dtlid) {
		// TODO Auto-generated method stub
		
		repodtl.deleteById(dtlid);
		System.out.println("in dtl repositorty");
	}
	public Integer countPurDtlId(Integer orderId) {
		Integer countPurDtlId = repodtl.countPurDtlId(orderId);
		System.out.println("count is="+countPurDtlId);
		System.out.println("Orer id is="+orderId);
		return countPurDtlId;
		
	}
}
