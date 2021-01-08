package ar.ungs.shop.rest_controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.ungs.shop.bussines_controllers.ShoppingCartControllerImpl;
import ar.ungs.shop.dtos.ShoppingCartDto;

@RestController
@RequestMapping(value = ShoppingCartResource.SHOPPING_CART)
public class ShoppingCartResource {

	public static final String SHOPPING_CART = "/shopping";
	
	public ShoppingCartControllerImpl controller;
	
	public ShoppingCartResource() {}
	
	@PutMapping
	public ShoppingCartDto save(@Valid @RequestBody ShoppingCartDto shoppingCart) {
		return controller.save(shoppingCart);
	}

	@Autowired
	public void setController(ShoppingCartControllerImpl controller) {
		this.controller = controller;
	}
}