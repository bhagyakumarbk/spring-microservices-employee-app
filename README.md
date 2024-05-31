# Spring Microservices


* Spring Microservices is an architecture which is exposed as loosely coupled services.

* These applications can we deployed, managed and developed independently.

* It supports Polyglot architecture which is one microservice can be built on java and another on python.

* We can use different versions of same programming language.

* In case of Monolithic application all the code is present in a single unit and deployed all at once.

* Scalability is an issue in Monolithic application.

* If we have multiple modules in an application and there is a requirement where we need to update in only one module then after 
  the changes we need to deploy whole application and test the entire flow. The testing will be an issue as we need to test from the scratch again.

* If one module stops working then entire application will be down.

* These modules cannot be deployed independently.

* When a client requests to a server internally the server will have a thread pool and threads inside it will help to 
  process the request and giving back the response.
* The thread will be idle and will not take any requests until it receives the response. Once the thread receives the and 
  given to client then the thread is deallocated. And then it will take another request for processing.
* If we have limited threads and we get bunch of requests, only few requests will process with the available threads and rest of them should wait until 
  the threads is deallocated. The threads will remain idle until it gets response from server which is blocking other requests from processing.
* This is where load balancer comes into pictures and we scale up the process and route the requests.
* But still we will be seeing the blocking mechanism as we use RestTemplate which is an synchronous call.
* To overcome this we can use WebClient which is a non-blocking which means that it doesn't block threads while waiting for a response from the server
  and is part of Spring WebFlux module and it allows to build  asynchronous  calls.

* Spring cloud introduced Feign client instead of using RestTemplate. 
  It synchronous, blocking client by default, although it can be used asynchronously with additional configurations.
* It follows a declarative approach where we can define interfaces with annotations(@FEignClient, @PostMapping, @GetMapping etc)
  to specify the desired HTTP endpoints.
* Uses traditional callback-based mechanisms for handling asynchronous operations.
* We can easily integrate feign client with service discovery and load balancer.

Traditional Load Balancer

* Service A is client and Service B is deployed on multiple instances/server and has a load balancer which is deployed on differenct server
* Load Balancer helps to distribute the load across the instances whenever we get multiple requests.
* Service A doesn't make call to service B directly instead it hits load balancer which routes to the registered services.
* The traditional load balancer cannot be used in microservices that is because it converts one remote call to two remote calls
  i.e., Service A hits load balancer and again load balancer will route to service B
* If the load balancer is failed to start then client app is not aware how to connect to the services.
* We need to manually configure server side applications to register for load balancer.

Discovery Service
================

* Whenever we configure Discovery service in an application it will manage all the host and port of that application and registers it
* Whenever a client makes a call to server side discovery service helps providing the host details of that particular app

For client side service discovery configuration we need to add this jar implementation 'org.springframework.cloud:spring-cloud-starter-netflix-eureka-client

* When the services starts they know where eureka server is usually runs and it is 8671. So they use the default localhost:8671/eureka to register themselves into the server

What if the the eureka server ports is updated or changed?
*Now we need to make the services route the eureka server to updated ports or host

eureka.client.service-url.defaultZone=http://localhost:5000/eureka/ -> this property is used when we have eureka server running on different port and we have no zones available


* Open Feign automatically handles load balancing 
* THe load balancer has been fetched as a transitive dependency as part of Eureka client dependency


Circuit Breaker
=================

In microservice architecture its common to make a rest call from one service to another and the server application might be down or it will not respond
When a microservice encounters an error, it will have an impact on other microservices that call it, and will also have a domino effect
in such cases we can use Circuit breaker pattern

The concept of a circuit breaker is to prevent calls to microservice when it’s known the call may fail or time out. This is done so that clients don’t waste their valuable resources handling requests that are likely to fail. 

when we have circuit and it is in closed loop and when circuit is in closed loop then it is working 

There are states in the circuit closed, open and half_open

CLOSED - when circuit breaker is closed all the requests are passed
if we make a call to other service which is down and those requests are failing. Here we define the threshold to monitor how many requests got failed with provided threshold then circuit will change the status from closed to open

OPEN - When cicuit is OPEN no requests are allowed and can define wait duration for CB to be in the open state. and it will change to HALF_OPEN

HALF_OPEN - it will allow few requests and check if it is working or not. Once the requests are good then it will change it to CLOSED state or else it will update to OPEN state


if we have business exception that particular exception should not consider as an failure then we can define those exception in the CB and it will consider as success flow


RETRY - The Retry pattern enables an application to retry an operation in the expectation that it'll succeed.
When an error occurs, attempt to re-execute the code block until it succeeds or reaches the maximum number of attempts

Rate-limiter - They manage the rate of traffic from clients or services, limiting the number of requests allowed within a specified period. If the request count exceeds the set limit defined by the rate limiter, all the excess calls are blocked



