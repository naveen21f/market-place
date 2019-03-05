package com.bondevalue.marketplaceservice.service.impl;

import com.bondevalue.marketplaceservice.domain.MarketMatch;
import com.bondevalue.marketplaceservice.domain.Order;
import com.bondevalue.marketplaceservice.domain.OrderType;
import com.bondevalue.marketplaceservice.exeptions.MatchNotFoundException;
import com.bondevalue.marketplaceservice.service.BuySellMatchService;
import com.bondevalue.marketplaceservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.logging.Logger;

@Service
public class BuySellMatchServiceImpl implements BuySellMatchService {

  private final static Logger LOGGER = Logger.getLogger(BuySellMatchServiceImpl.class.getName());

  @Autowired
  private OrderService orderService;

  @Override
  public Map match() {
    LOGGER.info("SERVICE: Request to match Sell Buy Orders");
    List<Order> orderList = orderService.findAll(Sort.by(new Sort.Order(Sort.Direction.DESC, "price")));
    if (orderList == null || orderList.size() == 0) {
      // No Sell and Buy Orders exist
      throw new MatchNotFoundException("No Buy and Sell Orders Exist");
    }
    List<Order> buyOrders = new ArrayList<>();
    List<Order> sellOrders = new ArrayList<>();
    for (Order order: orderList) {
        if (OrderType.BUY.equals(order.getType())) {
            buyOrders.add(order);
        } else {
          sellOrders.add(order);
        }
    }
    return computeMatch(buyOrders, sellOrders);
  }

  @Override
  public Map computeMatch(List<Order> buyOrders, List<Order> sellOrders) {
    LOGGER.info("SERVICE: request to computeMatch buy & sell orders");
    Map<String, List> matchMap = new HashMap<>(2);
    if (buyOrders == null || buyOrders.size() == 0) { //No Buy Orders Exist
      throw new MatchNotFoundException("No BUY Orders Exist");
    }
    if (sellOrders == null || sellOrders.size() == 0) { //No Sell Orders Exist
      throw new MatchNotFoundException("No SELL Orders Exist");
    }

    if (buyOrders.get(0).getPrice() < sellOrders.get(sellOrders.size() - 1).getPrice()) { //All Sell prices are higher than max buy Price
      matchMap.put("match", Collections.emptyList());
      List<Order> allOrders = new ArrayList<>(buyOrders);
      allOrders.addAll(sellOrders);
      matchMap.put("unmatch", allOrders);
    }
    List<MarketMatch> marketMatches = new ArrayList<>();
    List<Order> unMatchOrder = new ArrayList<>();
    int buyIndex = 0, sellIndex = 0;
    while (buyIndex < buyOrders.size() && sellIndex < sellOrders.size()) {
      Order buyOrder = buyOrders.get(buyIndex);
      for(;sellIndex < sellOrders.size();sellIndex++) {
        Order sellOrder = sellOrders.get(sellIndex);
        if (buyOrder.getPrice() >= sellOrder.getPrice()) {
          marketMatches.add(new MarketMatch(buyOrder, sellOrder));
          buyIndex++;
          break;
        } else {
          unMatchOrder.add(sellOrder);
        }
      }
      sellIndex++;
    }
    if (buyIndex < buyOrders.size()) {
      unMatchOrder.addAll(buyOrders.subList(buyIndex, buyOrders.size()));
    }
    if (sellIndex < sellOrders.size()) {
      unMatchOrder.addAll(sellOrders.subList(sellIndex, sellOrders.size()));
    }

    matchMap.put("match", marketMatches);
    matchMap.put("unmatch", unMatchOrder);
    return matchMap;
  }
}
