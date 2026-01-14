package arvato.de.repository;

import arvato.de.model.Co2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Co2Repository extends JpaRepository<Co2, Long> {
    Co2 findByYear(int year);
}