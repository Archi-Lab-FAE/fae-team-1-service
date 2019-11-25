package de.th.koeln.archilab.fae.faeteam1service.DementiellErkrankter;

import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
public class DementiellErkrankterController {

    private final DementiellErkrankterRepository dementiellErkrankterRepository;

    public DementiellErkrankterController(DementiellErkrankterRepository dementiellErkrankterRepository ) {
        this.dementiellErkrankterRepository = dementiellErkrankterRepository;
    }

    @GetMapping("/DementiellErkrankte")
    public Iterable<DementiellErkrankter> getallDementiellErkrankter() {
        return dementiellErkrankterRepository.findAll();
    }

    @GetMapping("/DementiellErkrankte/{id}")
    public Optional<DementiellErkrankter> getDementiellErkrankterById(@PathVariable UUID id){
        return dementiellErkrankterRepository.findById(id);
    }

    @DeleteMapping("/DementiellErkrankte/{id}")
    public void deleteDementiellErkrankterById(@PathVariable UUID id) {
        dementiellErkrankterRepository.deleteById(id);
        return;
    }

    @PostMapping("/DementiellErkrankte")
    public DementiellErkrankter newDementiellErkrankter(@RequestBody DementiellErkrankter dementiellErkrankter) {
        return dementiellErkrankterRepository.save(dementiellErkrankter);
    }

    @PutMapping("/DementiellErkrankte/{id}")
    public DementiellErkrankter updateDementiellErkrankter(@RequestBody DementiellErkrankter newDementiellErkrankter, @PathVariable UUID id ) {
        if ( dementiellErkrankterRepository.existsById(id) ) {
            newDementiellErkrankter.setId(id);
        }
        return dementiellErkrankterRepository.save(newDementiellErkrankter);
    }
}
