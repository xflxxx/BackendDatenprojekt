package arvato.de.controller;

import arvato.de.service.OwidService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class DataController {

    Map<Integer, String> coleConsumption = new LinkedHashMap<>();
    Map<Integer, String> oilConsumption = new LinkedHashMap<>();
    Map<Integer, String> emissions = new LinkedHashMap<>();

    private final OwidService owidService;

    public DataController(OwidService owidService) {
        this.owidService = owidService;
    }

    @GetMapping()
    public String test() throws Exception {
        coleConsumption = owidService.getColeConsumptionData();
        oilConsumption = owidService.getOilConsumptionData();
        emissions = owidService.getCo2Data();

        System.out.println("Kohle:");
        System.out.println(coleConsumption);
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("CO2:");
        System.out.println(emissions);
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Öl:");
        System.out.println(oilConsumption);

        return coleConsumption.toString() + emissions.toString() + oilConsumption.toString();

    }
}