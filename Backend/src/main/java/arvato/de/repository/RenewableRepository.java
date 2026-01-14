package arvato.de.repository;

import arvato.de.model.Renewable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RenewableRepository extends JpaRepository<Renewable, Long> {
    Renewable findByYear(int year);
}