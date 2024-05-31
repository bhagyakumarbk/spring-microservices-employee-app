package com.employee.app.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.employee.app.model.AddressDTO;
import com.employee.app.model.EmployeeDTO;
import com.employee.app.model.EmployeeEntity;
import com.employee.app.openfeign.AddressFeignClient;
import com.employee.app.repository.EmployeeRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import reactor.core.publisher.Mono;

@Service
public class EmployeeService implements EmployeeAPI {

//	@Autowired
	private RestTemplate restTemplate;// from spring 6.x RestTEmplate is deprecated and we need to use FeignClient
	@Autowired
	private EmployeeRepository repo;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private DiscoveryClient client2;

	@Autowired
	private LoadBalancerClient balancerClient;
//	@Autowired
//	private WebClient webClient;
//	@Value("${addressservice.base.url}")
//	private String addressUrl;

	public EmployeeService(@Value("${addressservice.base.url}") String addressUrl, RestTemplateBuilder builder) {

		// TODO Auto-generated constructor stub
		this.restTemplate = builder.rootUri(addressUrl).build();
	}

	@Autowired
	private AddressFeignClient client;

	@Override
	public EmployeeDTO createEmployee(EmployeeDTO employeeEntity) {
		// TODO Auto-generated method stub
		EmployeeEntity emp = mapper.map(employeeEntity, EmployeeEntity.class);
		emp = repo.save(emp);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		employeeEntity.getAddress().setEmployeeId(emp.getId());
		HttpEntity<AddressDTO> requestEntity = new HttpEntity<AddressDTO>(employeeEntity.getAddress(), headers);
		AddressDTO a = client.createAddress(employeeEntity.getAddress());
//				webClient.post().uri("/create").body(Mono.just(employeeEntity.getAddress()), AddressDTO.class)
//				.accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(AddressDTO.class).block();

		// restTemplate.postForObject("/create", requestEntity, AddressDTO.class);
		System.out.println("A" + a);
		EmployeeDTO e = mapper.map(emp, EmployeeDTO.class);
		e.setAddress(a);
		return e;
	}

	//@CircuitBreaker(name = "addressApp", fallbackMethod = "getFallback")
//	@Retry(name="addressApp")
	@RateLimiter(name = "addressApp")
	@Override
	public ResponseEntity<EmployeeDTO> getEmployeeById(int id) {
		// TODO Auto-generated method stub
		EmployeeEntity emp = repo.getById(id);

//		List<ServiceInstance> instances = client2.getInstances("ADDRESS-APP");
//		ServiceInstance instance = instances.get(0);
//		
//		String uri = instance.getUri().toString();

		ServiceInstance instance = balancerClient.choose("ADDRESS-APP");

		String uri = instance.getUri().toString();
		AddressDTO address =
				// client.getAddressByEmployeeId(id);
				// webClient.get().uri("/" +
				// id).retrieve().bodyToMono(AddressDTO.class).block();

				restTemplate.getForObject(uri + "/address-service/address/" + id, AddressDTO.class);
		EmployeeDTO e = mapper.map(emp, EmployeeDTO.class);
		e.setAddress(address);
		return new ResponseEntity<EmployeeDTO>(e, HttpStatusCode.valueOf(200));
	}
	
	public ResponseEntity<EmployeeDTO> getFallback(Exception e) {
		return new ResponseEntity<EmployeeDTO>(new EmployeeDTO(), HttpStatusCode.valueOf(500));
	}

	@Override
	public List<EmployeeDTO> getAllEmployees() {
		// TODO Auto-generated method stub
		List<EmployeeEntity> emp = repo.findAll();
		// mapper.map(emp, List<EmployeeDTO>());
		return null;
	}

	@Override
	public EmployeeDTO updateEmployee(EmployeeDTO employeeEntity) {
		// TODO Auto-generated method stub
		return null;
	}

}
