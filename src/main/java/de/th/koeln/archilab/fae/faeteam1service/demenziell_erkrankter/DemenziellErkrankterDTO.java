package de.th.koeln.archilab.fae.faeteam1service.demenziell_erkrankter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class DemenziellErkrankterDTO implements Serializable {
    private String id;
    private String name;
    private String vorname;
    private Boolean zustimmung;
    private List<Kontaktperson> kontaktpersonen;
    private List<Positionssender> positionssender;

    public DemenziellErkrankterDTO() {
        this.id = UUID.randomUUID().toString();
    }
}
