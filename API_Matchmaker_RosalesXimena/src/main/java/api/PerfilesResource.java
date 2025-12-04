/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package api;

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

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of PerfilesResource
     */
    public PerfilesResource() {
    }

    /**
     * Retrieves representation of an instance of api.PerfilesResource
     * @return an instance of dtos.ProfileDTO
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ProfileDTO getJson(@QueryParam("edad") int edad) {
        return new ProfileDTO("Alexis", "Quackity", "Maldonado", "2000-12-28",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQA4eQ4n_UFVHhKcqlRjgFWkdK82sRsTa4amg&s",
                "quackity@gmail.com", "Masculino", "México", "(54)666-7689",
                "De las Espadas", 24);
    }

    /**
     * PUT method for updating or creating an instance of PerfilesResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void addProfile(ProfileDTO content) {
        logger.info(content.toString());
    }
}
