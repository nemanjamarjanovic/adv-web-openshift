package ejb;

import entity.DeviceState;
import entity.Measurement;
import entity.WorkingMode;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class DeviceStateDao {

    @PersistenceContext
    EntityManager entityManager;

    @PostConstruct
    public void init() {
        List a = entityManager.createNamedQuery("WorkingMode.findAll").getResultList();
        if (a != null && a.isEmpty()) {
            WorkingMode workingMode0 = new WorkingMode(0, "NIJE ODABRAN",
                    BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, 0, 0);
            WorkingMode workingMode1 = new WorkingMode(1, "FAZA PRVA",
                    BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, 0, 0);
            WorkingMode workingMode2 = new WorkingMode(2, "FAZA DRUGA",
                    BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, 0, 0);
            WorkingMode workingMode3 = new WorkingMode(3, "FAZA TRECA",
                    BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, 0, 0);
            WorkingMode workingMode4 = new WorkingMode(4, "NEDOZVOLJENO STANJE",
                    BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, 0, 0);

            entityManager.persist(workingMode0);
            entityManager.persist(workingMode1);
            entityManager.persist(workingMode2);
            entityManager.persist(workingMode3);
            entityManager.persist(workingMode4);
        }
    }

    public List findLastDeviceState() {
        return entityManager
                .createNamedQuery("DeviceState.findAllDesc")
                .setMaxResults(1)
                .getResultList();
    }

    public List findtDeviceStatesBetweenTimes(Date dateFrom, Date dateTo, int limit) {
        return entityManager
                .createNamedQuery("DeviceState.findAllBetweenTimes")
                .setParameter("dateFrom", dateFrom)
                .setParameter("dateTo", dateTo)
                .setMaxResults(limit)
                .getResultList();
    }

    public void addNewMeasurement(String input) {
        String values[] = input.trim().split("-");
        for (String value : values) {
            value = value.trim();
        }

        WorkingMode workingMode = (WorkingMode) entityManager
                .createNamedQuery("WorkingMode.findById")
                .setParameter("id", Integer.parseInt(values[5]))
                .getSingleResult();

        Measurement measurement = new Measurement(input);
        entityManager.persist(measurement);

        Date deviceTime;
        try {
            deviceTime = new SimpleDateFormat("").parse(values[6]);
        } catch (ParseException ex) {
            deviceTime = new Date(0);
        }

        DeviceState deviceState = new DeviceState(new BigDecimal(values[1]), new BigDecimal(values[2]),
                workingMode, deviceTime, values[3].equals("DA"), values[4].equals("DA"));
        entityManager.persist(deviceState);

    }
}
