package com.medical.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medical.model.Patient;

public abstract class AbstractRepository {

	protected Map<String, Object> map = null;
	protected TreeSet<Integer> GetIDSet = new TreeSet<>();
	protected ObjectMapper mapper = new ObjectMapper();
	protected static final String PATH = "src/main/resources/";
	protected static final String FILE_NAME = "patient.json";

	protected abstract <T> T getAll(Class<T> clazz);

	protected abstract <T> List<T> deleteById(int n);

	protected abstract <T> List<T> addPatientInFile(List<T> t);

	@SuppressWarnings("unchecked")
	protected <T> List<T> convertMapToList(Map<String, Object> map) {
		return (List<T>) map.values().stream().parallel().collect(Collectors.toList());
	}

	protected void setPatientID(Object object) {

		if (object != null) {
			Patient patient = (Patient) object;
			GetIDSet.add(Integer.valueOf(patient.getId()));
		}
	}

	protected void constructAllMedicalMap() {

		Patient[] patients = getAll(Patient[].class);
		if (patients.length == 0) {
			addPatientInFile(new ArrayList<>());
		} else {
			this.map = new HashMap<>();
			for (Patient patient : patients) {
				map.put(patient.getId(), patient);
			}
		}
	}

	@SuppressWarnings("unchecked")
	protected <T> List<T> addDataInList(int n, List<Patient> patients) {

		if (GetIDSet.size() > 0) {
			if (GetIDSet.size() >= n) {
				setIteratorAndRemoveFromSet(patients);
			} else {
				setIteratorAndRemoveFromSet(patients);
				setIntoMap(n, patients);
			}
		} else {
			setIntoMap(n, patients);
		}
		return (List<T>) addPatientInFile(
				Arrays.stream(map.values().toArray()).parallel().collect(Collectors.toList()));

	}

	private void setIteratorAndRemoveFromSet(List<Patient> patients) {
		Iterator<Integer> iterator = GetIDSet.iterator();
		if (patients == null || patients.size() < 1) {
			List<Patient> patientList = new ArrayList<Patient>();
			while (iterator.hasNext()) {
				patientList.add(setPatient(iterator.next()));
				iterator.remove();
			}
			patientList.forEach(x -> map.put(x.getId(), x));
		} else {
			while (iterator.hasNext()) {
				for (int j = 0; j <= patients.size(); j++) {
					patients.get(j).setId(String.valueOf(iterator.next()));
				}
				iterator.remove();
			}
			patients.forEach(x -> map.put(x.getId(), x));
		}
	}

	private void setIntoMap(int n, List<Patient> patients) {

		if (patients == null || patients.isEmpty()) {
			List<Patient> patientList = new ArrayList<Patient>();
			for (int i = map.size() + 1; i <= map.size() + n; i++) {
				patientList.add(setPatient(i));
			}
			patientList.forEach(x -> map.put(x.getId(), x));
		} else {
			for (int i = map.size() + 1, j = 0; i <= map.size() + n; i++, j++) {
				patients.get(j).setId(String.valueOf(i));
			}
			patients.forEach(x -> map.put(x.getId(), x));
		}
	}

	private Patient setPatient(int id) {

		Patient patient = new Patient();
		patient.setAadhaar("Adhaar");
		patient.setCreatedDate(new Date());
		patient.setEmail("abc@mail.com");
		patient.setGender("Male");
		patient.setId(String.valueOf(id));
		patient.setMobile("8524585450");
		patient.setName("Aviral");
		patient.setPassword("Hello");
		patient.setProfilePicPath("path");
		return patient;
	}
}
