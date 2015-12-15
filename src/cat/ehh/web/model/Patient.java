package cat.ehh.web.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the patient database table.
 * 
 */
@Entity
@NamedQuery(name="Patient.findAll", query="SELECT p FROM Patient p")
public class Patient implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PatientID_GENERATOR", sequenceName="SEQ_PATIENT",initialValue=1,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PatientID_GENERATOR")
	@Column(name="patientId")
	private long patientId;

	@Column(name="dependencyGrade")
	private String dependencyGrade;

	private String disease;

	@Column(name="userId")
	private java.math.BigDecimal userId;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumns({@JoinColumn(name="userId",insertable=false,updatable=false)
	})
	private UserEHH user;

	public Patient() {
	}

	public long getPatientId() {
		return this.patientId;
	}

	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}

	public String getDependencyGrade() {
		return this.dependencyGrade;
	}

	public void setDependencyGrade(String dependencyGrade) {
		this.dependencyGrade = dependencyGrade;
	}

	public String getDisease() {
		return this.disease;
	}

	public void setDisease(String disease) {
		this.disease = disease;
	}

	public java.math.BigDecimal getUserId() {
		return this.userId;
	}

	public void setUserId(java.math.BigDecimal userId) {
		this.userId = userId;
	}

	public UserEHH getUser() {
		return user;
	}

	public void setUser(UserEHH user) {
		this.user = user;
	}

	
	

}