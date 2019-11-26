package de.th.koeln.archilab.fae.faeteam1service.DementiellErkrankter;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@Table( name = "kontaktperson" )
public class KontaktPerson {
    @Id
    private UUID id;
    private String name;
    private String vorname;
    private String telefonnummer;
    private Boolean zustimmung;
}
