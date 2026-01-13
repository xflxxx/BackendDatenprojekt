package arvato.de.model;

import jakarta.persistence.*;

@Entity
@Table(name = "energy_data")
public class EnergyData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_year")
    private Integer year;
    private String renewablesTotal;
    private String fossilTotal;
    private String co2;

    public EnergyData() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getRenewablesTotal() {
        return renewablesTotal;
    }

    public void setRenewablesTotal(String renewablesTotal) {
        this.renewablesTotal = renewablesTotal;
    }

    public String getFossilTotal() {
        return fossilTotal;
    }

    public void setFossilTotal(String fossilTotal) {
        this.fossilTotal = fossilTotal;
    }

    public String getCo2() {
        return co2;
    }

    public void setCo2(String co2) {
        this.co2 = co2;
    }
}