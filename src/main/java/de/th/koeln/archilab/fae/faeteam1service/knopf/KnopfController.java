package de.th.koeln.archilab.fae.faeteam1service.knopf;

import de.th.koeln.archilab.fae.faeteam1service.shared.AdresseRespository;
import de.th.koeln.archilab.fae.faeteam1service.shared.TelefonnummerRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class KnopfController {

    private final KnopfRepository knopfRepository;
    private final AdresseRespository adresseRespository;
    private final TelefonnummerRepository telefonnummerRepository;

    public KnopfController(KnopfRepository knopfRepository,
                           AdresseRespository adresseRespository,
                           TelefonnummerRepository telefonnummerRepository) {
        this.knopfRepository = knopfRepository;
        this.adresseRespository = adresseRespository;
        this.telefonnummerRepository = telefonnummerRepository;
    }

    @GetMapping
    public List<Knopf> getAllKnoepfe() {
        return this.knopfRepository.findAll();
    }

    @PostMapping
    public Knopf postKnopf(@RequestBody Knopf knopf) {
        this.adresseRespository.save(knopf.getAdresse());
        this.telefonnummerRepository.save(knopf.getTelefonnummer());
        return this.knopfRepository.save(knopf);
    }

    @PutMapping
    public Knopf putKnopf(@RequestParam UUID id, @RequestBody Knopf knopf) {
        return this.knopfRepository.findById(id).map(currentKnopf -> {
            currentKnopf.setAdresse(knopf.getAdresse());
            this.adresseRespository.findById(knopf.getAdresse().getId()).map(currentAdresse -> {
                currentAdresse.setStrasse(knopf.getAdresse().getStrasse());
                currentAdresse.setHausnummer(knopf.getAdresse().getHausnummer());
                currentAdresse.setPlz(knopf.getAdresse().getPlz());
                currentAdresse.setOrt(knopf.getAdresse().getOrt());
                return this.adresseRespository.save(currentAdresse);
            }).orElseGet(() -> this.adresseRespository.save(currentKnopf.getAdresse()));

            currentKnopf.setTelefonnummer(knopf.getTelefonnummer());
            this.telefonnummerRepository.findById(knopf.getTelefonnummer().getId()).map(currentTelefonnummer -> {
                currentTelefonnummer.setTelefonnummer(knopf.getTelefonnummer().getTelefonnummer());
                return this.telefonnummerRepository.save(currentTelefonnummer);
            }).orElseGet(() -> this.telefonnummerRepository.save(knopf.getTelefonnummer()));

            return this.knopfRepository.save(currentKnopf);
        }).orElseGet(() -> {
            this.adresseRespository.save(knopf.getAdresse());
            this.telefonnummerRepository.save(knopf.getTelefonnummer());
            return this.knopfRepository.save(knopf);
        });
    }

    @DeleteMapping
    public void deleteKnopf(@RequestParam UUID id) {
        this.knopfRepository.findById(id).ifPresent(knopf -> {
            this.adresseRespository.deleteById(knopf.getAdresse().getId());
            this.telefonnummerRepository.deleteById(knopf.getTelefonnummer().getId());
        });
        this.knopfRepository.deleteById(id);
    }
}
