package de.th.koeln.archilab.fae.faeteam1service.DemenziellErkrankter;


import lombok.AllArgsConstructor;
import lombok.Data;


import javax.persistence.*;
import java.awt.geom.Point2D;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@Table(name = "Positionen")

public class Positionssender {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer SenderId;
    private Date LetzteWartung;
    private Point2D.Double Position;

    public Positionssender( Date LetzteWartung, Point2D.Double Position){
        this.LetzteWartung = LetzteWartung;
        this.Position = Position;
    }

    public Integer getSenderId(){
        return SenderId;
    }

    public Date getLetzteWartung(){
        return LetzteWartung;
    }

    public Point2D.Double getPosition(){
        return Position;
    }

    @ManyToOne
    @JoinColumn( name = "DemenziellErkrankter", referencedColumnName = "id" )
    private DemenziellErkrankter demenziellErkrankter;

    public void setDemenziellErkrankter(DemenziellErkrankter demenziellErkrankter) {

        this.demenziellErkrankter = demenziellErkrankter;

    }

    public DemenziellErkrankter getDemenziellErkrankter() {
        return demenziellErkrankter;
    }
}

