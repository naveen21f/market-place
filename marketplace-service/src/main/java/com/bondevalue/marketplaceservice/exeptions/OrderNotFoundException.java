package com.bondevalue.marketplaceservice.exeptions;

/**
 * Order Not Found Exception, raises when searching for Order which not exist
 */
public class OrderNotFoundException extends Exception{
  public OrderNotFoundException(String s){
    super(s);
  }
}
