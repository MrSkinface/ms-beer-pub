package ua.mike.pub.services;

import ua.mike.pub.dto.BeerDto;
import ua.mike.pub.dto.OrderDto;
import ua.mike.pub.dto.OrderStatus;

import java.util.List;
import java.util.UUID;

/**
 * Created by mike on 09.06.2022 10:35
 */
public interface PubService {

    List<BeerDto> beerList();

    UUID createOrder(OrderDto order);

    OrderDto getOrderDetails(UUID id);

    List<OrderDto> getOrdersByStatus(OrderStatus status);

    void pickupOrder(UUID id);

    void cancelOrder(UUID id);
}
