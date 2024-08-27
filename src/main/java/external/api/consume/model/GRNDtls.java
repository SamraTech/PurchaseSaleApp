package external.api.consume.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "grn_dtl_tab")
@Data
public class GRNDtls { 
	@Id
	@GeneratedValue
	private Integer id;
	@Column(name = "grn_code_col")
	private String itemcode;
	@Column(name = "grn_cost_col")
	private Double baseCost;
	@Column(name = "grn_qty_col")
	private Integer qty;
	@Column(name = "grn_val_col")
	private Double itemval;
	String status;
	@ManyToOne
	@JoinColumn(name="grn_id_foreigh_key_col")
	private GRN grn;

}
