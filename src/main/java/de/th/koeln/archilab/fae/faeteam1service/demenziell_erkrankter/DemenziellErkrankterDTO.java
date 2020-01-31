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

    public DemenziellErkrankterDTO(String name, String vorname, Boolean zustimmung, List<Kontaktperson> kontaktpersonen, List<Positionssender> positionssender)
    {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.vorname = vorname;
        this.zustimmung = zustimmung;
        this.kontaktpersonen = kontaktpersonen;
        this.positionssender = positionssender;
    }
}
