package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@NamedQueries(
        {
            @NamedQuery(name = "WorkingMode.findAll", query = "select w from WorkingMode w"),
            @NamedQuery(name = "WorkingMode.findById", query = "select w from WorkingMode w where w.id=:id"),
        })
public class WorkingMode implements Serializable
{

    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;

    @NotNull
    @Size(min = 1, max = 64)
    @Column
    private String title;

    @NotNull
    @Column(precision = 3, scale = 1)
    private BigDecimal minTemperature;

    @NotNull
    @Column(precision = 3, scale = 1)
    private BigDecimal maxTemperature;

    @NotNull
    @Column(precision = 3, scale = 1)
    private BigDecimal minHumidity;

    @NotNull
    @Column(precision = 3, scale = 1)
    private BigDecimal maxHumidity;

    @NotNull
    @Column(precision = 3, scale = 1)
    private Integer minCO2;

    @NotNull
    @Column(precision = 3, scale = 1)
    private Integer maxCO2;

    public WorkingMode()
    {
    }

    public WorkingMode(Integer id, String title,
                       BigDecimal minTemperature, BigDecimal maxTemperature, 
                       BigDecimal minHumidity, BigDecimal maxHumidity, 
                       Integer minCO2, Integer maxCO2)
    {
        this.id = id;
        this.title = title;
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
        this.minHumidity = minHumidity;
        this.maxHumidity = maxHumidity;
        this.minCO2 = minCO2;
        this.maxCO2 = maxCO2;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public BigDecimal getMinTemperature()
    {
        return minTemperature;
    }

    public void setMinTemperature(BigDecimal minTemperature)
    {
        this.minTemperature = minTemperature;
    }

    public BigDecimal getMaxTemperature()
    {
        return maxTemperature;
    }

    public void setMaxTemperature(BigDecimal maxTemperature)
    {
        this.maxTemperature = maxTemperature;
    }

    public BigDecimal getMinHumidity()
    {
        return minHumidity;
    }

    public void setMinHumidity(BigDecimal minHumidity)
    {
        this.minHumidity = minHumidity;
    }

    public BigDecimal getMaxHumidity()
    {
        return maxHumidity;
    }

    public void setMaxHumidity(BigDecimal maxHumidity)
    {
        this.maxHumidity = maxHumidity;
    }

    public Integer getMinCO2()
    {
        return minCO2;
    }

    public void setMinCO2(Integer minCO2)
    {
        this.minCO2 = minCO2;
    }

    public Integer getMaxCO2()
    {
        return maxCO2;
    }

    public void setMaxCO2(Integer maxCO2)
    {
        this.maxCO2 = maxCO2;
    }

}
