package external.api.consume.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import external.api.consume.model.Shipment;

public interface ShipmentRepo extends JpaRepository<Shipment, Integer> {
	@Query("SELECT shpmnt_Id,shpmnt_Code FROM Shipment where enable_Shpmnt=:enabled")
	public List<Object[]> getIdAndShipmentCode(String enabled);
}
