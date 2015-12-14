package cat.ehh.web.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "patientResponsible" database table.
 * 
 */
@Entity
@Table(name="\"patientResponsible\"")
@NamedQuery(name="PatientResponsible.findAll", query="SELECT p FROM PatientResponsible p")
public class PatientResponsible implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PatientRespID_GENERATOR", sequenceName="SEQ_PATIENTRESP",initialValue=1,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PatientRespID_GENERATOR")
	@Column(name="patientResponsibleId")
	private long patientResponsibleId;

	@Column(name="responsibleId")
	private java.math.BigDecimal responsibleId;

	@Column(name="userId")
	private java.math.BigDecimal userId;

	//bi-directional many-to-one association to Responsible
	@ManyToOne
	@JoinColumns({@JoinColumn(name="responsibleId",insertable=false,updatable=false)
		})
	private Responsible responsible;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumns({@JoinColumn(name="userId",insertable=false,updatable=false)
		})
	private UserEHH user;

	public PatientResponsible() {
	}

	public long getPatientResponsibleId() {
		return this.patientResponsibleId;
	}

	public void setPatientResponsibleId(long patientResponsibleId) {
		this.patientResponsibleId = patientResponsibleId;
	}

	public java.math.BigDecimal getResponsibleId() {
		return this.responsibleId;
	}

	public void setResponsibleId(java.math.BigDecimal responsibleId) {
		this.responsibleId = responsibleId;
	}

	public java.math.BigDecimal getUserId() {
		return this.userId;
	}

	public void setUserId(java.math.BigDecimal userId) {
		this.userId = userId;
	}

	public Responsible getResponsible() {
		return this.responsible;
	}

	public void setResponsible(Responsible responsible) {
		this.responsible = responsible;
	}

	public UserEHH getUser() {
		return this.user;
	}

	public void setUser(UserEHH user) {
		this.user = user;
	}

}