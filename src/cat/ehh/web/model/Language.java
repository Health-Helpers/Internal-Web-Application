package cat.ehh.web.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;
import java.util.List;


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

	//bi-directional many-to-one association to User
	//@OneToMany(mappedBy="langid")
	private List<User> users;

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

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User addUser(User user) {
		getUsers().add(user);
		user.setLangid(BigDecimal.valueOf(this.getLanguageId()));

		return user;
	}

	public User removeUser(User user) {
		getUsers().remove(user);
		user.setLangid(new BigDecimal(0));

		return user;
	}

}