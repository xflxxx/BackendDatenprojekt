package arvato.de.service;

import com.opencsv.CSVReader;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.StringReader;
import java.util.*;

@Service
public class OwidService {

    private static final String COLE_CONSUMPTION_URL =
            "https://raw.githubusercontent.com/owid/energy-data/master/owid-energy-data.csv";
    private static final String CO2_URL =
            "https://raw.githubusercontent.com/owid/co2-data/master/owid-co2-data.csv";

    private final RestTemplate restTemplate = new RestTemplate();

    public Map<Integer, String> getOilConsumptionData() throws Exception {
        String csv = restTemplate.getForObject(COLE_CONSUMPTION_URL, String.class);
        return filterCsv(csv, "oil_consumption");
    }

    public Map<Integer, String> getColeConsumptionData() throws Exception {
        String csv = restTemplate.getForObject(COLE_CONSUMPTION_URL, String.class);
        return filterCsv(csv, "coal_consumption");
    }

    public Map<Integer, String> getCo2Data() throws Exception {
        String csv = restTemplate.getForObject(CO2_URL, String.class);
        return filterCsv(csv, "co2");
    }

    private Map<Integer, String> filterCsv(String csv, String value) throws Exception {
        Map<Integer, String> result = new LinkedHashMap<>();
        try (CSVReader reader = new CSVReader(new StringReader(csv))) {
            String[] headers = reader.readNext();
            String[] line;

            while ((line = reader.readNext()) != null) {
                Map<String, String> row = new HashMap<>();
                for (int i = 0; i < headers.length; i++) {
                    row.put(headers[i], line[i]);
                }

                if ("Germany".equals(row.get("country"))) {
                    int year = Integer.parseInt(row.get("year"));
                    if (year >= 2000) {
                        result.put(year, row.get(value));
                    }
                }
            }
        }
        return result;
    }
}
