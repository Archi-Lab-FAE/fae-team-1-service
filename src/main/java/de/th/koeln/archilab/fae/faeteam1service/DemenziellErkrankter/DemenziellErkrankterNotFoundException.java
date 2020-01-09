package de.th.koeln.archilab.fae.faeteam1service.DemenziellErkrankter;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Demenziell Erkrankter not found")
public class DemenziellErkrankterNotFoundException extends RuntimeException {
}
