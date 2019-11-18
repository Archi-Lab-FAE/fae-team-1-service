package de.th.koeln.archilab.fae.faeteam1service.alarmknopf;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.awt.geom.Point2D;
import java.util.UUID;

@Entity
@Data
public class Alarmknopf {

    @Id
    private UUID id;
    private Point2D.Double position;
    private String telefonnummer;

    protected Alarmknopf() {
        this.id = UUID.randomUUID();
    }

    public Alarmknopf(Point2D.Double position, String telefonnummer) {
        this.id = UUID.randomUUID();
        this.position = position;
        setTelefonnummer(telefonnummer);
    }

    public Point2D.Double getPosition() {
        return position;
    }

    public void setPosition(Point2D.Double position) {
        this.position = position;
    }

    public String getTelefonnummer() {
        return telefonnummer;
    }

    public void setTelefonnummer(String telefonnummer) {
        telefonnummer = telefonnummer.replaceAll("[ \\\\/()]", "")
                .replace("+", "00");
        if (telefonnummer.length() < 1 || !telefonnummer.matches("^[0-9]*$")) {
            throw new IllegalArgumentException("Ungueltige Telefonnumer");
        }
        this.telefonnummer = telefonnummer;
    }
}
