package rest;

import com.google.gson.Gson;
import ejb.DeviceStateDao;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/devicestates")
public class DeviceStateResource
{

    @Inject
    DeviceStateDao deviceStateDao;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll(
            @QueryParam("limit") Integer limit,
            @QueryParam("dateFrom") String dateFrom,
            @QueryParam("dateTo") String dateTo,
            @QueryParam("last") Boolean last,
            @QueryParam("lastId") Long lastId) throws ParseException
    {
        List deviceStates;
        if (last != null && last)
        {
            deviceStates = deviceStateDao.findLastDeviceState();
        }
        else
        {
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
            limit = (limit == null || limit <= 0) ? Constant.MAX_RESULTS : limit;
            Date dateTop = (dateTo == null) ? new Date() : sdf.parse(dateTo);
            Date dateFromp = (dateFrom == null) ? new Date(System.currentTimeMillis() - Constant.DAY) : sdf.parse(dateFrom);

            deviceStates = deviceStateDao.findtDeviceStatesBetweenTimes(dateFromp, dateTop, limit);
        }
        Gson gson = new Gson();
        String result = gson.toJson(deviceStates.toArray());
        return Response.status(Response.Status.OK).entity(result).build();
    }

}
