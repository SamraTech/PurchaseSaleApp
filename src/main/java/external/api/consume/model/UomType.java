package external.api.consume.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Entity
@Table(name="Uom_tab")
public class UomType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="Uom_ID_col" )
	private Integer uom_Id;
	@Column(name ="Uom_type_col" )
	private String uom_Type;
	@Column(name ="Uom_model_col" )
	private String uom_Model;
	@Column(name="uom_decrp")
	private String uom_Decrp;
	
	
	
}
