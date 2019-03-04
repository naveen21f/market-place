package com.bondevalue.marketplaceservice.dto;

import com.bondevalue.marketplaceservice.domain.OrderType;
import lombok.Data;

import java.util.Date;

@Data
public class OrderDto {

  private String id;
  private String name;
  private OrderType type;
  private int price;
  private Date createdAt;

}
