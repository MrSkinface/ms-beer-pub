package ua.mike.pub.services.scheduled;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ua.mike.pub.dto.OrderDto;
import ua.mike.pub.dto.OrderStatus;
import ua.mike.pub.dto.PositionDto;
import ua.mike.pub.services.PubService;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by mike on 08.06.2022 17:39
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class OrderBeerScheduledService {

    private final PubService pub;

    @Scheduled(initialDelay = 3 * 1000, fixedRate = 2 * 1000)
    public void makeRandomOrder() {
        log.debug("Ordering some beer ...");
        final var beers = pub.beerList();
        final var order = OrderDto.builder().lines(new ArrayList<>()).build();
        // random beers count in order
        for (int i = 0; i < random(beers.size() - 1); i++) {
            // random item of beer in order on each iteration
            // random ordered qty (from 1 to 15*4)
            order.getLines().add(PositionDto.builder()
                            .beerId(beers.get(random(beers.size() - 1)).getId())
                            .orderedQty(random(15) * 4)
                    .build());
        }
        pub.createOrder(order);
        log.debug("Made order, total lines: {} total qty: {}", order.getLines().size(),
                order.getLines().stream().mapToInt(PositionDto::getOrderedQty).sum());
    }

    @Scheduled(initialDelay = 4 * 1000, fixedRate = 5 * 1000)
    public void pickupOrders() {
        log.debug("Picking up orders ...");
        // will be canceled random order
        pub.getOrdersByStatus(OrderStatus.ALLOCATED).forEach(order -> {
            if (random(10) > 8) {
                pub.cancelOrder(order.getId());
                log.debug("Canceled order: {}", order.getId());
            } else {
                pub.pickupOrder(order.getId());
                log.debug("Picked up order: {}", order.getId());
            }
        });
    }

    private static int random(final int bound) {
        final var random = new Random();
        final int value = random.nextInt(bound);
        return value == 0 ? 1 : value;
    }
}
