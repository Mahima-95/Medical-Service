package com.medical.factory;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.medical.repository.AbstractRepository;
import com.medical.repository.MedicalDBRepository;
import com.medical.repository.MedicalFileRepository;

@Component
public class MedicalRepoFactory {

	@Autowired
	private MedicalFileRepository medicalFileRepository;
	@Autowired
	private MedicalDBRepository medicalDBRepository;

	public AbstractRepository repositoryFactory() {
		String computername;
		try {
			computername = InetAddress.getLocalHost().getHostName();
			System.out.println("computer name " + computername);

			if (computername.contains("XAV-101000702")) {
				return medicalFileRepository;
			} else {
				return medicalDBRepository;
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return null;
	}

}
