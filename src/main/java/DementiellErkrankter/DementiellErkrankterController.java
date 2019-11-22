package DementiellErkrankter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
public class DementiellErkrankterController {

    @Autowired
    private DementiellErkrankterRepository dementiellErkrankterRepository;

    @GetMapping("/DementiellErkrankter")
    public Iterable<DementiellErkrankter> getallDementiellErkrankter() {
        return dementiellErkrankterRepository.findAll();
    }

    @GetMapping("/DementiellErkrankter/{id}")
    public Optional<DementiellErkrankter> getDementiellErkrankterById(@PathVariable UUID id){
        return dementiellErkrankterRepository.findById(id);
    }

    @DeleteMapping("/DementiellErkrankter/{id}")
    public void deleteDementiellErkrankterById(@PathVariable UUID id) {
        dementiellErkrankterRepository.deleteById(id);
        return;
    }

    @PostMapping("/DementiellErkrankter")
    public DementiellErkrankter newDementiellErkrankter(@RequestBody DementiellErkrankter dementiellErkrankter) {
        return dementiellErkrankterRepository.save(dementiellErkrankter);
    }

    @PutMapping("/DementiellErkrankter/{id}")
    public DementiellErkrankter updateDementiellErkrankter(@RequestBody DementiellErkrankter newDementiellErkrankter, @PathVariable UUID id ) {
        if ( dementiellErkrankterRepository.existsById(id) ) {
            newDementiellErkrankter.setId(id);
        }
        return dementiellErkrankterRepository.save(newDementiellErkrankter);
    }
}
