package arvato.de.model;

import jakarta.persistence.*;

@Entity
@Table(name = "fossil_data")
public class Fossil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "data_year")
    private int year;
    private String fossilData;

    public String getFossilData() {
        return fossilData;
    }

    public void setFossilData(String fossilData) {
        this.fossilData = fossilData;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
