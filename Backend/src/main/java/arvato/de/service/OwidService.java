package arvato.de.service;

import arvato.de.model.EnergyData;
import arvato.de.repository.EnergyDataRepository;
import com.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
public class OwidService {

    @Autowired
    private EnergyDataRepository repository;

    private static final String CONSUMPTION_URL =
            "https://raw.githubusercontent.com/owid/energy-data/master/owid-energy-data.csv";
    private static final String CO2_URL =
            "https://raw.githubusercontent.com/owid/co2-data/master/owid-co2-data.csv";

    private final RestTemplate restTemplate = new RestTemplate();

    public void saveData() throws Exception {
        byte[] energyBytes = restTemplate.getForObject(CONSUMPTION_URL, byte[].class);
        Map<Integer, EnergyData> dataMap = new HashMap<>();

        try (InputStream is = new ByteArrayInputStream(energyBytes);
             CSVReader reader = new CSVReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {

            String[] headers = reader.readNext();
            String[] line;

            while ((line = reader.readNext()) != null) {
                Map<String, String> row = new HashMap<>();
                for (int i = 0; i < headers.length; i++) {
                    row.put(headers[i], line[i]);
                }

                int year = Integer.parseInt(row.get("year"));
                if (year < 1960 || !"Germany".equals(row.get("country"))) {
                    continue;
                }

                EnergyData data = new EnergyData();
                data.setYear(year);
                data.setRenewablesTotal(row.get("renewables_consumption"));
                data.setFossilTotal(row.get("fossil_fuel_consumption"));

                dataMap.put(year, data);
            }
        }

        byte[] co2Bytes = restTemplate.getForObject(CO2_URL, byte[].class);

        try (InputStream is = new ByteArrayInputStream(co2Bytes);
             CSVReader reader = new CSVReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {

            String[] headers = reader.readNext();
            String[] line;

            while ((line = reader.readNext()) != null) {
                Map<String, String> row = new HashMap<>();
                for (int i = 0; i < headers.length; i++) {
                    row.put(headers[i], line[i]);
                }

                int year = Integer.parseInt(row.get("year"));
                if (year < 1960 || !"Germany".equals(row.get("country"))) {
                    continue;
                }

                if (dataMap.containsKey(year)) {
                    dataMap.get(year).setCo2(row.get("co2"));
                }
            }
        }

        repository.saveAll(dataMap.values());
    }
}