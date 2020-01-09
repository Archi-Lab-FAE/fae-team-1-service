package de.th.koeln.archilab.fae.faeteam1service.DemenziellErkrankter;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DemenziellErkrankterController {

    private final DemenziellErkrankterRepository demenziellErkrankterRepository;

    public DemenziellErkrankterController(DemenziellErkrankterRepository demenziellErkrankterRepository) {
        this.demenziellErkrankterRepository = demenziellErkrankterRepository;
    }

    /* #################################
       # Endpunkt DemenziellErkrankter #
       ################################# */

    @Operation(
            summary = "Alle Demenziell Erkrankten ausgeben",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Liste aller Demenziell Erkrankten wir ausgegeben",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = DemenziellErkrankter.class))
                            )
                    )
            }
    )
    @GetMapping("/demenziell-erkrankte")
    public Iterable<DemenziellErkrankter> getAllDemenziellErkrankte() {
        return demenziellErkrankterRepository.findAll();
    }

    @Operation(
            summary = "Demenziell Erkrankten mit ID ausgeben",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Demenziell Erkrankter mit gegebener ID wird ausgegeben",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = DemenziellErkrankter.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Demenziell Erkrankter mit gegebener ID existiert nicht",
                            content = @Content(
                                    mediaType = "text/html",
                                    schema = @Schema(
                                            type = "string",
                                            example = "Demenziell Erkrankter not found"
                                    )
                            )
                    )
            },
            parameters = {
                    @Parameter(
                            name = "id",
                            description = "ID des Demenziell Erkrankten",
                            required = true,
                            schema = @Schema(type = "string")
                    )
            }
    )
    @GetMapping("/demenziell-erkrankte/{id}")
    public DemenziellErkrankter getDemenziellErkrankterById(@PathVariable String id) {
        return demenziellErkrankterRepository.findById(id).orElseThrow(DemenziellErkrankterNotFoundException::new);
    }

    @Operation(
            summary = "Demenziell Erkrankten anlegen",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Demenziell Erkrankter wird angelegt und ausgegeben",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = DemenziellErkrankter.class)
                            )
                    )
            },
            parameters = {
                    @Parameter(
                            name = "DemenziellErkrankter",
                            description = "Demenziell Erkrankter",
                            required = true,
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = DemenziellErkrankter.class)
                            )
                    )
            }
    )
    @PostMapping("/demenziell-erkrankte")
    public DemenziellErkrankter createDemenziellErkrankter(@RequestBody DemenziellErkrankter demenziellErkrankter) {
        return demenziellErkrankterRepository.save(demenziellErkrankter);
    }

    @Operation(
            summary = "Demenziell Erkrankten mit ID aktualisieren",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Demenziell Erkrankter mit gegebener ID wird aktualisiert/angelegt und ausgegeben",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = DemenziellErkrankter.class)
                            )
                    )
            },
            parameters = {
                    @Parameter(
                            name = "DemenziellErkrankter",
                            description = "Demenziell Erkrankter",
                            required = true,
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = DemenziellErkrankter.class)
                            )
                    ),
                    @Parameter(
                            name = "id",
                            description = "ID des Demenziell Erkrankten",
                            required = true,
                            schema = @Schema(type = "string")
                    )
            }
    )
    @PutMapping("/demenziell-erkrankte/{id}")
    public DemenziellErkrankter updateDemenziellErkrankter(@RequestBody DemenziellErkrankter demenziellErkrankter, @PathVariable String id) {
        return demenziellErkrankterRepository.save(demenziellErkrankter);
    }

    @Operation(
            summary = "Demenziell Erkrankten mit ID löschen",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Demenziell Erkrankter mit gegebener ID wird gelöscht"
                    )
            },
            parameters = {
                    @Parameter(
                            name = "id",
                            description = "ID des Demenziell Erkrankten",
                            required = true,
                            schema = @Schema(type = "string")
                    )
            }
    )
    @DeleteMapping("/demenziell-erkrankte/{id}")
    public void deleteDemenziellErkrankterById(@PathVariable String id) {
        demenziellErkrankterRepository.deleteById(id);
    }

    /* ##########################
       # Endpunkt Kontaktperson #
       ########################## */

    @Operation(
            summary = "Alle Kontaktpersonen eines Demenziell Erkrankten ausgeben",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Liste aller Kontaktpersonen eines Demenziell Erkrankten wird ausgegeben",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Kontaktperson.class))
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Demenziell Erkrankter mit gegebener ID existiert nicht",
                            content = @Content(
                                    mediaType = "text/html",
                                    schema = @Schema(
                                            type = "string",
                                            example = "Demenziell Erkrankter not found"
                                    )
                            )
                    )
            },
            parameters = {
                    @Parameter(
                            name = "demenziellErkrankterId",
                            description = "ID des Demenziell Erkrankten",
                            required = true,
                            schema = @Schema(type = "string")
                    )
            }
    )
    @GetMapping("/demenziell-erkrankte/{demenziellErkrankterId}/kontaktpersonen")
    public Iterable<Kontaktperson> getAllKontaktpersonenForDemenziellErkrankten(@PathVariable String demenziellErkrankterId) {
        return demenziellErkrankterRepository.findById(demenziellErkrankterId).map(DemenziellErkrankter::getKontaktpersonen)
                .orElseThrow(DemenziellErkrankterNotFoundException::new);
    }

    @Operation(
            summary = "Kontaktperson mit ID eines Demenziell Erkrankten ausgeben",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Kontaktpersonen mit gegebener ID eines Demenziell Erkrankten wird ausgegeben",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Kontaktperson.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Demenziell Erkrankter mit gegebener ID existiert nicht oder Kontaktperson mit gegebener ID existiert für Demenziell Erkrankten nicht",
                            content = @Content(
                                    mediaType = "text/html",
                                    schema = @Schema(
                                            type = "string",
                                            example = "Kontaktperson not found"
                                    )
                            )
                    )
            },
            parameters = {
                    @Parameter(
                            name = "demenziellErkrankterId",
                            description = "ID des Demenziell Erkrankten",
                            required = true,
                            schema = @Schema(type = "string")
                    ),
                    @Parameter(
                            name = "kontaktPersonId",
                            description = "ID der Kontaktperson",
                            required = true,
                            schema = @Schema(type = "string")
                    )
            }
    )
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

    @Operation(
            summary = "Kontaktpersonen für Demenziell Erkrankten anlegen",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Kontaktperson wird angelegt und ausgegeben",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Kontaktperson.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Demenziell Erkrankter mit gegebener ID existiert nicht",
                            content = @Content(
                                    mediaType = "text/html",
                                    schema = @Schema(
                                            type = "string",
                                            example = "Demenziell Erkrankter not found"
                                    )
                            )
                    )
            },
            parameters = {
                    @Parameter(
                            name = "kontaktperson",
                            description = "Kontaktperson des Demenziell Erkrankten",
                            required = true,
                            schema = @Schema(implementation = Kontaktperson.class)
                    ),
                    @Parameter(
                            name = "demenziellErkrankterId",
                            description = "ID des Demenziell Erkrankten",
                            required = true,
                            schema = @Schema(type = "string")
                    )
            }
    )
    @PostMapping("/demenziell-erkrankte/{demenziellErkrankterId}/kontaktpersonen")
    public Kontaktperson createKontaktpersonForDemenziellErkrankten(@RequestBody Kontaktperson kontaktperson, @PathVariable String demenziellErkrankterId) {
        demenziellErkrankterRepository.save(demenziellErkrankterRepository.findById(demenziellErkrankterId).map(demenziellErkrankter -> {
            List<Kontaktperson> kontaktpersonen = demenziellErkrankter.getKontaktpersonen();
            kontaktpersonen.add(kontaktperson);
            demenziellErkrankter.setKontaktpersonen(kontaktpersonen);
            return demenziellErkrankter;
        }).orElseThrow(DemenziellErkrankterNotFoundException::new));
        return kontaktperson;
    }

    @Operation(
            summary = "Kontaktpersonen für Demenziell Erkrankten aktualisieren",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Kontaktperson wird aktualisiert/angelegt und ausgegeben",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Kontaktperson.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Demenziell Erkrankter mit gegebener ID existiert nicht",
                            content = @Content(
                                    mediaType = "text/html",
                                    schema = @Schema(
                                            type = "string",
                                            example = "Demenziell Erkrankter not found"
                                    )
                            )
                    )
            },
            parameters = {
                    @Parameter(
                            name = "kontaktperson",
                            description = "Kontaktperson des Demenziell Erkrankten",
                            required = true,
                            schema = @Schema(implementation = Kontaktperson.class)
                    ),
                    @Parameter(
                            name = "demenziellErkrankterId",
                            description = "ID des Demenziell Erkrankten",
                            required = true,
                            schema = @Schema(type = "string")
                    ),
                    @Parameter(
                            name = "kontaktPersonId",
                            description = "ID der Kontaktperson",
                            required = true,
                            schema = @Schema(type = "string")
                    )
            }
    )
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

    @Operation(
            summary = "Kontaktpersonen für Demenziell Erkrankten löschen",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Kontaktperson wird gelöscht"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Demenziell Erkrankter mit gegebener ID existiert nicht",
                            content = @Content(
                                    mediaType = "text/html",
                                    schema = @Schema(
                                            type = "string",
                                            example = "Demenziell Erkrankter not found"
                                    )
                            )
                    )
            },
            parameters = {
                    @Parameter(
                            name = "demenziellErkrankterId",
                            description = "ID des Demenziell Erkrankten",
                            required = true,
                            schema = @Schema(type = "string")
                    ),
                    @Parameter(
                            name = "kontaktPersonId",
                            description = "ID der Kontaktperson",
                            required = true,
                            schema = @Schema(type = "string")
                    )
            }
    )
    @DeleteMapping("/demenziell-erkrankte/{demenziellErkrankterId}/kontaktpersonen/{kontaktPersonId}")
    public void deleteKontaktpersonForDemenziellErkrankten(@PathVariable String demenziellErkrankterId, @PathVariable String kontaktPersonId) {
        demenziellErkrankterRepository.save(demenziellErkrankterRepository.findById(demenziellErkrankterId).map(demenziellErkrankter -> {
            List<Kontaktperson> kontaktpersonen = demenziellErkrankter.getKontaktpersonen();
            kontaktpersonen.removeIf(existingKontaktperson -> existingKontaktperson.getId().equals(kontaktPersonId));
            demenziellErkrankter.setKontaktpersonen(kontaktpersonen);
            return demenziellErkrankter;
        }).orElseThrow(DemenziellErkrankterNotFoundException::new));
    }

    /* ############################
       # Endpunkt Positionssender #
       ############################ */

    @Operation(
            summary = "Alle Positionssender eines Demenziell Erkrankten ausgeben",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Liste aller Positionssender eines Demenziell Erkrankten wird ausgegeben",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Positionssender.class))
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Demenziell Erkrankter mit gegebener ID existiert nicht",
                            content = @Content(
                                    mediaType = "text/html",
                                    schema = @Schema(
                                            type = "string",
                                            example = "Demenziell Erkrankter not found"
                                    )
                            )
                    )
            },
            parameters = {
                    @Parameter(
                            name = "demenziellErkrankterId",
                            description = "ID des Demenziell Erkrankten",
                            required = true,
                            schema = @Schema(type = "string")
                    )
            }
    )
    @GetMapping("/demenziell-erkrankte/{demenziellErkrankterId}/positionssender")
    public Iterable<Positionssender> getAllPositionssenderForDemenziellErkrankten(@PathVariable String demenziellErkrankterId) {
        return demenziellErkrankterRepository.findById(demenziellErkrankterId).map(DemenziellErkrankter::getPositionssender)
                .orElseThrow(DemenziellErkrankterNotFoundException::new);
    }

    @Operation(
            summary = "Positionssender mit ID eines Demenziell Erkrankten ausgeben",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Positionssender mit gegebener ID eines Demenziell Erkrankten wird ausgegeben",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Positionssender.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Demenziell Erkrankter mit gegebener ID existiert nicht oder Positionssender mit gegebener ID existiert für Demenziell Erkrankten nicht",
                            content = @Content(
                                    mediaType = "text/html",
                                    schema = @Schema(
                                            type = "string",
                                            example = "Positionssender not found"
                                    )
                            )
                    )
            },
            parameters = {
                    @Parameter(
                            name = "demenziellErkrankterId",
                            description = "ID des Demenziell Erkrankten",
                            required = true,
                            schema = @Schema(type = "string")
                    ),
                    @Parameter(
                            name = "positionssenderId",
                            description = "ID des Positionssenders",
                            required = true,
                            schema = @Schema(type = "string")
                    )
            }
    )
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

    @Operation(
            summary = "Positionssender für Demenziell Erkrankten anlegen",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Positionssender wird angelegt und ausgegeben",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Positionssender.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Demenziell Erkrankter mit gegebener ID existiert nicht",
                            content = @Content(
                                    mediaType = "text/html",
                                    schema = @Schema(
                                            type = "string",
                                            example = "Demenziell Erkrankter not found"
                                    )
                            )
                    )
            },
            parameters = {
                    @Parameter(
                            name = "positionssender",
                            description = "Positionssender des Demenziell Erkrankten",
                            required = true,
                            schema = @Schema(implementation = Positionssender.class)
                    ),
                    @Parameter(
                            name = "demenziellErkrankterId",
                            description = "ID des Demenziell Erkrankten",
                            required = true,
                            schema = @Schema(type = "string")
                    )
            }
    )
    @PostMapping("/demenziell-erkrankte/{demenziellErkrankterId}/positionssender")
    public Positionssender createPositionssenderForDemenziellErkrankten(@RequestBody Positionssender positionssender, @PathVariable String demenziellErkrankterId) {
        demenziellErkrankterRepository.save(demenziellErkrankterRepository.findById(demenziellErkrankterId).map(demenziellErkrankter -> {
            List<Positionssender> positionssenderList = demenziellErkrankter.getPositionssender();
            positionssenderList.add(positionssender);
            demenziellErkrankter.setPositionssender(positionssenderList);
            return demenziellErkrankter;
        }).orElseThrow(DemenziellErkrankterNotFoundException::new));
        return positionssender;
    }

    @Operation(
            summary = "Positionssender für Demenziell Erkrankten aktualisieren",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Positionssender wird aktualisiert/angelegt und ausgegeben",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Positionssender.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Demenziell Erkrankter mit gegebener ID existiert nicht",
                            content = @Content(
                                    mediaType = "text/html",
                                    schema = @Schema(
                                            type = "string",
                                            example = "Demenziell Erkrankter not found"
                                    )
                            )
                    )
            },
            parameters = {
                    @Parameter(
                            name = "positionssender",
                            description = "Positionssender des Demenziell Erkrankten",
                            required = true,
                            schema = @Schema(implementation = Positionssender.class)
                    ),
                    @Parameter(
                            name = "demenziellErkrankterId",
                            description = "ID des Demenziell Erkrankten",
                            required = true,
                            schema = @Schema(type = "string")
                    ),
                    @Parameter(
                            name = "positionssenderId",
                            description = "ID des Positionssenders",
                            required = true,
                            schema = @Schema(type = "string")
                    )
            }
    )
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

    @Operation(
            summary = "Positionssender für Demenziell Erkrankten löschen",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Positionssender wird gelöscht"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Demenziell Erkrankter mit gegebener ID existiert nicht",
                            content = @Content(
                                    mediaType = "text/html",
                                    schema = @Schema(
                                            type = "string",
                                            example = "Demenziell Erkrankter not found"
                                    )
                            )
                    )
            },
            parameters = {
                    @Parameter(
                            name = "demenziellErkrankterId",
                            description = "ID des Demenziell Erkrankten",
                            required = true,
                            schema = @Schema(type = "string")
                    ),
                    @Parameter(
                            name = "positionssenderId",
                            description = "ID des Positionssenders",
                            required = true,
                            schema = @Schema(type = "string")
                    )
            }
    )
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
