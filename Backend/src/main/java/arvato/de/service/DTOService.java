package arvato.de.service;

import arvato.de.dto.EnergyDataDTO;
import arvato.de.model.Co2;
import arvato.de.model.Fossil;
import arvato.de.model.Renewable;
import arvato.de.repository.Co2Repository;
import arvato.de.repository.FossilRepository;
import arvato.de.repository.RenewableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DTOService {
    @Autowired
    private Co2Repository co2Repository;

    @Autowired
    private FossilRepository fossilRepository;

    @Autowired
    private RenewableRepository renewableRepository;

    public List<EnergyDataDTO> getAllEnergyData() {
        List<Co2> co2List = co2Repository.findAll();
        List<Fossil> fossilList = fossilRepository.findAll();
        List<Renewable> renewableList = renewableRepository.findAll();

        List<EnergyDataDTO> result = new ArrayList<>();
        for (int i = 0; i < co2List.size(); i++) {
            EnergyDataDTO dto = new EnergyDataDTO(
                    co2List.get(i).getYear(),
                    co2List.get(i).getCo2Data(),
                    fossilList.get(i).getFossilData(),
                    renewableList.get(i).getRenewableData()
            );
            result.add(dto);
        }

        return result;
    }
}
