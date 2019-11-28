package de.th.koeln.archilab.fae.faeteam1service.DemenziellErkrankter;

import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
public class DemenziellErkrankterController {

    private final DemenziellErkrankterRepository demenziellErkrankterRepository;

    public DemenziellErkrankterController(DemenziellErkrankterRepository demenziellErkrankterRepository ) {
        this.demenziellErkrankterRepository = demenziellErkrankterRepository;
    }

    @GetMapping("/DemenziellErkrankte")
    public Iterable<DemenziellErkrankter> getallDemenziellErkrankter() {
        return demenziellErkrankterRepository.findAll();
    }

    @GetMapping("/DemenziellErkrankte/{id}")
    public Optional<DemenziellErkrankter> getDemenziellErkrankterById(@PathVariable UUID id){
        return demenziellErkrankterRepository.findById(id);
    }

    @DeleteMapping("/DemenziellErkrankte/{id}")
    public void deleteDemenziellErkrankterById(@PathVariable UUID id) {
        demenziellErkrankterRepository.deleteById(id);
        return;
    }

    @PostMapping("/DemenziellErkrankte")
    public DemenziellErkrankter newDemenziellErkrankter(@RequestBody DemenziellErkrankter demenziellErkrankter) {
        return demenziellErkrankterRepository.save(demenziellErkrankter);
    }

    @PutMapping("/DemenziellErkrankte/{id}")
    public DemenziellErkrankter updateDemenziellErkrankter(@RequestBody DemenziellErkrankter newDemenziellErkrankter, @PathVariable UUID id ) {
        if ( demenziellErkrankterRepository.existsById(id) ) {
            //newDemenziellErkrankter.setId(id);
        }
        return demenziellErkrankterRepository.save(newDemenziellErkrankter);
    }
}
