package cat.ehh.web.model;

import java.io.Serializable;


import javax.persistence.*;



/**
 * The persistent class for the language database table.
 * 
 */
@Entity
@NamedQuery(name="Language.findAll", query="SELECT l FROM Language l")
public class Language implements Serializable {
	private static final long serialVersionUID = 1L;

	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="\"languageId\"")
	private long languageId;

	private String code;

	private String name;

	public Language() {
	}

	public long getLanguageId() {
		return this.languageId;
	}

	public void setLanguageId(long languageId) {
		this.languageId = languageId;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}