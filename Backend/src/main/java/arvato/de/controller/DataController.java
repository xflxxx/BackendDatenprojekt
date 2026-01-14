package arvato.de.controller;

import arvato.de.dto.EnergyDataDTO;
import arvato.de.repository.Co2Repository;
import arvato.de.repository.FossilRepository;
import arvato.de.repository.RenewableRepository;
import arvato.de.service.Co2Service;
import arvato.de.service.DTOService;
import arvato.de.service.FossilService;
import arvato.de.service.RenewableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class DataController {

    @Autowired
    private Co2Service co2Service;

    @Autowired
    private FossilService fossilService;

    @Autowired
    private RenewableService renewableService;

    @Autowired
    private DTOService dtoService;

    @Autowired
    private FossilRepository fossilRepository;

    @Autowired
    private Co2Repository co2Repository;

    @Autowired
    private RenewableRepository renewableRepository;

    @GetMapping("/get_data")
    public List<EnergyDataDTO> getData() throws Exception {
        if (fossilRepository.findAll().isEmpty() && co2Repository.findAll().isEmpty() && renewableRepository.findAll().isEmpty()) {
            co2Service.saveCo2Data();
            fossilService.saveFossilData();
            renewableService.saveRenewableData();
        }
        return dtoService.getAllEnergyData();
    }

    @PostMapping("/reload_data")
    public List<EnergyDataDTO> reloadData() throws Exception {
        co2Repository.deleteAll();
        fossilRepository.deleteAll();
        renewableRepository.deleteAll();
        co2Service.saveCo2Data();
        fossilService.saveFossilData();
        renewableService.saveRenewableData();
        return dtoService.getAllEnergyData();
    }
}