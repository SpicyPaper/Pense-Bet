package ch.arc.pensebet.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="user")

public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column
	public Integer id;
	
	@Column
	public String nickname;
	
	@Column
	public String email;

    @ManyToOne
    @JoinColumn
	public Role role;
    
    public User() { }
    
    public User(String nickname, String email, Role role) {
    	this.nickname = nickname;
    	this.email = email;
    	this.role = role;
    }

	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public Integer getId() {
		return id;
	}

}
