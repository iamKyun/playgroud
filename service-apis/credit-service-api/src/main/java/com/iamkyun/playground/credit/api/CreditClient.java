package com.iamkyun.playground.credit.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient
public interface CreditClient {
    /**
     * @param amount 数量
     * @param fromService 所属服务
     * @param source 来源
     * @return
     */
    @PostMapping("/credits")
    Boolean increaseCredit(Integer amount, String fromService, String source);
}
