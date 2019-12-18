package de.th.koeln.archilab.fae.faeteam1service.DemenziellErkrankter;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Data
@AllArgsConstructor
@Entity
@Table(name = "kontaktPersonen")
public class Kontaktperson {

    @Id
    private String id;
    private String name;
    private String vorname;
    private String telefonnummer;
    private Boolean aktiv;

    public Kontaktperson() {
        this.id = UUID.randomUUID().toString();
    }

    public KontakpersonOutDaten toOutFormat() {
        KontakpersonOutDaten outDaten = new KontakpersonOutDaten();
        outDaten.setId(this.id);
        outDaten.setName(this.name);
        outDaten.setVorname(this.vorname);
        outDaten.setTelefonnummer(this.telefonnummer);
        return outDaten;
    }

}
