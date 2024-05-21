package com.employee.app.config;

import java.io.Serializable;
import java.util.Random;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.stereotype.Component;

@Component
public class EmployeeIdGenerator {

	public int generate() {
		// Your custom logic to generate unique user IDs
		int userId = generateRandomString(6); // For example, generate a 6-character random string
		return userId;
	}

	private int generateRandomString(int length) {
		// Define characters to be used in the random string
		String characters = "0123456789";
		StringBuilder stringBuilder = new StringBuilder();

		// Generate random string using characters defined above
		for (int i = 0; i < length; i++) {
			int randomIndex = new Random().nextInt(characters.length());
			stringBuilder.append(characters.charAt(randomIndex));
		}

		return Integer.valueOf(stringBuilder.toString());
	}
}
