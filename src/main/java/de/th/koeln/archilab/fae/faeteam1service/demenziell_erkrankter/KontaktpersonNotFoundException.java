package de.th.koeln.archilab.fae.faeteam1service.demenziell_erkrankter;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Kontaktperson not found")
public class KontaktpersonNotFoundException extends RuntimeException {
}
