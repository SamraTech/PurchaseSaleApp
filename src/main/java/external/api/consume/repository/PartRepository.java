package external.api.consume.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import external.api.consume.model.Part;
@Repository
public interface PartRepository extends JpaRepository<Part,Integer>{
           
	@Query("SELECT partId,partCode FROM Part")
	public List<Object[]>getPartIdAndCode();
}
