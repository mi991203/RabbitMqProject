package com.shao.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class OrderCreateVO {
    private Integer accountId;
    private String address;
    private Integer productId;
}
