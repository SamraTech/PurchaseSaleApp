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
@Table(name = "shipping_dtl_tab")
@Data
public class ShippingDtls {
	@Id
	@GeneratedValue
	@Column(name = "ship_id_col")
	private Integer id;
	@Column(name = "ship_itm_cod_col")
	private String itemCode;
	@Column(name = "ship_qty_col")
	private Integer Qty;
	@Column(name = "ship_val_col")
	private Double Itemvalue;
	@Column(name = "ship_cost_col")
	private Double baseCost;
	@Column(name = "ship_status_col")
	private String status;
	@ManyToOne
	@JoinColumn(name="ship_id_foreign_key")
	private Shipping ship;

}
