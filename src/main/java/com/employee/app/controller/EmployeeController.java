package com.employee.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.employee.app.model.EmployeeDTO;
import com.employee.app.service.EmployeeAPI;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeAPI api;

	@GetMapping(path = "/employee/{id}")
	public EmployeeDTO getEmployee(@PathVariable("id") int id) {
		return api.getEmployeeById(id);
	}

	@PostMapping(path = "/employee/create")
	public EmployeeDTO create(@RequestBody EmployeeDTO employeeEntity) {
		return api.createEmployee(employeeEntity);
	}
}
