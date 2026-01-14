package arvato.de.model;

import jakarta.persistence.*;

@Entity
@Table(name = "co2_data")
public class Co2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "data_year")
    private int year;
    private String co2Data;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCo2Data() {
        return co2Data;
    }

    public void setCo2Data(String co2Data) {
        this.co2Data = co2Data;
    }
}
