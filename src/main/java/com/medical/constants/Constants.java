package com.medical.constants;

import java.util.HashMap;
import java.util.Map;

import com.medical.model.Patient;

public class Constants {

	@SuppressWarnings("serial")
	public static final Map<String, Object> idMap = new HashMap<String, Object>() {
		{
			put("pId", Patient.class);
		}
	};

}
