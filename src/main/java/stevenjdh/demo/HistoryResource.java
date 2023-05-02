/**
 * This file is part of surmiser-app <https://github.com/StevenJDH/quarkus-surmiser-app>.
 * Copyright (C) 2020-2023 Steven Jenkins De Haro.
 *
 * surmiser-app is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * surmiser-app is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with surmiser-app.  If not, see <http://www.gnu.org/licenses/>.
 */

package stevenjdh.demo;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import stevenjdh.demo.models.repositories.HistoryRepository;

@Path("/api/history")
public class HistoryResource {

    @Inject
    HistoryRepository history;
    
    /*
     * We seem limited to one GET per resource, so query parameters aren't 
     * taken into account. Alternatively, you could use @Path("{name}") and
     * @PathParam for /api/history/joe or handle everything in one function 
     * like below.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response history(@QueryParam("name") String name) {
        
        if (name == null || name.isBlank()) {

            return Response.ok(history.listAll()).build();
        }

        if (name.contains(" ")) {
            
            return Response.status(Response.Status.BAD_REQUEST)
                       .entity("Invalid name provided.")
                       .type(MediaType.TEXT_PLAIN_TYPE)
                       .build();
        }

        return Response.ok(history.findByNameIgnoreCase(name)).build();
    }
}