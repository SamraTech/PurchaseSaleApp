package external.api.consume.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import external.api.consume.model.SaleOrder;

public interface SaleOrderRepository extends JpaRepository<SaleOrder,Integer>{
@Modifying
	@Query("update SaleOrder set status=:status  where id=:id")
	public void updateStatus(Integer id,String status);

}
