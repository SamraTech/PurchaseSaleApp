package external.api.consume.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



// select prt.prt_cod_col,prt.prt_cos_col,dtl.prt_qty_col, 
//ord.po_id_col  from purchase_order_tab  ord join 
//purchase_ordr_dtl_tab dtl on ord.po_id_col =dtl.purchase_order_id_foreign_key join Part_tab prt on 
//prt.prt_id_col=dtl.prt_id_foreign_key where ord.po_id_col=1;;

@Repository
public interface PurchaseOrderDtl extends JpaRepository<external.api.consume.model.PurchaseOrderDtl,Integer>{
@Query("SELECT prt.partCode,prt.partCost,dtl.Qty FROM PurchaseOrder ord join PurchaseOrderDtl dtl on ord.id=dtl.order join Part prt on prt.partId=dtl.part WHERE ord.id=:poId")
	public List<Object[]> getprtCodeCosQtyBYPurchaseOrderId(Integer poId);

	@Query("SELECT dtl FROM PurchaseOrderDtl dtl  join  PurchaseOrder ord on dtl.order=ord.id WHERE ord.id=:poId")
	public List<external.api.consume.model.PurchaseOrderDtl> getpurchaseDtlBYpurOrderId(Integer poId);
	
	@Query("SELECT COUNT(dtl.id) FROM PurchaseOrderDtl dtl  join  PurchaseOrder ord on dtl.order=ord.id WHERE ord.id=:poId")
	public Integer countPurDtlId(Integer poId);

	
}
