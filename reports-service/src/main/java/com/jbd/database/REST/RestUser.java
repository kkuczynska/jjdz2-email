package com.jbd.database.REST;

import com.jbd.database.ActivityReport;
import com.jbd.database.ManageDB;
import com.jbd.database.Report;
import com.jbd.database.User;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Stateless
@Path("/users")
public class RestUser {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(RestUser.class);
    private static final Marker MARKER = MarkerFactory.getMarker("ManageDB");

    @Inject
    ManageDB manageDB;

    @Inject
    ActivityReport activityReport;

    @Context
    UriInfo uriInfo;


    @GET
    @Path("/name")
    @Produces(MediaType.APPLICATION_JSON)
    public Response users() {
        List<Report> reportList;
        reportList = activityReport.generateReport();
        if(reportList.isEmpty()){
            LOGGER.info(MARKER,"The report list is empty!");
            return Response.noContent().build();
        }
        else {
            LOGGER.info(MARKER, "Created report List... Ready to Send via API");
            return Response.ok(reportList).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(User user){
        if (user != null) {
            manageDB.saveUser(user);
            LOGGER.info("Created User from JSON and saved to DB : " + user);
            return Response
                    .created(uriInfo
                            .getAbsolutePathBuilder()
                            .build())
                    .build();
        }
        else {
            LOGGER.info("Received JSON is empty! no create...");
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
