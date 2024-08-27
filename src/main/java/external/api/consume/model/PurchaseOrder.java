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
@Table(name="purchase_order_tab")
@Data
public class PurchaseOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "po_id_col")
	private Integer id;
	@Column(name = "po_cod_col")
	private String orderCode;
	@Column(name = "po_ref_numb_col")
	private String refNumber;
	@Column(name = "po_qlty_chk_col")
	private String qltyCheck;
	@Column(name = "po_status_col")
	private String status;
	@Column(name = "po_desc_col")
	private String description;
	@ManyToOne
	@JoinColumn(name="ship_foreign_key")
	private Shipment shipment;
	@ManyToOne 
	@JoinColumn(name="WhUser_foreign_key")
	private WhUserType user;
	
	
}
