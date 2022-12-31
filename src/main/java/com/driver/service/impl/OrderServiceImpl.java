package com.driver.service.impl;

import com.driver.io.entity.OrderEntity;
import com.driver.io.repository.OrderRepository;
import com.driver.service.OrderService;
import com.driver.shared.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;
    @Override
    public OrderDto createOrder(OrderDto order) {

        OrderEntity orderEntity = OrderEntity.builder().id(order.getId())
                .orderId(order.getOrderId())
                .cost(order.getCost())
                .items(order.getItems())
                .status(order.isStatus())
                .userId(order.getUserId())
                .build();

        orderRepository.save(orderEntity);
        return order;
    }

    @Override
    public OrderDto getOrderById(String orderId) throws Exception {
        OrderEntity orderEntity = orderRepository.findByOrderId(orderId);
        OrderDto orderDto = OrderDto.builder().id(orderEntity.getId())
                .orderId(orderEntity.getOrderId())
                .cost(orderEntity.getCost())
                .items(orderEntity.getItems())
                .userId(orderEntity.getUserId())
                .status(orderEntity.isStatus()).build();
        return orderDto;
    }

    @Override
    public OrderDto updateOrderDetails(String orderId, OrderDto order) throws Exception {
        OrderEntity orderEntity = orderRepository.findByOrderId(orderId);
         orderEntity = OrderEntity.builder().id(order.getId())
                .orderId(order.getOrderId())
                .cost(order.getCost())
                .items(order.getItems())
                .status(order.isStatus())
                .userId(order.getUserId())
                .build();
        orderRepository.save(orderEntity);
        return order;

    }

    @Override
    public void deleteOrder(String orderId) throws Exception {
        OrderEntity orderEntity = orderRepository.findByOrderId(orderId);
        orderRepository.delete(orderEntity);
    }

    @Override
    public List<OrderDto> getOrders() {
        List<OrderEntity> orderList = (List<OrderEntity>) orderRepository.findAll();
        List<OrderDto> dtoList = new ArrayList<>();

        for(OrderEntity x: orderList) {
            OrderDto orderDto = OrderDto.builder().id(x.getId())
                    .orderId(x.getOrderId())
                    .cost(x.getCost())
                    .items(x.getItems())
                    .userId(x.getUserId())
                    .status(x.isStatus()).build();

            dtoList.add(orderDto);
        }
        return dtoList;
    }
}