package DementiellErkrankter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class DementiellErkrankter {

    private UUID id;
    private String name;
    private String vorname;
    private Boolean zustimmung;
    private Integer schuhgroesse;

    public DementiellErkrankter() {
        this.id = UUID.randomUUID();
    }
}
