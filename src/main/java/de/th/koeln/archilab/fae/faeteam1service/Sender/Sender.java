package de.th.koeln.archilab.fae.faeteam1service.Sender;

//import Heim.Heim;
//import Patient.Patient;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@Data
public class Sender{

 //   @Setter
 //   @JsonUnwrapped
 //   private Patient sender;

 //   private Heim heim;

    @Id
    private UUID id;

    private Date letzteWartung;

    private int letzteKoordinaten[];

    protected Sender() {
        this.setId(UUID.randomUUID());
    }

}
