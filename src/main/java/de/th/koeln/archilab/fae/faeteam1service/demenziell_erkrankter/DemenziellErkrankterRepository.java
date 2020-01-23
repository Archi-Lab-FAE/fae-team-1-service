package de.th.koeln.archilab.fae.faeteam1service.demenziell_erkrankter;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemenziellErkrankterRepository extends CrudRepository <DemenziellErkrankter, String> {
}
