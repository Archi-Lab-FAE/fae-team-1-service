package de.th.koeln.archilab.fae.faeteam1service.alarmknopf;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface AlarmknopfRepository extends CrudRepository<Alarmknopf, UUID> {
}
