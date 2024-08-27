package external.api.consume.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import external.api.consume.model.PurchaseOrder;

@Repository
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Integer> {
	@Modifying
	@Query("UPDATE PurchaseOrder SET status=:status WHERE  id=:orderid")
	public void updateStatus(String status, Integer orderid);
@Query("SELECT id,orderCode FROM PurchaseOrder WHERE status=:status")
	public List<Object[]> getIdAndCode(String status);
}
