package ch.arc.pensebet.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="participation")

public class Participation implements Serializable {
	 @Id
	 private long bet_id;
	 
	 @Id
	 private long user_id;
	 
	 @Column
	 private boolean agree;
	 
	 @ManyToOne
	 @PrimaryKeyJoinColumn(name="BET_ID", referencedColumnName="ID")
	 private Bet bet;
	 
	 @ManyToOne
	 @PrimaryKeyJoinColumn(name="USER_ID", referencedColumnName="ID")
	 private User user;
}
