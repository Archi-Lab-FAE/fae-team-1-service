package de.th.koeln.archilab.fae.faeteam1service.knopf;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface KnopfRepository extends JpaRepository<Knopf, UUID> {
}
