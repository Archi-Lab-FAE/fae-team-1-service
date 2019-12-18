package de.th.koeln.archilab.fae.faeteam1service.DemenziellErkrankter;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@Entity
@Table(name = "demenziellErkrankte")
public class DemenziellErkrankter implements Serializable {

    @Id
    private String id;
    private String name;
    private String vorname;
    private Boolean zustimmung;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "demenziellErkrankte_id")
    private List<Kontaktperson> kontaktpersonen;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "demenziellErkrankte_id")
    private List<Positionssender> positionssender;


    public DemenziellErkrankter() {
        this.id = UUID.randomUUID().toString();
    }

    public DemenziellErkrankterOutDaten toOutFormat() {
        DemenziellErkrankterOutDaten outDaten = new DemenziellErkrankterOutDaten();
        outDaten.setId(this.id);
        outDaten.setName(this.name);
        outDaten.setVorname(this.vorname);
        for (Kontaktperson kontaktPerson : this.kontaktpersonen) {
            if (kontaktPerson.getAktiv()) {
                outDaten.addKontaktperson(kontaktPerson.toOutFormat());
            }
        }
        return outDaten;
    }
}
