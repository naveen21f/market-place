package com.bondevalue.marketplaceservice.service.impl;

import com.bondevalue.marketplaceservice.exeptions.OrderNotFoundException;
import com.bondevalue.marketplaceservice.domain.Order;
import com.bondevalue.marketplaceservice.repository.OrderRepository;
import com.bondevalue.marketplaceservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.logging.Logger;

@Service
public class OrderServiceImpl implements OrderService {

  private final static Logger LOGGER = Logger.getLogger(OrderServiceImpl.class.getName());

  @Autowired
  private OrderRepository orderRepository;

  @Override
  public Order save(Order order) {
    LOGGER.info("SERVICE: request to save Order");
    return orderRepository.save(order);
  }

  @Override
  public List<Order> findAll() {
    LOGGER.info("SERVICE: request to find all Orders");

    return orderRepository.findAll();
  }

  @Override
  public Order findById(String orderId) throws OrderNotFoundException {
    LOGGER.info("SERVICE: request to find Order by Id: "+ orderId);
    return orderRepository.findById(orderId)
                          .orElseThrow(() -> new OrderNotFoundException("Order Not exist with id: "+ orderId));
  }

  @Override
  public List<Order> findAll(Sort sort) {
    LOGGER.info("SERVICE: request to find All Order Sorted ");
    return orderRepository.findAll(sort);
  }

  @Override
  public void deleteById(String orderId){
    LOGGER.info("SERVICE: request to delete Order by Id");
    Assert.notNull(orderId, "OrderId should not be null");

    orderRepository.deleteById(orderId);
  }


}
