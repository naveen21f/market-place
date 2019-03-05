package com.bondevalue.marketplaceservice.service.impl;

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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

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

  @Test
  public void computeMatch() {



  }
}
