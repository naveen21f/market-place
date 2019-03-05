package com.bondevalue.marketplaceservice.service;

import com.bondevalue.marketplaceservice.domain.Order;

import java.util.List;
import java.util.Map;

public interface BuySellMatchService {
  Map match();
  Map computeMatch(List<Order> buyOrders, List<Order> sellOrders);
}
