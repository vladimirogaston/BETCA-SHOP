package ar.ungs.shop.bussines_controllers;

import ar.ungs.shop.dtos.AuthUserDto;

public interface UsersController {

    public String getJwtToken(AuthUserDto credentials);
}
