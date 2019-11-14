package de.th.koeln.archilab.fae.faeteam1service.shared;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
public class Telefonnummer {

    @Id
    private UUID id;
    private String telefonnummer;

    protected Telefonnummer() {
        this.id = UUID.randomUUID();
    }

    /*public Telefonnummer(String telefonnummer) {
        this.id = UUID.randomUUID();
        setTelefonnummer(telefonnummer);
    }*/

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTelefonnummer() {
        return telefonnummer;
    }

    public void setTelefonnummer(String telefonnummer) {
        telefonnummer = telefonnummer.replaceAll("[ \\\\/()]", "")
        .replace("+", "00");
        if (telefonnummer.length() < 1 || !telefonnummer.matches("^[0-9]*$")) {
            throw new IllegalArgumentException("Ungueltige Telefonnumer");
        }
        this.telefonnummer = telefonnummer;
    }
}
