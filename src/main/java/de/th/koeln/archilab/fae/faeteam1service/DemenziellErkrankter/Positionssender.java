package de.th.koeln.archilab.fae.faeteam1service.DemenziellErkrankter;


import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;



@Entity

@Data

@AllArgsConstructor

@Table(name = "Positionen")



public class Positionssender {

    @Id
    private String id;
    private Date LetzteWartung;


    public Positionssender(Date LetzteWartung){
        this.LetzteWartung = LetzteWartung;
    }

    public Positionssender(){
        this.id = UUID.randomUUID().toString();
    }

    public Date getLetzteWartung(){
        return LetzteWartung;
    }


    /*
     * TODO: WARNING: Gegenseitiger Verweis!
     */
    @ManyToOne
    @JoinColumn( name = "DemenziellErkrankter", referencedColumnName = "id" )
    private DemenziellErkrankter demenziellErkrankter;

    public void setDemenziellErkrankter(DemenziellErkrankter demenziellErkrankter) {
        this.demenziellErkrankter = demenziellErkrankter;
    }

    public DemenziellErkrankter getDemenziellErkrankter() {
        return demenziellErkrankter;
    }

    public PositionssenderOutDaten toOutFormat(){
        PositionssenderOutDaten outDaten = new PositionssenderOutDaten();
        outDaten.setId(this.id);
        outDaten.setLetzteWartung(this.LetzteWartung);

        return outDaten;
    }
}

