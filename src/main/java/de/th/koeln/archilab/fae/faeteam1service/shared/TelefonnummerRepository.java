package de.th.koeln.archilab.fae.faeteam1service.shared;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TelefonnummerRepository extends JpaRepository<Telefonnummer, UUID> {
}
