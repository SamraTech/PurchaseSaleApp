package external.api.consume.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import external.api.consume.model.ShippingDtls;

public interface ShippingDtlRepository extends JpaRepository<ShippingDtls,Integer>{
@Query("SELECT dtl FROM ShippingDtls dtl join dtl.ship as ship WHERE ship.id=:id")
	public List<ShippingDtls> getShippingDtlByShippingId(Integer id);

@Modifying
@Query("UPDATE ShippingDtls  SET status=:status    WHERE id=:dtlid")

public void updateStatus(Integer dtlid, String status);

}
