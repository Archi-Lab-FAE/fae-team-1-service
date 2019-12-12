package de.th.koeln.archilab.fae.faeteam1service.DemenziellErkrankter;


import lombok.Data;

import java.awt.geom.Point2D;
import java.util.Date;

@Data
public class PositionssenderOutDaten {
    private String Id;
    private Date letzteWartung;
    private Point2D.Double Position;
}
