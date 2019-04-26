package ch.arc.pensebet.model;

import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.transaction.annotation.Transactional;

@Entity
@Table(name="bet")

public class Bet {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column
	private Integer id;
	
	@Column
	private String subject;
	
	@Column
	private Date creationDate;
	
	@Column
	private Date endingDate;
	
	@Column
	private float amount;
	
	@Column
	private Boolean result = null;

    @ManyToOne
    @JoinColumn
    private State state;

    @ManyToOne
    @JoinColumn
    private User owner;
    
    @OneToMany(fetch=FetchType.LAZY, mappedBy="bet", cascade = CascadeType.ALL)
    private Set<Participation> participations;
    
    @OneToMany(fetch=FetchType.LAZY, mappedBy="bet", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Invitation> invitations;
	
	public Bet() { }
	
	public Bet(String subject, Date endingDate, float amount, State state, User owner) {
		this.subject = subject;
		this.endingDate = endingDate;
		this.amount = amount;
		this.state = state;
		this.owner = owner;
		
		creationDate = new Date();
	}
	
	public Integer getId() {
		return id;
	}
	public Boolean getResult() {
		return result;
	}
	public void setResult(Boolean result) {
		this.result = result;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Date getEndingDate() {
		return endingDate;
	}
	public void setEndingDate(Date endingDate) {
		this.endingDate = endingDate;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
		
	public Set<Participation> getParticipations() {
		return participations;
	}

	public void setParticipations(Set<Participation> participations) {
		this.participations = participations;
	}

	public Set<Invitation> getInvitations() {
		return invitations;
	}

	public void setInvitations(Set<Invitation> invitations) {
		this.invitations = invitations;
	}

	@Override
	public String toString() {
		return getSubject();
	}
	
	@Transactional
	public void addInvitation(Invitation invitation)
	{
		invitation.setBet(this);
		invitations.add(invitation);
	}
	
	public void addParticipation(Participation participation)
	{
		participation.setBet(this);
		
		cancelInvitation(participation.getUser());
	    
		participations.add(participation);
	}
	
	public void cancelInvitation(User user)
	{
		Iterator<Invitation> iterator = invitations.iterator();
	    while(iterator.hasNext()) {
	        Invitation invitation = iterator.next();
	        
	        if (invitation.getBet().getId() == getId() && 
					invitation.getUser().getId() == user.getId())
			{
	        	invitation.setBet(null);
	        	invitation.setUser(null);
				iterator.remove();
				break;
			}
	    }
	}
	
}
