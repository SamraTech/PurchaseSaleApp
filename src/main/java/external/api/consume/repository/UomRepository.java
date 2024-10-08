package external.api.consume.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import external.api.consume.model.UomType;
@Repository
public interface UomRepository extends JpaRepository<UomType,Integer>{

@Query("select uom_Id,uom_Model from UomType")
public List<Object[]>	getUomIdAndModel();
@Query("SELECT COUNT(uom_Model) from UomType WHERE uom_Model=:model")
public Integer validateUomModelCount(String model);
@Query("SELECT COUNT(uom_Model) from UomType WHERE uom_Model=:uommodel and uom_Id!=:id" )

public Integer modelCountExistsWithId(String uommodel, Integer id);
@Query("select u From UomType u  where u.uom_Model like %?1%")
public Page<UomType> findByUomModelLike(String uom_Model,Pageable p);
}
