package com.bondevalue.marketplaceservice.repository;

import com.bondevalue.marketplaceservice.domain.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {

}
