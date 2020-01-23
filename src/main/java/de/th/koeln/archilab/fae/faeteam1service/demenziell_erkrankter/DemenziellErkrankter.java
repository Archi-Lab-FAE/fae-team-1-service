package de.th.koeln.archilab.fae.faeteam1service.demenziell_erkrankter;

import de.th.koeln.archilab.fae.faeteam1service.eventing.EventEntity;
import de.th.koeln.archilab.fae.faeteam1service.eventing.EventPublishingEntityListener;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@Entity
@EntityListeners(EventPublishingEntityListener.class)
@Table(name = "demenziellErkrankte")
public class DemenziellErkrankter implements Serializable, EventEntity {

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

    public DemenziellErkrankter(DemenziellErkrankterDTO demenziellErkrankterDTO) {
        this.setId(demenziellErkrankterDTO.getId());
        this.setName(demenziellErkrankterDTO.getName());
        this.setVorname(demenziellErkrankterDTO.getVorname());
        this.setZustimmung(demenziellErkrankterDTO.getZustimmung());
        this.setKontaktpersonen(demenziellErkrankterDTO.getKontaktpersonen());
        this.setPositionssender(demenziellErkrankterDTO.getPositionssender());
    }
}
