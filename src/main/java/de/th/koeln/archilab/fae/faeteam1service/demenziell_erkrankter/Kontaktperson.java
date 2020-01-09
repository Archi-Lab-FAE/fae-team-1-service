package de.th.koeln.archilab.fae.faeteam1service.demenziell_erkrankter;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
@Entity
@Table(name = "kontaktPersonen")
public class Kontaktperson implements Serializable {

    @Id
    private String id;
    private String name;
    private String vorname;
    private String telefonnummer;
    private Boolean aktiv;

    public Kontaktperson() {
        this.id = UUID.randomUUID().toString();
    }

    public Kontaktperson(KontaktpersonDTO kontaktpersonDTO) {
        this.setId(kontaktpersonDTO.getId());
        this.setName(kontaktpersonDTO.getName());
        this.setVorname(kontaktpersonDTO.getVorname());
        this.setTelefonnummer(kontaktpersonDTO.getTelefonnummer());
        this.setAktiv(kontaktpersonDTO.getAktiv());
    }
}
