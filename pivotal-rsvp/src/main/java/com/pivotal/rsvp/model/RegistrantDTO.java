package com.pivotal.rsvp.model;

import java.util.Date;

public class RegistrantDTO {
	
	Integer eventID;
	String name;
	String email_addr;
	String phoneNbr;
    private Long id;
    private Date creationTime;
	private long version = 0;
	
	public Integer getEventID() {
		return eventID;
	}
	public void setEventID(Integer eventID) {
		this.eventID = eventID;
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
