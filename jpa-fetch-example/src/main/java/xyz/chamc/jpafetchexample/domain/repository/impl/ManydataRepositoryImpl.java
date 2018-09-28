package xyz.chamc.jpafetchexample.domain.repository.impl;

import org.eclipse.persistence.jpa.JpaEntityManager;
import org.eclipse.persistence.queries.CursoredStream;
import org.eclipse.persistence.queries.DataReadQuery;
import org.eclipse.persistence.sessions.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.stereotype.Repository;
import xyz.chamc.jpafetchexample.domain.model.ManydataEntity;
import xyz.chamc.jpafetchexample.domain.repository.ManydataRepositoryCustom;

import javax.persistence.EntityManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Repository
public class ManydataRepositoryImpl implements ManydataRepositoryCustom {

    @Autowired
    private JpaContext context;

    public List<ManydataEntity> findAllSimple() {

        final EntityManager em = context.getEntityManagerByManagedType(ManydataEntity.class);
        JpaEntityManager eclipseLinkEm = em.unwrap(JpaEntityManager.class);
        final String sql = "SELECT * FROM MANYDATA;";
        DataReadQuery q = new DataReadQuery(sql);

        Session session = eclipseLinkEm.getActiveSession();
        ResultSet rs = (ResultSet) session.executeQuery(q);

        System.out.println("START LOOP");
        Long starttime = Calendar.getInstance().getTime().getTime();
        try {
            while (rs.next()) {
            }
        } catch (Exception e) {
            // thru
        }
        Long endtime = Calendar.getInstance().getTime().getTime();
        System.out.println("END LOOP. execute time: " + (endtime - starttime) + " ms.");

        List<ManydataEntity> result = new ArrayList<>();
        return result;
    }


    public List<ManydataEntity> findAllByFetch(Integer fetchsize) {
        final EntityManager em = context.getEntityManagerByManagedType(ManydataEntity.class);
        JpaEntityManager eclipseLinkEm = em.unwrap(JpaEntityManager.class);
        final String sql = "SELECT * FROM MANYDATA;";
        DataReadQuery q = new DataReadQuery(sql);
        q.useCursoredStream();
        q.setFetchSize(fetchsize);

        Session session = eclipseLinkEm.getActiveSession();
        CursoredStream cursor = (CursoredStream) session.executeQuery(q);

        System.out.println("START LOOP");
        Long starttime = Calendar.getInstance().getTime().getTime();
        int i=0;
        String checkflg;
        while (cursor.hasNext()) {
            cursor.next();
            if (i%10000 == 0) {
                cursor.clear();
            }
            i++;
        }
        Long endtime = Calendar.getInstance().getTime().getTime();
        System.out.println("END LOOP. execute time: " + (endtime - starttime) + " ms.");

        List<ManydataEntity> result = new ArrayList<>();

        return result;
    }

    public List<ManydataEntity> findAllNoFetch() {

        final EntityManager em = context.getEntityManagerByManagedType(ManydataEntity.class);
        JpaEntityManager eclipseLinkEm = em.unwrap(JpaEntityManager.class);
        final String sql = "SELECT * FROM MANYDATA;";
        DataReadQuery q = new DataReadQuery(sql);
        q.useCursoredStream();

        Session session = eclipseLinkEm.getActiveSession();
        CursoredStream cursor = (CursoredStream) session.executeQuery(q);

        System.out.println("START LOOP");
        Long starttime = Calendar.getInstance().getTime().getTime();
        int i=0;
        while (cursor.hasNext()) {
            Object row = cursor.next();
            if (i%10000 == 0) {
                cursor.clear();
            }
            i++;
        }
        Long endtime = Calendar.getInstance().getTime().getTime();
        System.out.println("END LOOP. execute time: " + (endtime - starttime) + " ms.");

        List<ManydataEntity> result = new ArrayList<>();

        return result;
    }
}
