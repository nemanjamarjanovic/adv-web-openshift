package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@NamedQueries(
        {
            @NamedQuery(
                    name = "Measurement.findAll",
                    query = "select m from Measurement m order by m.createTime"),
            @NamedQuery(
                    name = "Measurement.findAllDesc",
                    query = "select m from Measurement m order by m.createTime desc"),
            @NamedQuery(
                    name = "Measurement.findById",
                    query = "select m from Measurement m where m.id=:id"),
            @NamedQuery(
                    name = "Measurement.findAllBetweenTimes",
                    query = "select m from Measurement m where m.createTime between :before and :after order by m.createTime")
        })
public class Measurement implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(updatable = false)
    @NotNull
    @Size(min = 1, max = 100)
    private String originalData;

    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    public Measurement()
    {
    }

    public Measurement(String originalData)
    {
        this.originalData = originalData;
        this.createTime = new Date();
    }

    public Long getId()
    {
        return id;
    }

    public String getOriginalData()
    {
        return originalData;
    }

    public Date getCreateTime()
    {
        return createTime;
    }

}
