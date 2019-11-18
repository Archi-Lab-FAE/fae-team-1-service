package de.th.koeln.archilab.fae.faeteam1service.alarmknopf;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AlarmknopfRepository extends JpaRepository<Alarmknopf, UUID> {
}
