package com.jbd.REST;

import com.jbd.authorization.SessionData;
import com.jbd.database.ManageUser;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.time.LocalDateTime;
import java.util.List;

@Stateless
@Path("/users")

public class restUsers {

    @Inject
    ManageUser manageUser;

    @Path("/name")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello(@PathParam("user_name") String username) {
        return username + LocalDateTime.now();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<SessionData> users(){
        List<SessionData> userList = manageUser.searchForAll();
        return userList;
    }

}
