package ch.arc.pensebet.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="user")

public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column
	private Integer id;
	
	@Column
	private String nickname;
	
	@Column
	private String password;
	
	@Column
	private String email;

    @ManyToOne
    @JoinColumn
    private Role role;
    
    @OneToMany(mappedBy="user")
    private List<Participation> users;

    @ManyToMany
    @JoinTable(
      name="INVITATION",
      joinColumns=@JoinColumn(name="USERID", referencedColumnName="ID"),
      inverseJoinColumns=@JoinColumn(name="BET_ID", referencedColumnName="ID"))
    private List<Bet> bets;
    
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return getId() + " : " + getNickname();
	}
}
