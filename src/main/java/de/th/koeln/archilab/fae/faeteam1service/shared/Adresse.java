package de.th.koeln.archilab.fae.faeteam1service.shared;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
public class Adresse {

    @Id
    private UUID id;
    private String strasse;
    private int hausnummer;
    private int plz;
    private String ort;

    protected Adresse() {
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getStrasse() {
        return strasse;
    }

    public int getHausnummer() {
        return hausnummer;
    }

    public int getPlz() {
        return plz;
    }

    public String getOrt() {
        return ort;
    }

    public void setStrasse(String strasse) {
        if (strasse.length() < 1 || !strasse.matches("^[A-Za-zäöüß.\\- ]*$")) {
            throw new IllegalArgumentException("Ungueltiger Strassenname");
        }
        this.strasse = strasse.trim();
    }

    public void setHausnummer(int hausnummer) {
        if (hausnummer < 1) {
            throw new IllegalArgumentException("Ungueltige Hausnummer");
        }
        this.hausnummer = hausnummer;
    }

    public void setPlz(int plz) {
        if (plz < 10000 || plz > 99999) {
            throw new IllegalArgumentException("Ungueltige Postleitzahl");
        }
        this.plz = plz;
    }

    public void setOrt(String ort) {
        if (ort.length() < 1 || !ort.matches("^[A-Za-zäöüß\\- ]*$")) {
            throw new IllegalArgumentException("Ungueltiger Ort");
        }
        this.ort = ort.trim();
    }
}
