package de.th.koeln.archilab.fae.faeteam1service.DemenziellErkrankter;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class DemenziellErkrankterController {

    private final DemenziellErkrankterRepository demenziellErkrankterRepository;

    public DemenziellErkrankterController(DemenziellErkrankterRepository demenziellErkrankterRepository ) {
        this.demenziellErkrankterRepository = demenziellErkrankterRepository;
    }

    @GetMapping("/demenziell-erkrankte")
    public Iterable<DemenziellErkrankterOutDaten> getallDemenziellErkrankter() {
        List<DemenziellErkrankterOutDaten> outDaten = new ArrayList<DemenziellErkrankterOutDaten>();
        for ( DemenziellErkrankter demenziellErkrankter: demenziellErkrankterRepository.findAll() ) {
            outDaten.add(demenziellErkrankter.toOutFormat());
        }
        return outDaten;
    }

    @GetMapping("/demenziell-erkrankte/{id}")
    public DemenziellErkrankterOutDaten getDemenziellErkrankterById(@PathVariable String id){
        Optional<DemenziellErkrankter> demenziellErkrankter = demenziellErkrankterRepository.findById(UUID.fromString(id));
        DemenziellErkrankterOutDaten outDaten = new DemenziellErkrankterOutDaten();
        if ( demenziellErkrankter.isPresent() ) {
            outDaten = demenziellErkrankter.get().toOutFormat();
        }
        return outDaten;
    }

    @DeleteMapping("/demenziell-erkrankte/{id}")
    public void deleteDemenziellErkrankterById(@PathVariable String id) {
        demenziellErkrankterRepository.deleteById(UUID.fromString(id));
        return;
    }

    @PostMapping("/demenziell-erkrankte")
    public DemenziellErkrankter newDemenziellErkrankter(@RequestBody DemenziellErkrankter demenziellErkrankter) {
        return demenziellErkrankterRepository.save(demenziellErkrankter);
    }

    @PutMapping("/demenziell-erkrankte/{id}")
    public DemenziellErkrankter updateDemenziellErkrankter(@RequestBody DemenziellErkrankter newDemenziellErkrankter, @PathVariable String id ) {
        if ( demenziellErkrankterRepository.existsById(UUID.fromString(id)) ) {
            //newDemenziellErkrankter.setId(id);
        }
        return demenziellErkrankterRepository.save(newDemenziellErkrankter);
    }
}
