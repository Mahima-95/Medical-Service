package com.medical.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.medical.model.Patient;
import com.medical.service.MedicalService;

@RestController
public class MedicalController {
	
	@Autowired
	private MedicalService medicalService;

	@RequestMapping("/addPatient")
	public List<Patient> addPatients(@RequestParam int n) {
		return medicalService.addPatients(n);
	}

	@RequestMapping("/getPatient")
	public Patient[] getAllPatients() {
		return medicalService.getAllPatients();
	}

	@RequestMapping(value = "/updatePatient", method = RequestMethod.POST)
	public Patient updatePatient(@RequestBody Patient patient) {
		return medicalService.updatePatient(patient);
	}

	@RequestMapping("/deleteAllPatient")
	public List<Patient> deleteAllPatients() {
		return medicalService.deleteAllPatients();
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public List<Patient> uploadDocument(@RequestParam("file") MultipartFile file) {

		System.out.println(file);
		return medicalService.uploadDocument(file);
	}

	@RequestMapping("/deletePatientById")
	public List<Patient> deletePatientById(@RequestParam int n) {
		return medicalService.deletePatientById(n);
	}
}
