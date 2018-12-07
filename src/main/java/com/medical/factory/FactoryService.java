package com.medical.factory;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.medical.model.Patient;
import com.medical.repository.MedicalRepository;

@Component
public class FactoryService {

	@Autowired
	MedicalRepository medicalRepository;

	public String checkSystem() {
		String computername;
		try {
			computername = InetAddress.getLocalHost().getHostName();
			System.out.println("computer name " + computername);

			if (computername.contains("XAV-101000702")) {
				medicalRepository.getAll(Patient[].class);
			} else {
				
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		return null;

	}

}
