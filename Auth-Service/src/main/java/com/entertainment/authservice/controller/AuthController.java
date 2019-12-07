package com.entertainment.authservice.controller;

import com.entertainment.authservice.model.AuthEntity;
import com.entertainment.authservice.service.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

@RestController
@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthController {
    private final AuthServiceImpl authService;

    @Autowired
    public AuthController(AuthServiceImpl authService) {
        this.authService = authService;
    }

    @POST
    @Path("/")
    public Response addAuth(AuthEntity authEntity) {
        try {
            authService.create(authEntity);
        } catch (Exception e) {
            return Response.status(Response.Status.CONFLICT).entity(e.getMessage()).build();
        }
        return Response.status(Response.Status.CREATED).entity(authEntity).build();
    }
}
