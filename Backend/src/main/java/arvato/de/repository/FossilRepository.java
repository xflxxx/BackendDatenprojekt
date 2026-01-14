package arvato.de.repository;

import arvato.de.model.Fossil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FossilRepository extends JpaRepository<Fossil, Long> {
    Fossil findByYear(int year);
}