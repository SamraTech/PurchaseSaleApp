package external.api.consume.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import external.api.consume.model.GRN;
import external.api.consume.model.GRNDtls;
import external.api.consume.repository.GRNDtlRepository;
import external.api.consume.repository.GRNRepository;

@Service
public class GRNService {
@Autowired
private GRNRepository repo;
@Autowired
private GRNDtlRepository grndtlrepo;
	public String saveGrn(GRN grn) {
		// TODO Auto-generated method stub
		GRN save = repo.save(grn);
		return "Grn id is="+save.getId();
		
	}
	public List<GRN> getGrn(){
		return repo.findAll();
	}
	
	public void saveGrnDtl(GRNDtls dtls) {
		
		GRNDtls save = grndtlrepo.save(dtls);
		System.out.println(save.getItemcode());
		
		
	}
	public List<GRNDtls> getGRNDtl(Integer id){
		List<GRNDtls> grnDtls = grndtlrepo.getGrnDtls(id);
		for (GRNDtls dtl:grnDtls) {
			System.out.println(dtl.getItemcode());
		}
		return grnDtls;
	}
	public GRN getGRnById(Integer id) {
		// TODO Auto-generated method stub
		return repo.findById(id).get();
	}
	@Transactional
	public void updateStatus(Integer grndtlid, String status) {
		// TODO Auto-generated method stub
		grndtlrepo.updateStatus(grndtlid,status);
	}
	
}
