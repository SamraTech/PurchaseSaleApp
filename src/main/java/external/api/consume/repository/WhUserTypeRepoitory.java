package external.api.consume.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import external.api.consume.model.WhUserType;

public interface WhUserTypeRepoitory  extends JpaRepository<WhUserType,Integer>{
	@Query("SELECT usr_Id,usr_Code FROM WhUserType")
	public List<Object[]> getIdAndWHUserTyepCode();
}
