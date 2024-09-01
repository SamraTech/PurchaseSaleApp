package external.api.consume.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "shp_tab")
@Data
public class Shipping {
	@Id
	@GeneratedValue
	private Integer id;
	@Column(name="shp_shp_code_col")
	private String shippingCode;
	@Column(name = "shp_ref_num_col")
	private String shipRefNum;
	@Column(name = "shp_cont_col")
	private String contactDetails;
	@ManyToOne
	@JoinColumn(name = "order_id_primary_key", unique = true)
	private SaleOrder order;
	@Column(name = "shp_descrp_col")
	private String description;
	@Column(name = "shp_bill_add_col")
	private String billingToAdd;
	@Column(name = "shp_ship_add_col")
	private String shippingToAddress;

}
