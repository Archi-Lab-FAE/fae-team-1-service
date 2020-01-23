package de.th.koeln.archilab.fae.faeteam1service.eventing;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@JsonPropertyOrder({"id", "key", "version", "timestamp", "type", "payload"})
public class Event implements Serializable {
    private final String id = UUID.randomUUID().toString();
    private String key;
    private Long version;
    private final LocalDateTime timestamp = LocalDateTime.now();
    private String type = "";
    private EventEntity payload;
}
