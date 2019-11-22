package de.th.koeln.archilab.fae.faeteam1service.DementiellErkrankter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
public class DementiellErkrankter {
    @Id
    private UUID id;
    private String name;
    private String vorname;
    private Boolean zustimmung;
    private Integer schuhgroesse;

    protected DementiellErkrankter() {
        this.id = UUID.randomUUID();
    }
}
