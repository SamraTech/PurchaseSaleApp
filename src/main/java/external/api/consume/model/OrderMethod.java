package external.api.consume.model;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "Order_Meth_tab")
public class OrderMethod {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_Id_col")
	private Integer order_method_Id;
	@Column(name = "order_Mode_col")
	private String order_Mode;
	@Column(name = "order_Code_col")
	private String order_Code;
	@Column(name = "order_Method_col")
	private String order_Method;
	@ElementCollection
	@CollectionTable(name = "order_Method_Accept", joinColumns = @JoinColumn(name = "order_Id_col"))
	@Column(name = "order_Accept")
	private List<String> order_Accept;
	@Column(name = "order_Descrp_col")
	private String order_Descrp;

}
