package com.epam.esm.controller;

import com.epam.esm.dto.OrderPriceDateDto;
import com.epam.esm.dto.TagDto;
import com.epam.esm.dto.UserOrderDto;
import com.epam.esm.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.QueryParam;
import java.util.List;

@RestController
@RequestMapping("/v1/orders")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * Add order
     *
     * @param login the user login
     * @param certificateId the certificate id
     */
    @RequestMapping(value = "/users/{login}/certificates/{certificateId}", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void addOrder(@PathVariable(value = "login") String login, @PathVariable(value = "certificateId") long certificateId) {
        orderService.add(login, certificateId);
    }

    /**
     * Find all orders.
     *
     * @return the list of found orders
     */
    @RequestMapping(value = "/users/{login}", method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<UserOrderDto> findAllUserOrders(@PathVariable("login") String login,
                                                @QueryParam("pageNumber") Integer pageNumber,
                                                @QueryParam("size") Integer size) {
        return orderService.findAllUserOrders(login, pageNumber, size);
    }

    @RequestMapping(value = "/tags", method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public TagDto findMostPopularHighCostTag() {
        return orderService.findMostPopularHighCostTag();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public OrderPriceDateDto findOrderPriceDate(@PathVariable("id") long orderId) {
        return orderService.findOrderPriceDate(orderId);
    }
}
