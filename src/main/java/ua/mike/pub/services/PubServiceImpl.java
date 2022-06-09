package ua.mike.pub.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ua.mike.pub.dto.BeerDto;
import ua.mike.pub.dto.OrderDto;
import ua.mike.pub.dto.OrderStatus;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Created by mike on 09.06.2022 10:42
 */
@Service
@RequiredArgsConstructor
public class PubServiceImpl implements PubService {

    private final RestTemplate http;

    @Value("${api.host}")
    private String host;
    @Value("${api.beer.list.uri}")
    private String beerList;
    @Value("${api.order.create.uri}")
    private String createOrder;
    @Value("${api.order.list.uri}")
    private String listOrders;
    @Value("${api.order.get.uri}")
    private String getOrder;
    @Value("${api.order.pickup.uri}")
    private String pickupOrder;
    @Value("${api.order.cancel.uri}")
    private String cancelOrder;

    @Override
    public List<BeerDto> beerList() {
        return List.of(Objects.requireNonNull(http.getForObject(host + beerList, BeerDto[].class)));
    }

    @Override
    public UUID createOrder(OrderDto order) {
        return http.postForObject(host + createOrder, order, UUID.class);
    }

    @Override
    public OrderDto getOrderDetails(UUID id) {
        return http.getForObject(host + getOrder.replace("{id}", id.toString()), OrderDto.class);
    }

    @Override
    public List<OrderDto> getOrdersByStatus(OrderStatus status) {
        return List.of(Objects.requireNonNull(http.getForObject(host + listOrders.replace("{status}", status.toString()), OrderDto[].class)));
    }

    @Override
    public void pickupOrder(UUID id) {
        http.put(host + pickupOrder.replace("{id}", id.toString()), null);
    }

    @Override
    public void cancelOrder(UUID id) {
        http.put(host + cancelOrder.replace("{id}", id.toString()), null);
    }
}
