package external.api.consume;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import external.api.consume.model.Shipment;
import external.api.consume.repository.ShipmentRepo;
import external.api.consume.repository.UomRepository;
import external.api.consume.service.OrderMethodService;
import external.api.consume.service.PurchaseOrderDtlService;
import external.api.consume.service.ShipmentService;
import external.api.consume.service.UomService;


@SpringBootApplication
@EnableJpaRepositories
public class ExternalApiApplication implements ApplicationRunner {
	/*
	 * @Autowired private ShipmentRepo repo;
	 */
	/*
	 * @Autowired private UomService repo;
	 */
	/*
	 * @Autowired private OrderMethodService ser;
	 * 
	 * @Autowired private ShipmentService sership;
	 * 
	 * @Autowired private PurchaseOrderDtlService purservice;
	 */
	public static void main(String[] args) {
		// OrderMethodService sr=new OrderMethodService();
		SpringApplication.run(ExternalApiApplication.class, args);
		System.out.println("done");

	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		/*
		 * // TODO Auto-generated method stub Map<Integer, String> idAndCode =
		 * ser.getIdAndCode("Sale");
		// */	//System.out.println(idAndCode);
		/*
		 * Map<Integer, String> idAndCode = sership.getIdAndCode("yes");
		 * System.out.println(idAndCode);
		 */
		/*
		 * List<Object[]> partQty = purservice.getPartQty(1);
		 * System.out.println(partQty);
		 */
	}

	/*
	 * @Override public void run(ApplicationArguments args) throws Exception {
	 * 
	 * Map<Integer, String> uomIdAndModel = repo.getUomIdAndModel();
	 * Stream.of(uomIdAndModel).forEach(map -> System.out.println(map)); ;
	 * 
	 * // TODO Auto-generated method stub Shipment save = repo.save(new
	 * Shipment("Roadway","RBC",true,"C","through Road"));
	 * System.out.println("Shipment id is="+save.getShpmnt_Id());
	 * 
	 * } Map<Integer, String> idAndCode = ser.getIdAndCode("sale");
	 * 
	 * System.out.println(idAndCode);
	 * 
	 * 
	 * }
	 */

}
