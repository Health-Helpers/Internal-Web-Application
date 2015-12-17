package cat.ehh.web.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@Table(name = "userehh")
@NamedQuery(name="User.findAll", query="SELECT u FROM UserEHH u")
public class UserEHH implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="UserID_GENERATOR", sequenceName="SEQ_User",initialValue=1,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="UserID_GENERATOR")
	@Column(name="userId")
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

	//bi-directional many-to-one association to Responsible
	@OneToMany(mappedBy="user")
	private List<Responsible> responsibles;

	@Transient
	private Language language;


	public UserEHH() {
	}
	
	

	public UserEHH(String adress, Date birthdate, String iddoc, BigDecimal langid, String name, String phone,
			String surname, Integer type) {
		super();
		this.adress = adress;
		this.birthdate = birthdate;
		this.iddoc = iddoc;
		this.langid = langid;
		this.name = name;
		this.phone = phone;
		this.surname = surname;
		this.type = type;
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

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}



}