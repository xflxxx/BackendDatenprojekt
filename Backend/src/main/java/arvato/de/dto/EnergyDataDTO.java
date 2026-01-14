package arvato.de.dto;

public class EnergyDataDTO {
    private int year;
    private String co2Data;
    private String fossilData;
    private String renewableData;

    public EnergyDataDTO(int year, String co2Data, String fossilData, String renewableData) {
        this.year = year;
        this.co2Data = co2Data;
        this.fossilData = fossilData;
        this.renewableData = renewableData;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getCo2Data() {
        return co2Data;
    }

    public void setCo2Data(String co2Data) {
        this.co2Data = co2Data;
    }

    public String getFossilData() {
        return fossilData;
    }

    public void setFossilData(String fossilData) {
        this.fossilData = fossilData;
    }

    public String getRenewableData() {
        return renewableData;
    }

    public void setRenewableData(String renewableData) {
        this.renewableData = renewableData;
    }

    @Override
    public String toString() {
        return "EnergyDataDTO{" +
                "year=" + year +
                ", co2Value=" + co2Data +
                ", fossilValue=" + fossilData +
                ", renewableValue=" + renewableData +
                '}';
    }
}
