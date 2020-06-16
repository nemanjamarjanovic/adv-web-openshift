package rest;

import ejb.DeviceStateDao;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.inject.Inject;

@Path("/measurements")
public class MeasurementResource
{

    @Inject
    DeviceStateDao deviceStateDao;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(final String input)
    {
        deviceStateDao.addNewMeasurement(input);
        return Response.status(Response.Status.CREATED).build();
    }

}
