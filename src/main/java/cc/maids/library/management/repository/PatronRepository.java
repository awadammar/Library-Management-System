package cc.maids.library.management.repository;

import cc.maids.library.management.entity.Patron;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatronRepository extends JpaRepository<Patron, Long> {
    boolean existsByContactInformation(String contactInformation);
}
