package external.api.consume.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

import external.api.consume.model.UomType;
import external.api.consume.repository.UomRepository;

@Service
public class UomService {
	@Autowired
	private UomRepository repository;

	public String saveUom(UomType type) {
		// TODO Auto-generated method stub
		UomType save = repository.save(type);
		return "uom save successfully" + save.getUom_Id();

	}

	/*
	 * public List<UomType> getUom() { List<UomType> uom = repository.findAll();
	 * 
	 * // TODO Auto-generated method stub return uom;
	 */
	// }
	// for pagination
	public Page<UomType> getUom(Pageable p) {
		Page<UomType> uom = repository.findAll(p);

		// TODO Auto-generated method stub
		return uom;
	}

	public UomType getUomByID(Integer id) {

		Optional<UomType> uomType = repository.findById(id);
		return uomType.get();
	}

	public String deleteByUomId(Integer id) {
		String msg = "";
		if (repository.findById(id).get().getUom_Id() == id) {
			msg = "" + id + " is deleted succesfully";
			repository.deleteById(id);
		} else {
			msg = "" + id + " is not found";
		}
		return msg;
	}

	public String saveUomUpdate(UomType type) {
		UomType save = repository.save(type);

		return "updated " + save.getUom_Id();
	}

	public Map<Integer, String> getUomIdAndModel() {
		Map<Integer, String> mp = new HashMap<Integer, String>();
		List<Object[]> uom = repository.getUomIdAndModel();
		for (Object obj[] : uom) {
			mp.put(Integer.valueOf(obj[0].toString()), obj[1].toString());
		}
		return mp;
	}

	public boolean validateUomModelCount(String model) {
		// TODO Auto-generated method stub
		boolean msg = false;
		Integer modelcount = repository.validateUomModelCount(model);

		if (modelcount > 0) {
			System.out.println("in count of if");
			msg = true;

		}

		else {
			System.out.println("in count of elseif");
			msg = false;
		}
		return msg;
	}

	public boolean validateUomModelEditIdExits(String uommodel, Integer id) {
		// TODO Auto-generated method stub
		boolean msg = false;
		Integer modelCountExistsWithId = repository.modelCountExistsWithId(uommodel, id);
		if (modelCountExistsWithId > 0) {
			System.out.println("in count of if");
			msg = true;

		} else {
			System.out.println("in count of elseif");
			msg = false;
		}
		return msg;

	}

	public Page<UomType> findByUomModelContaining(String uomModel,Pageable page) {

		Page<UomType> uomModelContaining = repository.findByUomModelLike(uomModel, page);
		return uomModelContaining;
	}

}
