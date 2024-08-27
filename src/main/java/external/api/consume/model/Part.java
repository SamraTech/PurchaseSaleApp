package external.api.consume.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "Part_tab")
@Data
public class Part {
	@javax.persistence.Id
	@GeneratedValue
	@Column(name = "prt_id_col")
	private Integer partId;
	@Column(name = "prt_cod_col")
	private String partCode;
	@Column(name = "prt_len_col")
	private Double partLen;
	@Column(name = "prt_wid_col")
	private Double partWid;
	@Column(name = "prt_hght_col")
	private Double partHght;
	@Column(name = "prt_cos_col")
	private Double partCost;
	@Column(name = "prt_curen_col")
	private String partCurrency;
	@ManyToOne
	@JoinColumn(name = "uom_id_foriegnkey_col")
	private UomType uomType;
	@ManyToOne
	@JoinColumn(name = "order_method_foerighkey_col")
	private OrderMethod ordermethod;
	
	/*
	 * @ManyToOne
	 * 
	 *  @JoinColumn(name="order_id_foreignkey_col") private OrderMethod orderMethod;
	 */

}
