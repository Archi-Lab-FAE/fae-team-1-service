package de.th.koeln.archilab.fae.faeteam1service.alarmknopf;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
public class AlarmknopfController {

    private final AlarmknopfRepository alarmknopfRepository;

    public AlarmknopfController(AlarmknopfRepository alarmknopfRepository) {
        this.alarmknopfRepository = alarmknopfRepository;
    }

    @GetMapping("/alarmknoepfe")
    public Iterable<Alarmknopf> getAllAlarmknoepfe() {
        return this.alarmknopfRepository.findAll();
    }

    @GetMapping("/alarmknoepfe/{id}")
    public Alarmknopf getAlarmknopf(@PathVariable UUID id) {
        return this.alarmknopfRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Alarmknopf existiert nicht"));
    }

    @PostMapping("/alarmknoepfe")
    public Alarmknopf postAlarmknopf(@RequestBody Alarmknopf alarmknopf) {
        return this.alarmknopfRepository.save(alarmknopf);
    }

    @PutMapping("/alarmknoepfe/{id}")
    public Alarmknopf putAlarmknopf(@PathVariable UUID id, @RequestBody Alarmknopf alarmknopf) {
        return this.alarmknopfRepository.findById(id).map(currentKnopf -> {
            currentKnopf.setPosition(alarmknopf.getPosition());
            currentKnopf.setTelefonnummer(alarmknopf.getTelefonnummer());
            return this.alarmknopfRepository.save(currentKnopf);
        }).orElseGet(() -> this.alarmknopfRepository.save(alarmknopf));
    }

    @DeleteMapping("/alarmknoepfe")
    public void deleteAllAlarmknoepfe() {
        this.alarmknopfRepository.deleteAll();
    }

    @DeleteMapping("/alarmknoepfe/{id}")
    public void deleteAlarmknopf(@PathVariable UUID id) {
        this.alarmknopfRepository.deleteById(id);
    }
}
