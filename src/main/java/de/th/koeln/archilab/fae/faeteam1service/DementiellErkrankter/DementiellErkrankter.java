package de.th.koeln.archilab.fae.faeteam1service.DementiellErkrankter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@Table( name = "dementiellErkrankte")
public class DementiellErkrankter implements Serializable {
    @Id
    private UUID id;
    private String name;
    private String vorname;
    private Boolean zustimmung;
    private Integer schuhgroesse;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "dementiellErkrankte_id")
    private List<Zone> zonen;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "dementiellErkrankte_id")
    private List<KontaktPerson> kontaktPersonen;

    protected DementiellErkrankter() {
        this.id = UUID.randomUUID();
    }
    public void setZonen(List<Zone> zonen) {
        this.zonen = zonen;
    }
}
