package de.th.koeln.archilab.fae.faeteam1service.DemenziellErkrankter;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@Table( name = "kontaktPersonen" )
public class KontaktPerson {
    @Id
    private UUID id;
    private String name;
    private String vorname;
    private String telefonnummer;
    private Boolean zustimmung;

    public KontaktPerson() {
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getTelefonnummer() {
        return telefonnummer;
    }

    public void setTelefonnummer(String telefonnummer) {
        this.telefonnummer = telefonnummer;
    }

    public Boolean getZustimmung() {
        return zustimmung;
    }

    public void setZustimmung(Boolean zustimmung) {
        this.zustimmung = zustimmung;
    }
}
