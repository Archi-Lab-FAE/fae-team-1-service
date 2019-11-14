package de.th.koeln.archilab.fae.faeteam1service.Sender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
public class SenderController {

    @Autowired
    private SenderRepository senderRepository;

    @GetMapping("/sender")
    public Iterable<Sender> sender() {
        return( senderRepository.findAll() );
    }

    @PostMapping("/sender")
    public Sender newSender( @RequestBody Sender newSender) {
        return senderRepository.save(newSender);
    }

    @DeleteMapping("/sender/{id}")
    public void deleteSender(@PathVariable UUID id) {
       senderRepository.deleteById(id);
    }

    @GetMapping("/sender/{id}")
    public Optional<Sender> getSenderByID(@PathVariable UUID id) {
        return senderRepository.findById(id);
    }

    @PutMapping("/sender/{id}")
    public ResponseEntity<Sender> updateSender(@PathVariable UUID id, @RequestBody Sender neuerSender  ) {
        Optional<Sender> sender = senderRepository.findById(id);
        if (!sender.isPresent()){
            return ResponseEntity.notFound().build();
        }
        Sender kopie = sender.get();
        kopie.setId( id );
        kopie.setLetzteKoordinaten(neuerSender.getLetzteKoordinaten());
        kopie.setLetzteWartung(neuerSender.getLetzteWartung());
        senderRepository.save(kopie);
        return ResponseEntity.noContent().build();
    }
}
