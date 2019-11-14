package de.th.koeln.archilab.fae.faeteam1service.controller;

import de.th.koeln.archilab.fae.faeteam1service.repository.HeimRepository;
import de.th.koeln.archilab.fae.faeteam1service.ressource.erkranktePerson.ErkranktePerson;
import de.th.koeln.archilab.fae.faeteam1service.ressource.heim.Heim;
import de.th.koeln.archilab.fae.faeteam1service.ressource.knopf.Knopf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
public class HeimController
{
    @Autowired
    private HeimRepository heimRepository;

    @GetMapping("/heim")
    public Iterable<Heim> heim()
    {
        return heimRepository.findAll();
    }

    @PostMapping("/heim")
    public Heim neuesHeim(@RequestBody Heim heim)
    {
        return heimRepository.save(heim);
    }

    @GetMapping("/heim/{id}/erkrankteperson")
    public Iterable<ErkranktePerson> heimErkranktePersonen(@PathVariable Integer id)
    {
        Optional<Heim> heim = heimRepository.findById(id);
        if(heim.isPresent())
            return heim.get().getErkranktePersonen();
        else
            return new ArrayList<>();
    }

    @GetMapping("/heim/{id}/knopf")
    public Iterable<Knopf> heimKnoepfe(@PathVariable Integer id)
    {
        Optional<Heim> heim = heimRepository.findById(id);
        if(heim.isPresent())
            return heim.get().getKnoepfe();
        else
            return new ArrayList<>();
    }
}
