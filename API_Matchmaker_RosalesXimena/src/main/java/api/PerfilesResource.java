/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package api;

import bo.PerfilesBO;
import dtos.ProfileDTO;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.logging.Logger;

/**
 * REST Web Service
 *
 * @author Ximena
 */
@Path("perfiles")
@RequestScoped
public class PerfilesResource {
    private static final Logger logger = Logger.getLogger(PerfilesResource.class.getName());
    private PerfilesBO bo = new PerfilesBO();

    public PerfilesResource() {
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addProfile(ProfileDTO perfil) throws Exception {
        bo.agregar(perfil);
        return Response.ok("Perfil agregado").build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPerfiles(
            @QueryParam("genero") String genero,
            @QueryParam("pais") String pais,
            @QueryParam("edad") Integer edad) throws Exception {
        
        ProfileDTO encontrado = bo.buscarAleatorio(genero, pais, edad);
        if (encontrado == null) {
            return Response.status(404).entity("No se encontraron perfiles").build();
        }
        
        return Response.ok(encontrado).build();
        
    }
}
