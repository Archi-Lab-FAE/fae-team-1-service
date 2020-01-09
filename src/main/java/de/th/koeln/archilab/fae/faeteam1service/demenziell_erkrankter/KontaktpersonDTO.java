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
}
