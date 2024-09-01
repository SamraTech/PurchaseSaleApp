package external.api.consume.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import external.api.consume.model.Shipping;

public interface ShippingRepository extends JpaRepository<Shipping,Integer>{

}
