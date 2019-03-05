package com.bondevalue.marketplaceservice.service.impl;

import com.bondevalue.marketplaceservice.domain.MarketMatch;
import com.bondevalue.marketplaceservice.domain.Order;
import com.bondevalue.marketplaceservice.domain.OrderType;
import com.bondevalue.marketplaceservice.exeptions.MatchNotFoundException;
import com.bondevalue.marketplaceservice.service.BuySellMatchService;
import com.bondevalue.marketplaceservice.service.OrderService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
public class BuySellMatchServiceImplTest {
  @TestConfiguration
  static class EmployeeServiceImplTestContextConfiguration {

    @Bean
    public BuySellMatchService buySellMatchService() {
      return new BuySellMatchServiceImpl();
    }
  }

  @MockBean
  private OrderService orderService;

  @Autowired
  private BuySellMatchService buySellMatchService;

  @Before
  public void setUp() throws Exception {

  }

  @After
  public void tearDown() throws Exception {
  }

  @Test(expected = MatchNotFoundException.class)
  public void match_NoOrdersExist() {
    Mockito.when(orderService.findAll(new Sort(Sort.Direction.DESC, "price")))
      .thenReturn(Collections.emptyList());
    buySellMatchService.match();
  }

  @Test(expected = MatchNotFoundException.class)
  public void computeMatch_NoSellOrdersExist() {
    Order order = new Order();
    order.setType(OrderType.BUY);
    List<Order>  buyOrders = new ArrayList<>(Arrays.asList(order));
    buySellMatchService.computeMatch(buyOrders, Collections.emptyList());

  }

  @Test(expected = MatchNotFoundException.class)
  public void computeMatch_NoBuyOrdersExist() {
    Order order = new Order();
    order.setType(OrderType.SELL);
    List<Order> sellOrders = new ArrayList<>(Arrays.asList(order));
    buySellMatchService.computeMatch(Collections.emptyList(), sellOrders);
  }

  @Test
  public void computeMatch_match1Success() {
    Order buyorder = new Order();
    buyorder.setType(OrderType.BUY);
    buyorder.setName("buyer");
    buyorder.setPrice(5);

    Order sellOrder = new Order();
    sellOrder.setType(OrderType.SELL);
    sellOrder.setName("seller");
    sellOrder.setPrice(5);
    Map<String, List<MarketMatch>> matchMap = buySellMatchService.computeMatch(Arrays.asList(buyorder), Arrays.asList(sellOrder));
    assertNotNull(matchMap);
    assertNotNull(matchMap.get("match"));
    assertTrue(matchMap.get("unmatch").size() == 0);
    assertThat("buyer", is(matchMap.get("match").get(0).getBuyer().getName()));
    assertThat("seller", is(matchMap.get("match").get(0).getSeller().getName()));

  }

  @Test
  public void computeMatch_NoMatchSuccess() {
    Order buyorder = new Order();
    buyorder.setType(OrderType.BUY);
    buyorder.setName("buyer");
    buyorder.setPrice(5);

    Order sellOrder = new Order();
    sellOrder.setType(OrderType.SELL);
    sellOrder.setName("seller");
    sellOrder.setPrice(15);
    Map<String, List<Order>> matchMap = buySellMatchService.computeMatch(Arrays.asList(buyorder), Arrays.asList(sellOrder));
    assertNotNull(matchMap);
    assertNotNull(matchMap.get("match"));
    assertEquals(0, matchMap.get("match").size());
    assertEquals(2, matchMap.get("unmatch").size());

  }

}
