package external.api.consume.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="grn_tab")
@Data
public class GRN {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "grn_code_col")
	private String grn_Code;
	@Column(name ="grn_type_col")
	private String grn_Type;
	private String grn_Descrp;
	@ManyToOne
	@JoinColumn(name="po_id_foriegn_key", unique = true)
	private PurchaseOrder order;
}
