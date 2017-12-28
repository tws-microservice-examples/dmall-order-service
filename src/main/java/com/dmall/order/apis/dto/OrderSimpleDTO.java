package com.dmall.order.apis.dto;

import com.dmall.order.domain.model.query.CustomerContact;
import com.dmall.order.domain.model.query.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.EnumType.STRING;

public class OrderSimpleDTO {
    private Long id;

    private OrderStatus orderStatus = OrderStatus.NOT_COMPLETED;

    private List<Long> orderItemIds = new ArrayList<>();
}
