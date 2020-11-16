package com.cb.savedProfessionalsService.api;

import com.cb.savedProfessionalsService.cache.LRUCache;
import com.cb.savedProfessionalsService.database.models.SavedProfessional;
import com.cb.savedProfessionalsService.services.SavedProfessionalsService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Path("/api/v1/savedProfessionals")
@Consumes(MediaType.APPLICATION_JSON + "; charset=utf-8")
@Produces(MediaType.APPLICATION_JSON)
public class SavedProfessionalsResource {
    private LRUCache<Integer, Set<SavedProfessional>> cache = new LRUCache<Integer, Set<SavedProfessional>>(10);
    private SavedProfessionalsService savedProfessionalsService;

    public SavedProfessionalsResource(SavedProfessionalsService savedProfessionalsService) {
        this.savedProfessionalsService = savedProfessionalsService;
    }

    @GET()
    @Path("/list/{userId}")
    public Response getSavedProfessionals(@PathParam("userId") final int userId) {
        Set<SavedProfessional> SavedProfessionals;
        if(cache.contains(userId)) {
            SavedProfessionals = this.cache.get(userId);
        }
        else {
            SavedProfessionals = this.savedProfessionalsService.getSavedProfessionals(userId);
            cache.put(userId, SavedProfessionals);
        }
        return Response.status(200).entity(SavedProfessionals).build();
    }

    @POST()
    @Path("/insert")
    public Response insertSavedProfessionals(List<SavedProfessional> savedProfessionals) {
        for(SavedProfessional savedProfessional : savedProfessionals) {
            //get the user id of the record we're trying to insert
            int userId = savedProfessional.getUserId();

            //declare a set of SavedProfessionals
            Set<SavedProfessional> setSP;

            //if cache contains the userId, then retrieve the set from the cache
            if(cache.contains(userId)) {
                setSP = cache.get(userId);
            }
            //otherwise get the set from the database
            else {
                setSP = savedProfessionalsService.getSavedProfessionals(userId);
            }

            //if the set doesn't contain the professional's id, then insert it into the database
            if(!setSP.contains(savedProfessional.getProfessionalId())) {
                this.savedProfessionalsService.insertSavedProfessional(savedProfessional);
                if(!cache.contains(userId)) {
                    cache.put(userId, new HashSet<SavedProfessional>());
                }
                cache.get(userId).add(savedProfessional);
            }
        }
        return Response.status(200).entity("professionals saved successfully").build();
    }
}