package cat.ehh.web.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the "adjBackup" database table.
 * 
 */
@Entity
@NamedQuery(name="AdjBackup.findAll", query="SELECT a FROM AdjBackup a")
public class AdjBackup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ADJBACKUP_ID_GENERATOR", sequenceName="SEQ_BACKUP",initialValue=1,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ADJBACKUP_ID_GENERATOR")
	private long id;

	@Temporal(TemporalType.DATE)
	private Date date;

	private String json;

	@Column(name="\"userId\"")
	private java.math.BigDecimal userId;

	public AdjBackup() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getJson() {
		return this.json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public java.math.BigDecimal getUserId() {
		return this.userId;
	}

	public void setUserId(java.math.BigDecimal userId) {
		this.userId = userId;
	}

}