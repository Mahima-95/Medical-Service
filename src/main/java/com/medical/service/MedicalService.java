package com.medical.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Iterables;
import com.medical.model.Patient;
import com.medical.repository.MedicalRepository;

@Service
public class MedicalService {

	@Autowired
	private MedicalRepository medicalRepository;

	@Autowired
	public MedicalService(MedicalRepository medicalRepository) {
		this.medicalRepository = medicalRepository;
	}

	private Comparator<Patient> patientComparatorForId = new Comparator<Patient>() {

		@Override
		public int compare(Patient p1, Patient p2) {
			return Integer.valueOf(p1.getId()) - Integer.valueOf(p2.getId());
		}
	};

	public List<Patient> addPatients(int n) {
		return medicalRepository.add(n, null);
	}

	public Patient[] getAllPatients() {

		Patient[] patients = medicalRepository.getAll(Patient[].class);
		List<Patient> patientList = Arrays.stream(patients).parallel()
				.collect(Collectors.toList());
		Collections.sort(patientList, patientComparatorForId);
		return Iterables.toArray(patientList, Patient.class);
	}

	public Patient updatePatient(Patient patient) {
		return medicalRepository.update(patient);
	}

	public List<Patient> deleteAllPatients() {
		return medicalRepository.deleteAll();
	}

	public List<Patient> deletePatientById(int n) {
		return medicalRepository.deleteById(n);
	}

	public List<Patient> uploadDocument(MultipartFile multipartFile) {

		File file = new File(multipartFile.getOriginalFilename());
		List<Patient> patients = new ArrayList<>();
		try {
			file.createNewFile();
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(multipartFile.getBytes());
			// multipartFile.transferTo(file);
			Reader in = new FileReader(file);
			BufferedReader bufferReader = new BufferedReader(in);
			bufferReader.lines().forEach(
					x -> {
						String[] arr = x.split(",");
						Patient patient = new Patient();
						patient.setName(arr[0]);
						patient.setMobile(arr[1]);
						patient.setAadhaar(arr[2]);
						patient.setEmail(arr[3]);
						patient.setGender(arr[4]);
						patient.setAllergies(arr[5]);
						patient.setPassword(arr[6]);
						patient.setProfilePicPath(arr[7]);
						patient.setTandCAccepted(arr[8] != null ? Integer
								.valueOf(arr[8]) : null);
						patient.setPatientAddress(arr[9]);
						patients.add(patient);

					});
			List<Patient> allPatients = medicalRepository.add(patients.size(), patients);
			bufferReader.close();
			fos.close();
			return allPatients;
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		return patients;
	}
}
