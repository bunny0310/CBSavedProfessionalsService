package com.cb.savedProfessionalsService.api;

import com.cb.savedProfessionalsService.cache.LRUCache;
import com.cb.savedProfessionalsService.database.models.SavedProfessional;
import com.cb.savedProfessionalsService.services.SavedProfessionalsService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/api/v1/savedProfessionals")
@Consumes(MediaType.APPLICATION_JSON + "; charset=utf-8")
@Produces(MediaType.APPLICATION_JSON)
public class SavedProfessionalsResource {
    private LRUCache<String, String> cache = new LRUCache<String, String>(10);
    private SavedProfessionalsService savedProfessionalsService;

    public SavedProfessionalsResource(SavedProfessionalsService savedProfessionalsService) {
        this.savedProfessionalsService = savedProfessionalsService;
    }

    @GET()
    @Path("/list/{userId}")
    public Response getSavedProfessionals(@PathParam("userId") final int userId) {
        List<SavedProfessional> SavedProfessionals = this.savedProfessionalsService.getSavedProfessionals(userId);
        return Response.status(200).entity(SavedProfessionals).build();
    }

    @POST()
    @Path("/insert")
    public Response insertSavedProfessionals(List<SavedProfessional> savedProfessionals) {
        for(SavedProfessional savedProfessional : savedProfessionals) {
            this.savedProfessionalsService.insertSavedProfessional(savedProfessional);
        }
        return Response.status(200).entity("professionals saved successfully").build();
    }
}