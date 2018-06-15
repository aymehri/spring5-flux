package com.aymen.kadi.repository;

import com.aymen.kadi.model.Product;
import org.reactivestreams.Publisher;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.util.concurrent.Flow;

public interface ProductRepository extends ReactiveMongoRepository<Product, String> {

    Flux<Product> findByNameOrderByPrice(Publisher<String> name);
}
