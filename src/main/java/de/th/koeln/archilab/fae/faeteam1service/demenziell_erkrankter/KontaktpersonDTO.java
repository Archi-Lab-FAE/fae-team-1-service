package de.th.koeln.archilab.fae.faeteam1service.demenziell_erkrankter;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
public class KontaktpersonDTO implements Serializable {
    private String id;
    private String name;
    private String vorname;
    private String telefonnummer;
    private Boolean aktiv;

    public KontaktpersonDTO() {
        this.id = UUID.randomUUID().toString();
    }

    public KontaktpersonDTO(String name, String vorname, String telefonnummer, Boolean aktiv)
    {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.vorname = vorname;
        this.telefonnummer = telefonnummer;
        this.aktiv = aktiv;
    }
}
