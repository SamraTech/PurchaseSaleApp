package external.api.consume.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import external.api.consume.model.Shipping;
import external.api.consume.model.ShippingDtls;
import external.api.consume.repository.ShippingDtlRepository;
import external.api.consume.repository.ShippingRepository;

@Service
public class ShippingService {
	@Autowired
	private ShippingRepository repository;
	@Autowired
	private ShippingDtlRepository dtlrepository;

	public Integer saveShipping(Shipping ship) {
		// TODO Auto-generated method stub
		Shipping save = repository.save(ship);
		return save.getId();
	}

	public void saveShipDtl(ShippingDtls dtls) {
		// TODO Auto-generated method stub
		dtlrepository.save(dtls);
	}

	public List<Shipping> getShipping() {
		// TODO Auto-generated method stub
		List<Shipping> shipAll = repository.findAll();

		return shipAll;
	}

	public List<ShippingDtls> getShipDtl(Integer id) {
		// TODO Auto-generated method stub

		List<ShippingDtls> shippingDtlByShippingId = dtlrepository.getShippingDtlByShippingId(id);

		return shippingDtlByShippingId;
	}

	public Shipping getShippingById(Integer id) {
		// TODO Auto-generated method stub
		Optional<Shipping> shipping = repository.findById(id);
	return shipping.get();
	}
@Transactional
	public void updateStatus(Integer dtlid, String status) {
		// TODO Auto-generated method stub
		dtlrepository.updateStatus(dtlid,status);
	}

}
