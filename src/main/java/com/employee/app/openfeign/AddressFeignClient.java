package com.employee.app.openfeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.employee.app.model.AddressDTO;

@FeignClient(name = "address-app", url = "http://localhost:8080/address")
public interface AddressFeignClient {

	@PostMapping(path = "/create")
	AddressDTO createAddress(@RequestBody AddressDTO addressDTO);

	@GetMapping(path = "/{id}")
	AddressDTO getAddressByEmployeeId(@PathVariable("id") int id);

}
