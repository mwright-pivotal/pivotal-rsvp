package com.pivotal.rsvp.service;

import java.util.List;

import com.pivotal.rsvp.model.Registrant;
import com.pivotal.rsvp.model.RegistrantDTO;

public interface RegistrantService {
	public Registrant create(RegistrantDTO rto);
	
	public List<Registrant> findAll();
}
