package org.acme.controller;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.entity.PalestraEntity;
import org.acme.service.PalestraService;

import java.util.List;
import java.util.UUID;

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

    @GET
    @Path("/{id}")
    public Response createPalestra(@PathParam("id")UUID palestraId) {
        return Response.ok(palestraService.findById(palestraId)).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updatePalestra(@PathParam("id")UUID palestraId,PalestraEntity palestraEntity) {
        return Response.ok(palestraService.updatePalestra(palestraId, palestraEntity)).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteById(@PathParam("id")UUID palestraId) {
        palestraService.deleteById(palestraId);
        return Response.noContent().build();
    }

    @GET
    @Path("/buscar")
    @Produces(MediaType.APPLICATION_JSON)
    public List<PalestraEntity> buscarComFiltros(
            @QueryParam("titulo") String titulo,
            @QueryParam("nomeAutor") String nomeAutor,
            @QueryParam("email") String email) {
        return palestraService.buscarComFiltros(titulo, nomeAutor, email);
    }
}
