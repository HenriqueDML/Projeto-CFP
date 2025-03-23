package org.acme.controller;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.entity.PalestraEntity;
import org.acme.service.PalestraService;

@Path("/palestras")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PalestraController  {

    private final PalestraService palestraService;

    @Inject
    public PalestraController(PalestraService palestraService) {
        this.palestraService = palestraService;
    }


    @POST
    @Transactional
    public Response createPalestra(PalestraEntity palestraEntity) {
        return Response.ok(palestraService.createPalestra(palestraEntity)).build();
    }

    @GET
    public Response findAll(@QueryParam("page") @DefaultValue( "0") Integer page,
                                 @QueryParam("pageSize") @DefaultValue ("10") Integer pageSize) {
        var palestras = palestraService.findPalestra(page, pageSize);
        return Response.ok(palestras).build();
    }
}
