package com.pivotal.rsvp.model;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "registrants")
public class Registrant {
	
	@Column(name = "event_id", nullable = false)
	String eventID;
	
	@Column(name = "name", nullable = false)
	String name;
	
	@Column(name = "email_addr", nullable = false)
	String email_addr;
	
	@Column(name = "phone_nbr", nullable = false)
	String phoneNbr;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@Column(name = "creation_time", nullable = false)
    private Date creationTime;
	
	@Version
	private long version = 0;
	
	public String getEventID() {
		return eventID;
	}
	public void setEventID(String eventID2) {
		this.eventID = eventID2;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmailAddr() {
		return email_addr;
	}
	public void setEmailAddr(String email_addr) {
		this.email_addr = email_addr;
	}
	public String getPhoneNbr() {
		return phoneNbr;
	}
	public void setPhoneNbr(String phoneNbr) {
		this.phoneNbr = phoneNbr;
	}

	@PrePersist
    public void prePersist() {
        Date now = new Date();
        creationTime = now;
    }
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}
	public long getVersion() {
		return version;
	}
	public void setVersion(long version) {
		this.version = version;
	}
}
