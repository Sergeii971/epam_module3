package com.epam.esm.hateoas;

import com.epam.esm.controller.OrderController;
import com.epam.esm.controller.UserController;
import com.epam.esm.dto.UserDto;
import com.epam.esm.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserHateoas {
    private final ApplicationContext applicationContext;

    @Autowired
    public UserHateoas(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }


    public CollectionModel<UserDto> addLinksForFindAllUsers(List<UserDto> users) {
        for (UserDto user : users) {
            Link selfLink = WebMvcLinkBuilder.linkTo(UserController.class).slash(user.getLogin()).withSelfRel();
            user.add(selfLink);
            OrderService orderService = applicationContext.getBean(OrderService.class);

            if (orderService.findAllUserOrders(user.getLogin(), 1, 1).size() > 0) {
                Link link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(OrderController.class)
                        .findAllUserOrders(user.getLogin(), 1, 10)).withRel("allOrders");
                user.add(link);
            }
        }
        Link link = WebMvcLinkBuilder.linkTo(UserController.class).withSelfRel();
        return new CollectionModel<>(users, link);
    }
}
