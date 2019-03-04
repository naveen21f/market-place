package com.bondevalue.marketplaceservice.service;

import com.bondevalue.marketplaceservice.exeptions.OrderNotFoundException;
import com.bondevalue.marketplaceservice.domain.Order;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface OrderService {

  Order save(Order order);
  List<Order> findAll();
  Order findById(String orderId) throws OrderNotFoundException;
  List<Order> findAll(Sort sort);
  void deleteById(String orderId);
}
