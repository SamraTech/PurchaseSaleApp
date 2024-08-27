package external.api.consume.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

import lombok.RequiredArgsConstructor;

@Entity
@Data

public class Shipment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer shpmnt_Id;
	private String shpmnt_Mode;
	private String shpmnt_Code;
	private String enable_Shpmnt;
	private String shpmnt_Grade;
	private String shpmnt_Descrp;

	

}
