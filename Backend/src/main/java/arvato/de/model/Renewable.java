package arvato.de.model;

import jakarta.persistence.*;

@Entity
@Table(name = "renewable_data")
public class Renewable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "data_year")
    private int year;
    private String renewableData;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getRenewableData() {
        return renewableData;
    }

    public void setRenewableData(String renewableData) {
        this.renewableData = renewableData;
    }
}
