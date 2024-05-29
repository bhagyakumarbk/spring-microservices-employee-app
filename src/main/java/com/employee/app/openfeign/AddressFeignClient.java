package com.employee.app.openfeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.employee.app.model.AddressDTO;

@FeignClient(name = "ADDRESS-APP")
public interface AddressFeignClient {

	@PostMapping(path = "address-service/address/create")
	AddressDTO createAddress(@RequestBody AddressDTO addressDTO);

	@GetMapping(path = "address-service/address/{id}")
	AddressDTO getAddressByEmployeeId(@PathVariable("id") int id);

}
