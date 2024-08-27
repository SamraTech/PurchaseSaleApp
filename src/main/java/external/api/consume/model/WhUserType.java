package external.api.consume.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "Wh_tab_UserType")
public class WhUserType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer usr_Id;
	@Column(name = "Wh_col_usrTyp")
	private String usr_Type;
	@Column(name = "Wh_col_usrCode")
	private String usr_Code;
	@Column(name = "Wh_col_usrFor")
	private String usr_For;
	@Column(name = "Wh_col_usrEmail")
	private String usr_Email;
	@Column(name = "Wh_col_usrCont")
	private String usr_Cont;
	@Column(name = "Wh_col_usr_Id_Typ")
	private String usr_Id_Type;
	@Column(name = "Wh_col_usrIf_Other")
	private String usr_If_Other;
	@Column(name = "Wh_col_usrId_Num")
	private String usr_Id_Number;

}
