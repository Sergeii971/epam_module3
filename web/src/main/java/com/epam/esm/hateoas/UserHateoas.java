package com.epam.esm.hateoas;

import com.epam.esm.controller.OrderController;
import com.epam.esm.dto.UserDto;
import com.epam.esm.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * The type UserHateoas.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
@Component
public class UserHateoas {
    private final ApplicationContext applicationContext;
    private static final String ALL_ORDERS = "all orders";

    @Autowired
    public UserHateoas(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * Add links to users
     *
     * @param users the users
     * @return the list of users with links
     */
    public List<UserDto> addLinksForFindAllUsers(List<UserDto> users) {
        for (UserDto user : users) {
            OrderService orderService = applicationContext.getBean(OrderService.class);

            if (orderService.findAllUserOrders(user.getLogin(), 1, 1).size() > 0) {
                String link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(OrderController.class)
                        .findAllUserOrders(user.getLogin(), 1, 10)).toString();
                user.getLinks().put(ALL_ORDERS, link);
            }
        }
        return users;
    }
}
