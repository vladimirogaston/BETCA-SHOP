package ar.ungs.shop.bussines_controllers;

import ar.ungs.shop.dtos.AuthUserDto;
import ar.ungs.shop.dtos.UserDto;

public interface UsersController {

    String auth(AuthUserDto credentials);

    UserDto save(UserDto user);
}
