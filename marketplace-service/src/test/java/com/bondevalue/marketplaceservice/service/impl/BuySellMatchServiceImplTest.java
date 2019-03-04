package com.bondevalue.marketplaceservice.service.impl;

import com.bondevalue.marketplaceservice.service.BuySellMatchService;
import com.bondevalue.marketplaceservice.service.OrderService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

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

  @Test
  public void match() {
  }

  @Test
  public void computeMatch() {
  }
}
