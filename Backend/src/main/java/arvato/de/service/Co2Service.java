package arvato.de.service;

import arvato.de.model.Co2;
import arvato.de.repository.Co2Repository;
import com.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
public class Co2Service {
    @Autowired
    private Co2Repository co2Repository;

    private static final String CO2_URL = "https://raw.githubusercontent.com/owid/co2-data/master/owid-co2-data.csv";

    public void saveCo2Data() throws Exception {

        URL url = new URL(CO2_URL);
        InputStream stream = url.openStream();
        InputStreamReader streamReader = new InputStreamReader(stream, StandardCharsets.UTF_8);
        CSVReader reader = new CSVReader(streamReader);
        String[] headers = reader.readNext();
        String[] line;

        List<Co2> dataList = new ArrayList<>();

        while ((line = reader.readNext()) != null) {
            Map<String, String> row = new HashMap<>();
            for (int i = 0; i < headers.length; i++) {
                row.put(headers[i], line[i]);
            }

            int year = Integer.parseInt(row.get("year"));
            if (year < 1970 || !"Germany".equals(row.get("country"))) {
                continue;
            }

            Co2 co2Data = new Co2();
            co2Data.setYear(year);
            co2Data.setCo2Data(row.get("co2"));
            dataList.add(co2Data);
        }
        co2Repository.saveAll(dataList);
        reader.close();
    }
}
