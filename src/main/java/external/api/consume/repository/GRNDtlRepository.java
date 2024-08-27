package external.api.consume.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import external.api.consume.model.GRNDtls;
@Repository
public interface GRNDtlRepository extends JpaRepository<GRNDtls,Integer>{
	@Query("SELECT dtl FROM GRNDtls dtl join  GRN gr on dtl.grn=gr.id WHERE gr.id=:id")
	public List<GRNDtls> getGrnDtls(Integer id);

	@Modifying
	@Query("UPDATE GRNDtls  SET status=:status WHERE id=:grndtlid")
	public void updateStatus(Integer grndtlid, String status);
}
