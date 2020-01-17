package root.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "hashCode_table")
public class HashCodeConfirm {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "username", unique = true, nullable = false)
	String username;
	
	@Column(name = "potvrdjen", unique = false, nullable = true)
	String potvrdjen;
	
	public String getPotvrdjen() {
		return potvrdjen;
	}

	public void setPotvrdjen(String potvrdjen) {
		this.potvrdjen = potvrdjen;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getHashCode() {
		return hashCode;
	}

	public void setHashCode(String hashCode) {
		this.hashCode = hashCode;
	}

	@Column(name = "hashCode", unique = false, nullable = true)
	String hashCode;
	
	public HashCodeConfirm() {}
	
	public HashCodeConfirm(String username, String hashCode, String potvrdjen) {
		this.hashCode = hashCode;
		this.username = username;
		this.potvrdjen = potvrdjen;
	}
	
	
}
