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
