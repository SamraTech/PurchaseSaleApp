package external.api.consume.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import external.api.consume.model.OrderMethod;

public interface OrderMethodRepository extends JpaRepository<OrderMethod, Integer> {
	/*
	 * @Query("select  order_method_Id,order_Code From OrderMethod where order_Mode=:order_Mode"
	 * ) public List<Object[]> getIdCode(String order_Mode);
	 */
	@Query("select  order_method_Id,order_Code From OrderMethod where order_Mode=:mode")
	public List<Object[]> getIdAndCodeSale(String mode);
	 
		@Query("select  order_method_Id,order_Code From OrderMethod where order_Mode=:mode")
		public List<Object[]> getIdAndCodePurchase(String mode);
		

		@Query("select  order_method_Id,order_Code From OrderMethod where order_Mode=:mode")
		public List<Object[]> getIdAndCode(String mode);
		
		
	
}
