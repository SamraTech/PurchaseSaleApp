package external.api.consume.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import external.api.consume.model.SaleOrder;
import external.api.consume.model.Shipping;

public interface SaleOrderRepository extends JpaRepository<SaleOrder,Integer>{
@Modifying
	@Query("update SaleOrder set status=:status  where id=:id")
	public void updateStatus(Integer id,String status);
@Query("select id,orderCode From SaleOrder where status=:status")

      public List<Object[]> getIdAndCode(String status);


}
