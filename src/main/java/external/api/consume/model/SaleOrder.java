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
@Table(name = "sale_ordr_tab")
@Data
public class SaleOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "saleordr_id_col")
	private Integer id;
	@Column(name = "saleordr_cod_col")
	private String orderCode;
	@Column(name = "saleordr_ref_numb_col")
	private String refNumber;
	@Column(name = "saleordr_status_col")
	private String status;
	@Column(name = "saleordr_desc_col")
	private String description;
	@Column(name = "saleordr_stk_mode_col")
	private String stockMode;
	@Column(name = "saleordr_source_col")
	private String source;
	@ManyToOne
	@JoinColumn(name = "ship_id_foreign_key")
	private Shipment shipment;
	@ManyToOne
	@JoinColumn(name = "whuser_id_foreign_key")
	private WhUserType user;

}
