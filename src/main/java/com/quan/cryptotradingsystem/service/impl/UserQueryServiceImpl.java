package com.quan.cryptotradingsystem.service.impl;

import com.quan.cryptotradingsystem.service.UserQueryService;
import org.springframework.stereotype.Service;

@Service
public class UserQueryServiceImpl implements UserQueryService {

  @Override
  public long getLoginUserId() {
    return 1;
  }
}
