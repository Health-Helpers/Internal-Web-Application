package cat.ehh.web.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the adjustements database table.
 * 
 */
@Entity
@NamedQuery(name="Adjustement.findAll", query="SELECT a FROM Adjustement a")
public class Adjustement implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ADJUSTEMENTS_IDADJUSTEMENTS_GENERATOR", sequenceName="SEQ_ADJUSTEMENTS",initialValue=1,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ADJUSTEMENTS_IDADJUSTEMENTS_GENERATOR")
	@Column(name="\"idAdjustements\"")
	private long idAdjustements;

	private String json;

	@Column(name="\"userId\"")
	private java.math.BigDecimal userId;

	public Adjustement() {
	}

	public long getIdAdjustements() {
		return this.idAdjustements;
	}

	public void setIdAdjustements(long idAdjustements) {
		this.idAdjustements = idAdjustements;
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