package cat.ehh.web.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the "auxiliar-data" database table.
 * 
 */
@Entity
@NamedQuery(name="Auxiliar_data.findAll", query="SELECT a FROM Auxiliar_data a")
public class Auxiliar_data implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="AUXILIAR-DATA_ID_GENERATOR", sequenceName="SEQ_AUXILIAR",initialValue=1,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="AUXILIAR-DATA_ID_GENERATOR")
	@Column(name="idAuxiliarData")
	private long idAuxiliarData;

	@Column(name="auxiliar-data")
	private BigDecimal auxiliar_data;

	@Column(name="patientId")
	private BigDecimal patientId;

	public Auxiliar_data() {
	}

	public long getIdAuxiliarData() {
		return this.idAuxiliarData;
	}

	public void setIdAuxiliarData(long idAuxiliarData) {
		this.idAuxiliarData = idAuxiliarData;
	}

	public BigDecimal getAuxiliar_data() {
		return this.auxiliar_data;
	}

	public void setAuxiliar_data(BigDecimal auxiliar_data) {
		this.auxiliar_data = auxiliar_data;
	}

	public BigDecimal getPatientId() {
		return this.patientId;
	}

	public void setPatientId(BigDecimal patientId) {
		this.patientId = patientId;
	}

}