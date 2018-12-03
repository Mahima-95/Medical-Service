package com.medical.repository;

import static com.medical.constants.Constants.idMap;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.medical.model.Patient;

@Repository
public class MedicalRepository extends AbstractRepository {

	public MedicalRepository() {
		constructAllMedicalMap();
	}

	public <T> List<T> addPatient(int n, List<Patient> patients) {
		return addDataInList(n, patients);

	}

	@Override
	public <T> List<T> addPatientInFile(List<T> t) {
		try {
			mapper.writeValue(new File(PATH + FILE_NAME), t);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return t;

	}

	public <T> T getAll(Class<T> clazz) {

		File file = new File(PATH + FILE_NAME);
		try {
			return mapper.readValue(file, clazz);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public <T> T update(T t) {

		if (t != null) {
			Field[] fields = t.getClass().getDeclaredFields();
			for (Field field : fields) {
				if (idMap.containsKey(field.getName())) {
					try {
						field.setAccessible(true);
						String value = String.valueOf(field.get(t));
						if (value != null && map.containsKey(value)) {
							map.put(value, t);
							addPatientInFile(convertMapToList(map));
							return (T) map.get(value);
						}
					} catch (IllegalArgumentException | IllegalAccessException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> deleteById(int n) {

		Object removed = map.remove(map.containsKey(String.valueOf(n)) ? String.valueOf(n) : null);
		if (removed != null) {
			List<T> list = new ArrayList<>();
			for (String key : map.keySet()) {
				list.add((T) map.get(key));
			}
			setPatientID(removed);

			return (List<T>) addPatientInFile(list);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public <T> T deletePatients() {
		return (T) addPatientInFile(new ArrayList<>());
	}
}
