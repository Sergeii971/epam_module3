package com.epam.esm.controller;

import com.epam.esm.dto.OrderPriceDateDto;
import com.epam.esm.dto.TagDto;
import com.epam.esm.dto.UserOrderDto;
import com.epam.esm.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.QueryParam;
import java.util.List;

/**
 * The type OrderController.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
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
     * @param id the user id
     * @param certificateId the certificate id
     */
    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "/users/{userId}/certificates/{certificateId}", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public UserOrderDto addOrder(@PathVariable(value = "userId") long id, @PathVariable(value = "certificateId") long certificateId) {
        return orderService.add(id, certificateId);
    }

    /**
     * Find all orders.
     *
     * @return the list of found orders
     */
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @RequestMapping(value = "/users/{userId}", method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<UserOrderDto> findAllUserOrders(@PathVariable("userId") long id,
                                                @QueryParam("pageNumber") Integer pageNumber,
                                                @QueryParam("size") Integer size) {
        return orderService.findAllUserOrders(id, pageNumber, size);
    }

    /**
     * find the most popular and high cost tag
     *
     * @return the list of found tags
     */
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @RequestMapping(value = "/users/{userId}/tags/popular", method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public  List<TagDto> findMostUserPopularHighCostTag(@PathVariable(value = "userId") long id) {
        return orderService.findMostPopularHighCostTag(id);
    }

    /**
     * find order price and date
     *
     * @param orderId the order id
     * @return orderPrice dateDto the order price date dto
     */
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @RequestMapping(value = "/{id}",method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public OrderPriceDateDto findOrderPriceDate(@PathVariable("id") long orderId) {
        return orderService.findOrderPriceDate(orderId);
    }
}
