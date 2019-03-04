package com.bondevalue.marketplaceservice.common;

import lombok.Data;

import java.util.Date;

@Data
public class ApiResponse<T, R> {
  private T data;
  private R errors;
  private String status;
  private int statusCode;
  private String reason;
  private Date timeStamp = new Date();
  public ApiResponse() {

  }

  public ApiResponse(T data, R errors, String status, int statusCode, String reason) {
    this.data = data;
    this.errors = errors;
    this.status = status;
    this.statusCode = statusCode;
    this.reason = reason;
  }
}
