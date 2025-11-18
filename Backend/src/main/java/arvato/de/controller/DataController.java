package arvato.de.controller;

import arvato.de.model.Data;
import arvato.de.repository.Repo;
import arvato.de.service.OwidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class DataController {

    @Autowired
    private OwidService owidService;
    @Autowired
    private Repo repo;

    public void createDB() throws Exception {
        owidService.saveData();
    }

    @GetMapping()
    public List<Data> getData() throws Exception {
        createDB();
        return repo.findAll();
    }
}