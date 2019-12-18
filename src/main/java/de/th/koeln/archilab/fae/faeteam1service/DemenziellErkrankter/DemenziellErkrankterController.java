package de.th.koeln.archilab.fae.faeteam1service.DemenziellErkrankter;

import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class DemenziellErkrankterController {

    private final DemenziellErkrankterRepository demenziellErkrankterRepository;

    public DemenziellErkrankterController(DemenziellErkrankterRepository demenziellErkrankterRepository) {
        this.demenziellErkrankterRepository = demenziellErkrankterRepository;
    }

    @GetMapping("/demenziell-erkrankte")
    public Iterable<DemenziellErkrankterOutDaten> getAllDemenziellErkrankte() {
        List<DemenziellErkrankterOutDaten> outDaten = new ArrayList<>();
        for (DemenziellErkrankter demenziellErkrankter : demenziellErkrankterRepository.findAll()) {
            outDaten.add(demenziellErkrankter.toOutFormat());
        }
        return outDaten;
    }

    @GetMapping("/demenziell-erkrankte/{id}")
    public DemenziellErkrankterOutDaten getDemenziellErkrankterById(@PathVariable String id) {
        Optional<DemenziellErkrankter> demenziellErkrankter = demenziellErkrankterRepository.findById(UUID.fromString(id));
        DemenziellErkrankterOutDaten outDaten = new DemenziellErkrankterOutDaten();
        if (demenziellErkrankter.isPresent()) {
            outDaten = demenziellErkrankter.get().toOutFormat();
        }
        return outDaten;
    }

    @PostMapping("/demenziell-erkrankte")
    public DemenziellErkrankter newDemenziellErkrankter(@RequestBody DemenziellErkrankter demenziellErkrankter) {
        return demenziellErkrankterRepository.save(demenziellErkrankter);
    }

    @PutMapping("/demenziell-erkrankte/{id}")
    public DemenziellErkrankter updateDemenziellErkrankter(@RequestBody DemenziellErkrankter newDemenziellErkrankter, @PathVariable String id) {
        return demenziellErkrankterRepository.save(newDemenziellErkrankter);
    }

    @DeleteMapping("/demenziell-erkrankte/{id}")
    public void deleteDemenziellErkrankterById(@PathVariable String id) {
        demenziellErkrankterRepository.deleteById(UUID.fromString(id));
    }

    /* ##########################
       # Endpunkt Kontaktperson #
       ########################## */

    @GetMapping("/demenziell-erkrankte/{demenziellErkrankterId}/kontaktpersonen")
    public Iterable<Kontaktperson> getAllKontaktpersonenForDemenziellErkrankten(@PathVariable String demenziellErkrankterId) {
        return demenziellErkrankterRepository.findById(UUID.fromString(demenziellErkrankterId)).map(DemenziellErkrankter::getKontaktpersonen)
                .orElseThrow(DemenziellErkrankterNotFoundException::new);
    }

    @GetMapping("/demenziell-erkrankte/{demenziellErkrankterId}/kontaktpersonen/{kpid}")
    public Kontaktperson getKontaktpersonById(@PathVariable String demenziellErkrankterId, @PathVariable String kpid) {
        return demenziellErkrankterRepository.findById(UUID.fromString(demenziellErkrankterId)).map(demenziellErkrankter -> {
            for (Kontaktperson kontaktperson : demenziellErkrankter.getKontaktpersonen()) {
                if (kontaktperson.getId().equals(kpid)) {
                    return kontaktperson;
                }
            }
            throw new KontaktpersonNotFoundException();
        }).orElseThrow(DemenziellErkrankterNotFoundException::new);
    }

    // TODO: should return Kontaktperson
    @PostMapping("/demenziell-erkrankte/{demenziellErkrankterId}/kontaktpersonen/{kpid}")
    public DemenziellErkrankter newKontaktpersonForDemenziellErkrankten(@RequestBody Kontaktperson kontaktperson, @PathVariable String demenziellErkrankterId, @PathVariable String kpid) {
        return demenziellErkrankterRepository.save(demenziellErkrankterRepository.findById(UUID.fromString(demenziellErkrankterId)).map(demenziellErkrankter -> {
            List<Kontaktperson> kontaktpersonen = demenziellErkrankter.getKontaktpersonen();
            kontaktpersonen.add(kontaktperson);
            demenziellErkrankter.setKontaktpersonen(kontaktpersonen);
            return demenziellErkrankter;
        }).orElseThrow(DemenziellErkrankterNotFoundException::new));
    }

    // TODO: should return Kontaktperson
    @PutMapping("/demenziell-erkrankte/{demenziellErkrankterId}/kontaktpersonen/{kpid}")
    public DemenziellErkrankter updateKontaktpersonForDemenziellErkrankten(@RequestBody Kontaktperson kontaktperson, @PathVariable String demenziellErkrankterId, @PathVariable String kpid) {
        return demenziellErkrankterRepository.save(demenziellErkrankterRepository.findById(UUID.fromString(demenziellErkrankterId)).map(demenziellErkrankter -> {
            List<Kontaktperson> kontaktpersonen = demenziellErkrankter.getKontaktpersonen();
            kontaktpersonen.removeIf(existingKontaktperson -> existingKontaktperson.getId().equals(kpid));
            kontaktpersonen.add(kontaktperson);
            demenziellErkrankter.setKontaktpersonen(kontaktpersonen);
            return demenziellErkrankter;
        }).orElseThrow(DemenziellErkrankterNotFoundException::new));
    }

    @DeleteMapping("/demenziell-erkrankte/{demenziellErkrankterId}/kontaktpersonen/{kpid}")
    public void deleteKontaktpersonForDemenziellErkrankten(@PathVariable String demenziellErkrankterId, @PathVariable String kpid) {
        demenziellErkrankterRepository.save(demenziellErkrankterRepository.findById(UUID.fromString(demenziellErkrankterId)).map(demenziellErkrankter -> {
            List<Kontaktperson> kontaktpersonen = demenziellErkrankter.getKontaktpersonen();
            kontaktpersonen.removeIf(existingKontaktperson -> existingKontaktperson.getId().equals(kpid));
            demenziellErkrankter.setKontaktpersonen(kontaktpersonen);
            return demenziellErkrankter;
        }).orElseThrow(DemenziellErkrankterNotFoundException::new));
    }
}
