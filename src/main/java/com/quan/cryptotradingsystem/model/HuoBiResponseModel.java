package com.quan.cryptotradingsystem.model;

import java.util.List;

public class HuoBiResponseModel {
  private List<HuoBiPriceModel> data;
  private String status;
  private long ts;

  public List<HuoBiPriceModel> getData() {
    return data;
  }

  public void setData(List<HuoBiPriceModel> data) {
    this.data = data;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public long getTs() {
    return ts;
  }

  public void setTs(long ts) {
    this.ts = ts;
  }
}
