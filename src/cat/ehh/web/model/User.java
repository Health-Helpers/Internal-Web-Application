package cat.ehh.web.model;

import java.io.Serializable;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="\"userId\"")
	private long userId;

	private String adress;

	@Temporal(TemporalType.DATE)
	private Date birthdate;

	private String iddoc;

	private java.math.BigDecimal langid;

	private String name;

	private String phone;

	private String surname;

	private Integer type;

	//bi-directional many-to-one association to Patient
	@OneToMany(mappedBy="user")
	private List<Patient> patients;

	//bi-directional many-to-one association to PatientResponsible
	@OneToMany(mappedBy="user")
	private List<PatientResponsible> patientResponsibles;

	//bi-directional many-to-one association to Responsible
	@OneToMany(mappedBy="user")
	private List<Responsible> responsibles;
	
	

	public User() {
	}

	public long getUserId() {
		return this.userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getAdress() {
		return this.adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public Date getBirthdate() {
		return this.birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getIddoc() {
		return this.iddoc;
	}

	public void setIddoc(String iddoc) {
		this.iddoc = iddoc;
	}

	public java.math.BigDecimal getLangid() {
		return this.langid;
	}

	public void setLangid(java.math.BigDecimal langid) {
		this.langid = langid;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public List<Patient> getPatients() {
		return this.patients;
	}

	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}

	public Patient addPatient(Patient patient) {
		getPatients().add(patient);
		patient.setUser(this);

		return patient;
	}

	public Patient removePatient(Patient patient) {
		getPatients().remove(patient);
		patient.setUser(null);

		return patient;
	}

	public List<PatientResponsible> getPatientResponsibles() {
		return this.patientResponsibles;
	}

	public void setPatientResponsibles(List<PatientResponsible> patientResponsibles) {
		this.patientResponsibles = patientResponsibles;
	}

	public PatientResponsible addPatientResponsible(PatientResponsible patientResponsible) {
		getPatientResponsibles().add(patientResponsible);
		patientResponsible.setUser(this);

		return patientResponsible;
	}

	public PatientResponsible removePatientResponsible(PatientResponsible patientResponsible) {
		getPatientResponsibles().remove(patientResponsible);
		patientResponsible.setUser(null);

		return patientResponsible;
	}

	public List<Responsible> getResponsibles() {
		return this.responsibles;
	}

	public void setResponsibles(List<Responsible> responsibles) {
		this.responsibles = responsibles;
	}

	public Responsible addResponsible(Responsible responsible) {
		getResponsibles().add(responsible);
		responsible.setUser(this);

		return responsible;
	}

	public Responsible removeResponsible(Responsible responsible) {
		getResponsibles().remove(responsible);
		responsible.setUser(null);

		return responsible;
	}

}