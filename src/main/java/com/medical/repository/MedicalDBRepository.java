package com.medical.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.medical.model.Patient;

@Repository
public class MedicalDBRepository extends AbstractRepository {

	@Override
	public <T> T getAll(Class<T> clazz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> deleteById(int n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> addListInFile(List<T> t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> add(int n, List<Patient> patients) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T update(T t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T deleteAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
