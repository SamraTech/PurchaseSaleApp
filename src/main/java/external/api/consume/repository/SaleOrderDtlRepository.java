package external.api.consume.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import external.api.consume.model.SaleOrderDtl;

public interface SaleOrderDtlRepository extends JpaRepository<SaleOrderDtl,Integer> {

@Query("select  dtl from  SaleOrderDtl dtl join dtl.saleorder as order where order.id=:saleorderid ")	
public List<SaleOrderDtl> getSaleOrderDtl(Integer saleorderid);
@Query("select  count(dtl) from  SaleOrderDtl dtl join dtl.saleorder as order where order.id=:orid ")	

public Integer countDtlBySaleId(Integer orid);	
}
