package ch.arc.pensebet.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
    
    private float money;
    
    @OneToMany(mappedBy="user", cascade = CascadeType.ALL)
    private List<Participation> participations;

    @OneToMany(mappedBy="user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Invitation> invitations;
    
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

	public float getMoney() {
		return money;
	}

	public void setMoney(float money) {
		this.money = money;
	}
	
	public void addMoney(float money) {
		this.money += money;
	}

	@Override
	public String toString() {
		return getId() + " : " + getNickname();
	}
}
