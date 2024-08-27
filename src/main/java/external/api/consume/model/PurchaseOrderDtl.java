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
@Table(name = "purchase_ordr_dtl_tab")
@Data
public class PurchaseOrderDtl {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "purchase_dtl_id_col")
	private Integer id;
	@Column(name = "prt_qty_col")
	private Integer Qty;
	@ManyToOne
	@JoinColumn(name = "prt_id_foreign_key")
	private Part part;
	@ManyToOne
	@JoinColumn(name = "purchase_order_id_foreign_key")
	private PurchaseOrder order;
}
