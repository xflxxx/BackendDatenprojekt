package arvato.de.service;

import arvato.de.model.Fossil;
import arvato.de.repository.FossilRepository;
import com.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FossilService {
    @Autowired
    private FossilRepository fossilRepository;

    private static final String CONSUMPTION_URL = "https://raw.githubusercontent.com/owid/energy-data/master/owid-energy-data.csv";

    public void saveFossilData() throws Exception {

        URL url = new URL(CONSUMPTION_URL);
        InputStream stream = url.openStream();
        InputStreamReader streamReader = new InputStreamReader(stream, StandardCharsets.UTF_8);
        CSVReader reader = new CSVReader(streamReader);
        String[] headers = reader.readNext();
        String[] line;

        List<Fossil> dataList = new ArrayList<>();

        while ((line = reader.readNext()) != null) {
            Map<String, String> row = new HashMap<>();
            for (int i = 0; i < headers.length; i++) {
                row.put(headers[i], line[i]);
            }

            int year = Integer.parseInt(row.get("year"));
            if (year < 1970 || !"Germany".equals(row.get("country"))) {
                continue;
            }

            Fossil fossilData = new Fossil();
            fossilData.setYear(year);
            fossilData.setFossilData(row.get("fossil_fuel_consumption"));
            dataList.add(fossilData);
        }
        fossilRepository.saveAll(dataList);
        reader.close();
    }
}
