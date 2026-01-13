package arvato.de.controller;

import arvato.de.model.EnergyData;
import arvato.de.repository.EnergyDataRepository;
import arvato.de.service.OwidService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class DataController {

    @Autowired
    private OwidService owidService;

    @Autowired
    private EnergyDataRepository repository;

    @GetMapping("/get_data")
    public List<EnergyData> getData() throws Exception {
        if (repository.findAll().isEmpty()) {
            owidService.saveData();
        }
        return repository.findAll();
    }

    @PostConstruct
    public void clearTable() {
        repository.deleteAll();
    }
}