package com.aymen.kadi;

import com.aymen.kadi.model.Product;
import com.aymen.kadi.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class Spring5FluxApplication {

	public static void main(String[] args) {
		SpringApplication.run(Spring5FluxApplication.class, args);
	}

	@Bean
	CommandLineRunner init(ReactiveMongoOperations operations, ProductRepository repository) {
		return args -> {
			Flux<Product> productFlux = Flux.just(
					new Product(null, "Big Latte", 2.99),
					new Product(null, "Big Decaf", 2.49),
					new Product(null, "Green Tea", 2.99))
					.flatMap(repository::save);


			productFlux
					.thenMany(repository.findAll())
					.subscribe(System.out::println);

			/*
			operations.collectionExists(Product.class)
					.flatMap(exists -> exists ? operations.dropCollection(Product.class): Mono.just(exists))
					.thenMany(v -> operations.createCollection(Product.class))
					.thenMany(productFlux)
					.thenMany(repository.findAll())
					.subscribe(System.out::println);*/
		};
	}
}
