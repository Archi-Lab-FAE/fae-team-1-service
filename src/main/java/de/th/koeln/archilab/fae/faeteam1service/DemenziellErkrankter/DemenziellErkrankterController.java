package de.th.koeln.archilab.fae.faeteam1service.DemenziellErkrankter;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class DemenziellErkrankterController {

    private final DemenziellErkrankterRepository demenziellErkrankterRepository;

    public DemenziellErkrankterController(DemenziellErkrankterRepository demenziellErkrankterRepository) {
        this.demenziellErkrankterRepository = demenziellErkrankterRepository;
    }

    /* #################################
       # Endpunkt DemenziellErkrankter #
       ################################# */

    @GetMapping("/demenziell-erkrankte")
    public Iterable<DemenziellErkrankter> getAllDemenziellErkrankte() {
        return demenziellErkrankterRepository.findAll();
    }

    @GetMapping("/demenziell-erkrankte/{id}")
    public DemenziellErkrankter getDemenziellErkrankterById(@PathVariable String id) {
        return demenziellErkrankterRepository.findById(id).orElseThrow(DemenziellErkrankterNotFoundException::new);
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
        demenziellErkrankterRepository.deleteById(id);
    }

    /* ##########################
       # Endpunkt Kontaktperson #
       ########################## */

    @GetMapping("/demenziell-erkrankte/{demenziellErkrankterId}/kontaktpersonen")
    public Iterable<Kontaktperson> getAllKontaktpersonenForDemenziellErkrankten(@PathVariable String demenziellErkrankterId) {
        return demenziellErkrankterRepository.findById(demenziellErkrankterId).map(DemenziellErkrankter::getKontaktpersonen)
                .orElseThrow(DemenziellErkrankterNotFoundException::new);
    }

    @GetMapping("/demenziell-erkrankte/{demenziellErkrankterId}/kontaktpersonen/{kontaktPersonId}")
    public Kontaktperson getKontaktpersonById(@PathVariable String demenziellErkrankterId, @PathVariable String kontaktPersonId) {
        return demenziellErkrankterRepository.findById(demenziellErkrankterId).map(demenziellErkrankter -> {
            for (Kontaktperson kontaktperson : demenziellErkrankter.getKontaktpersonen()) {
                if (kontaktperson.getId().equals(kontaktPersonId)) {
                    return kontaktperson;
                }
            }
            throw new KontaktpersonNotFoundException();
        }).orElseThrow(DemenziellErkrankterNotFoundException::new);
    }

    @PostMapping("/demenziell-erkrankte/{demenziellErkrankterId}/kontaktpersonen")
    public Kontaktperson newKontaktpersonForDemenziellErkrankten(@RequestBody Kontaktperson kontaktperson, @PathVariable String demenziellErkrankterId) {
        demenziellErkrankterRepository.save(demenziellErkrankterRepository.findById(demenziellErkrankterId).map(demenziellErkrankter -> {
            List<Kontaktperson> kontaktpersonen = demenziellErkrankter.getKontaktpersonen();
            kontaktpersonen.add(kontaktperson);
            demenziellErkrankter.setKontaktpersonen(kontaktpersonen);
            return demenziellErkrankter;
        }).orElseThrow(DemenziellErkrankterNotFoundException::new));
        return kontaktperson;
    }

    @PutMapping("/demenziell-erkrankte/{demenziellErkrankterId}/kontaktpersonen/{kontaktPersonId}")
    public Kontaktperson updateKontaktpersonForDemenziellErkrankten(@RequestBody Kontaktperson kontaktperson, @PathVariable String demenziellErkrankterId, @PathVariable String kontaktPersonId) {
        demenziellErkrankterRepository.save(demenziellErkrankterRepository.findById(demenziellErkrankterId).map(demenziellErkrankter -> {
            List<Kontaktperson> kontaktpersonen = demenziellErkrankter.getKontaktpersonen();
            kontaktpersonen.removeIf(existingKontaktperson -> existingKontaktperson.getId().equals(kontaktPersonId));
            kontaktpersonen.add(kontaktperson);
            demenziellErkrankter.setKontaktpersonen(kontaktpersonen);
            return demenziellErkrankter;
        }).orElseThrow(DemenziellErkrankterNotFoundException::new));
        return kontaktperson;
    }

    @DeleteMapping("/demenziell-erkrankte/{demenziellErkrankterId}/kontaktpersonen/{kontaktPersonId}")
    public void deleteKontaktpersonForDemenziellErkrankten(@PathVariable String demenziellErkrankterId, @PathVariable String kontaktPersonId) {
        demenziellErkrankterRepository.save(demenziellErkrankterRepository.findById(demenziellErkrankterId).map(demenziellErkrankter -> {
            List<Kontaktperson> kontaktpersonen = demenziellErkrankter.getKontaktpersonen();
            kontaktpersonen.removeIf(existingKontaktperson -> existingKontaktperson.getId().equals(kontaktPersonId));
            demenziellErkrankter.setKontaktpersonen(kontaktpersonen);
            return demenziellErkrankter;
        }).orElseThrow(DemenziellErkrankterNotFoundException::new));
    }

    /* ###########################
       # Endpunkt Positionsender #
       ########################### */

    @GetMapping("/demenziell-erkrankte/{demenziellErkrankterId}/positionssender")
    public Iterable<Positionssender> getAllPositionssenderForDemenziellErkrankten(@PathVariable String demenziellErkrankterId) {
        return demenziellErkrankterRepository.findById(demenziellErkrankterId).map(DemenziellErkrankter::getPositionssender)
                .orElseThrow(DemenziellErkrankterNotFoundException::new);
    }

    @GetMapping("/demenziell-erkrankte/{demenziellErkrankterId}/positionssender/{positionssenderId}")
    public Positionssender getPositionssenderById(@PathVariable String demenziellErkrankterId, @PathVariable String positionssenderId) {
        return demenziellErkrankterRepository.findById(demenziellErkrankterId).map(demenziellErkrankter -> {
            for (Positionssender positionssender : demenziellErkrankter.getPositionssender()) {
                if (positionssender.getId().equals(positionssenderId)) {
                    return positionssender;
                }
            }
            throw new PositionssenderNotFoundException();
        }).orElseThrow(DemenziellErkrankterNotFoundException::new);
    }

    @PostMapping("/demenziell-erkrankte/{demenziellErkrankterId}/positionssender")
    public Positionssender newPositionssenderForDemenziellErkrankten(@RequestBody Positionssender positionssender, @PathVariable String demenziellErkrankterId) {
        demenziellErkrankterRepository.save(demenziellErkrankterRepository.findById(demenziellErkrankterId).map(demenziellErkrankter -> {
            List<Positionssender> positionssenderList = demenziellErkrankter.getPositionssender();
            positionssenderList.add(positionssender);
            demenziellErkrankter.setPositionssender(positionssenderList);
            return demenziellErkrankter;
        }).orElseThrow(DemenziellErkrankterNotFoundException::new));
        return positionssender;
    }

    @PutMapping("/demenziell-erkrankte/{demenziellErkrankterId}/positionssender/{positionssenderId}")
    public Positionssender updatePositionssenderForDemenziellErkrankten(@RequestBody Positionssender positionssender, @PathVariable String demenziellErkrankterId, @PathVariable String positionssenderId) {
        demenziellErkrankterRepository.save(demenziellErkrankterRepository.findById(demenziellErkrankterId).map(demenziellErkrankter -> {
            List<Positionssender> positionssenderList = demenziellErkrankter.getPositionssender();
            positionssenderList.removeIf(existingPositionssender -> existingPositionssender.getId().equals(positionssenderId));
            positionssenderList.add(positionssender);
            demenziellErkrankter.setPositionssender(positionssenderList);
            return demenziellErkrankter;
        }).orElseThrow(DemenziellErkrankterNotFoundException::new));
        return positionssender;
    }

    @DeleteMapping("/demenziell-erkrankte/{demenziellErkrankterId}/positionssender/{positionssenderId}")
    public void deletePositionssenderForDemenziellErkrankten(@PathVariable String demenziellErkrankterId, @PathVariable String positionssenderId) {
        demenziellErkrankterRepository.save(demenziellErkrankterRepository.findById(demenziellErkrankterId).map(demenziellErkrankter -> {
            List<Positionssender> positionssenderList = demenziellErkrankter.getPositionssender();
            positionssenderList.removeIf(existingPositionssender -> existingPositionssender.getId().equals(positionssenderId));
            demenziellErkrankter.setPositionssender(positionssenderList);
            return demenziellErkrankter;
        }).orElseThrow(DemenziellErkrankterNotFoundException::new));
    }
}
