package com.bondevalue.marketplaceservice.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Min;
import java.util.Date;

@Data
@Document
public class Order {

  @Id
  private String id;
  private String name;
  private OrderType type;
  @Min(value = 0, message = "Price should be greater than zero")
  private int price;

  @CreatedDate
  private Date createdAt;
  @LastModifiedDate
  private Date lastModified;
}
