package arvato.de.service;

import arvato.de.model.Data;
import arvato.de.repository.Repo;
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
    Repo repo;

    private static final String CONSUMPTION_URL =
            "https://raw.githubusercontent.com/owid/energy-data/master/owid-energy-data.csv";
    private static final String CO2_URL =
            "https://raw.githubusercontent.com/owid/co2-data/master/owid-co2-data.csv";

    private final RestTemplate restTemplate = new RestTemplate();

    public Map<Integer, Data> getConsumptionData() throws Exception {
        byte[] csvBytes = restTemplate.getForObject(CONSUMPTION_URL, byte[].class);
        Map<Integer, Data> map = new LinkedHashMap<>();

        try (InputStream is = new ByteArrayInputStream(csvBytes);
             CSVReader reader = new CSVReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
            String[] headers = reader.readNext();
            String[] line;

            while ((line = reader.readNext()) != null) {
                Map<String, String> row = new HashMap<>();
                for (int i = 0; i < headers.length; i++) {
                    row.put(headers[i], line[i]);
                }

                if (!"Germany".equals(row.get("country"))) {
                    continue;
                }
                int year = Integer.parseInt(row.get("year"));
                if (year < 2000) {
                    continue;
                }

                System.out.println("starting new Data");
                Data data = new Data();
                data.setYear(year);
                data.setOil(row.get("oil_consumption"));
                data.setCoal(row.get("coal_consumption"));
                map.put(year, data);
                System.out.println("finished new Data");
            }
        }
        return map;
    }

    public Map<Integer, String> getCo2Data() throws Exception {
        byte[] csvBytes = restTemplate.getForObject(CO2_URL, byte[].class);
        Map<Integer, String> map = new LinkedHashMap<>();

        try (InputStream is = new ByteArrayInputStream(csvBytes);
             CSVReader reader = new CSVReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
            String[] headers = reader.readNext();
            String[] line;

            while ((line = reader.readNext()) != null) {
                Map<String, String> row = new HashMap<>();
                //anders und performanter
                for (int i = 0; i < headers.length; i++) {
                    row.put(headers[i], line[i]);
                }

                int year = Integer.parseInt(row.get("year"));
                if (year < 2000) {
                    continue;
                }

                if (!"Germany".equals(row.get("country"))) {
                    continue;
                }

                map.put(year, row.get("co2"));
            }
        }
        return map;
    }

    public void saveData() throws Exception {
        System.out.println("start deleting");
        repo.deleteAll();
        System.out.println("finished deleting");

        System.out.println("start map1");
        Map<Integer, Data> map = getConsumptionData();
        System.out.println("finished map1");
        System.out.println("start map2");
        Map<Integer, String> co2 = getCo2Data();
        System.out.println("finished map2");

        System.out.println("started combining maps");
        for (Map.Entry<Integer, Data> entry : map.entrySet()) {
            Integer year = entry.getKey();
            entry.getValue().setCo2(co2.get(year));
        }
        System.out.println("finished combining maps");

        System.out.println("started saving all values");
        repo.saveAll(map.values());
        System.out.println("finished saving all values");
    }
}
