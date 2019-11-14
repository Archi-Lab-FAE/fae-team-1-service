package de.th.koeln.archilab.fae.faeteam1service.knopf;

import de.th.koeln.archilab.fae.faeteam1service.shared.Adresse;
import de.th.koeln.archilab.fae.faeteam1service.shared.Telefonnummer;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.UUID;

@Entity
@Data
public class Knopf {

    @Id
    private UUID id;
    @OneToOne
    private Adresse adresse;
    @OneToOne
    private Telefonnummer telefonnummer;

    protected Knopf() {
        this.id = UUID.randomUUID();
    }

    public Knopf(Adresse adresse, Telefonnummer telefonnummer) {
        this.id = UUID.randomUUID();
        this.adresse = adresse;
        this.telefonnummer = telefonnummer;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public Telefonnummer getTelefonnummer() {
        return telefonnummer;
    }

    public void setTelefonnummer(Telefonnummer telefonnummer) {
        this.telefonnummer = telefonnummer;
    }
}
