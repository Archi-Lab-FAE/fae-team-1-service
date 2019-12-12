package de.th.koeln.archilab.fae.faeteam1service.DemenziellErkrankter;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DemenziellErkrankterOutDaten {
    private String id;
    private String name;
    private String vorname;
    private List<KontakpersonOutDaten> kontaktPersonen;
    private List<Positionssender> positionssender;

    public DemenziellErkrankterOutDaten() {
        kontaktPersonen = new ArrayList<KontakpersonOutDaten>();
        positionssender = new ArrayList<Positionssender>();
    }

    public void addKontaktperson(KontakpersonOutDaten kontakperson) {
        this.kontaktPersonen.add(kontakperson);
    }
}
