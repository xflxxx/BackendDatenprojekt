package arvato.de.model;

import jakarta.persistence.*;

@Entity
@Table(name = "dataTable")
public class Data {

    @Id
    @Column(name = "data_year")
    private int year;

    private String co2;
    private String coal;
    private String oil;

    public String getOil() {
        return oil;
    }
    public void setOil(String oil) {
        this.oil = oil;
    }

    public String getCoal() {
        return coal;
    }
    public void setCoal(String coal) {
        this.coal = coal;
    }

    public String getCo2() {
        return co2;
    }
    public void setCo2(String co2) {
        this.co2 = co2;
    }

    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }

    public Data(){}

    public Data(int year, String coal, String co2, String oil) {
        this.year = year;
        this.coal = coal;
        this.co2 = co2;
        this.oil = oil;
    }
}
