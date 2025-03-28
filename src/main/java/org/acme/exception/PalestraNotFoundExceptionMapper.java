package org.acme.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.nio.file.attribute.UserPrincipalNotFoundException;

@Provider
public class PalestraNotFoundExceptionMapper implements ExceptionMapper<UserPrincipalNotFoundException> {

    @Override
    public Response toResponse(UserPrincipalNotFoundException exception) {
        return Response.status(Response.Status.NOT_FOUND.getStatusCode(), "User Not Found").build();
    }
}
