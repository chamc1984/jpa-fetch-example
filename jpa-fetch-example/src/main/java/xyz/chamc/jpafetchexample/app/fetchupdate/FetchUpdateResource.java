package xyz.chamc.jpafetchexample.app.fetchupdate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import xyz.chamc.jpafetchexample.domain.model.ManydataEntity;
import xyz.chamc.jpafetchexample.domain.repository.ManydataRepository;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.util.Calendar;
import java.util.List;

@Component
@Path("/fetchupdate")
public class FetchUpdateResource {

    @Autowired
    ManydataRepository manydataRepository;

    @GET
    @Path("call")
    public String call() {
        return "called!";
    }

    @GET
    @Path("select")
    @Produces("application/json")
    public ManydataDto select() {
        System.out.println("START select()");

        List<ManydataEntity> list = manydataRepository.findAll();

        System.out.println("select size: " + list.size());

        ManydataDto result = new ManydataDto();
        result.setId(list.get(0).getId());
        result.setUsername(list.get(0).getUsername());
        result.setCheckflg(list.get(0).getCheckflg());

        System.out.println("END select()");
        return result;
    }

    @GET
    @Path("findallsimple")
    @Produces("application/json")
    public ManydataDto findAllSimple() {
        System.out.println("START select()");

//        List<ManydataEntity> list = manydataRepository.findAllSimple();

        System.out.println("START DBCALL");
        Long startdbtime = Calendar.getInstance().getTime().getTime();
        List<ManydataEntity> list = manydataRepository.findAll();
        Long enddbtime = Calendar.getInstance().getTime().getTime();
        System.out.println("END DBCALL. execute time: " + (enddbtime - startdbtime) + " ms.");

        System.out.println("START LOOP");
        Long starttime = Calendar.getInstance().getTime().getTime();
        for(ManydataEntity entity : list) {

        }
        System.out.println("select size: " + list.size());
        Long endtime = Calendar.getInstance().getTime().getTime();
        System.out.println("END LOOP. execute time: " + (endtime - starttime) + " ms.");

        ManydataDto result = new ManydataDto();

        System.out.println("END select()");
        return result;
    }

    @GET
    @Path("fetchselect")
    @Produces("application/json")
    @Transactional
    public ManydataDto fetchselect(@QueryParam("size") Integer size) {
        System.out.println("START select()");

        List<ManydataEntity> list = manydataRepository.findAllByFetch(size);

          ManydataDto result = new ManydataDto();

        System.out.println("END select()");
        return result;
    }

    @GET
    @Path("nofetchselect")
    @Produces("application/json")
    @Transactional
    public ManydataDto nofetchselect() {
        System.out.println("START select()");

        List<ManydataEntity> list = manydataRepository.findAllNoFetch();

        ManydataDto result = new ManydataDto();

        System.out.println("END select()");
        return result;
    }

    @GET
    @Path("count")
    public Long count() {
        return manydataRepository.count();
    }

}