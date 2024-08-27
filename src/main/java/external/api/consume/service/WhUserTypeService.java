package external.api.consume.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import external.api.consume.model.WhUserType;
import external.api.consume.repository.WhUserTypeRepoitory;

@Service
public class WhUserTypeService {
	@Autowired
	private WhUserTypeRepoitory repository;
	private List<WhUserType> usrList;

	public String saveWhUser(WhUserType user) {
		System.out.println("i am in service");
		// TODO Auto-generated method stub
		WhUserType save = repository.save(user);

		return "user id is save=" + save.getUsr_Id();
	}

	public List<WhUserType> getWhUserType() {

		usrList = repository.findAll();
		return usrList;
	}

	public WhUserType getWhUserTypeById(Integer id) {
		Optional<WhUserType> whUserType = repository.findById(id);
		return whUserType.get();
	}

	public String updateWhUserypeById(WhUserType user, Integer id) {
		String msg ="";
		if (repository.findById(id)!=null) {
			WhUserType save = repository.save(user);
			msg="updated successfully"+save;
			
		}
		else {
			msg="no id found in database";
			
		}
		return msg;
		
		
		// TODO Auto-generated method stub
		
	}

	public String deleteByWhUserId(Integer id) {
		// TODO Auto-generated method stub
		String msg="";
		if (repository.findById(id).get().getUsr_Id().equals(id)) {
			  repository.deleteById(id);	
			   msg="id found and deleted";
		}
		else {
			msg="no id found in table";
			 
		}
		   
			return msg;
	}
	
	
public Map<Integer,String> getIdAndCode() {
	//Map<Integer,String> map=new HashMap<Integer, String>();
	List<Object[]> idAndCode = repository.getIdAndWHUserTyepCode();
	Map<Integer, String> mp = idAndCode.stream().collect(Collectors.toMap(obj->Integer.valueOf(obj[0].toString()), obj->obj[1].toString()));
	System.out.println(mp);
	
	return mp;

}

}
