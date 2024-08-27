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
@Table(name="sale_order_tab")
@Data
public class SaleOrderDtl {
@Id
@GeneratedValue
@Column(name="sale_ord_id_col")
private Integer id;
@Column(name="sale_qty_col")
private Integer Qty;
@ManyToOne
@JoinColumn(name = "part_id_foerign_key")
private Part part;
@ManyToOne
@JoinColumn(name = "sale_order_id_foerign_key")
private SaleOrder saleorder;
}
