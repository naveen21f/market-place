package com.bondevalue.marketplaceservice.domain;

import lombok.Data;

@Data
public class MarketMatch {

  public MarketMatch(Order buyer, Order seller) {
    this.buyer = buyer;
    this.seller = seller;
  }

  private Order buyer;
  private Order seller;

}
