package com.medical.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Configuration
public class Configurations {

	private String username = "root";
	private String password = "root";
	private String connectionUrl = "jdbc:mysql://localhost:3306/medical";
	private String driverName = "com.mysql.jdbc.Driver";

	@Bean
	@Qualifier("dataSource")
	public DataSource getDataSource() {

		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(driverName);
		dataSource.setUrl(connectionUrl);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		getHostId();
		return dataSource;
	}

	public void getHostId() {

		String computername;
		try {
			computername = InetAddress.getLocalHost().getHostName();
			System.out.println("computer name " + computername);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
}
