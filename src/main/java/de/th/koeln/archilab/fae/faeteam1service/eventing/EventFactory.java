package de.th.koeln.archilab.fae.faeteam1service.eventing;

import org.springframework.stereotype.Component;

@Component
public class EventFactory {
    public static Event createEvent(EventEntity entity, String action) {
        Event event = new Event();
        event.setKey(entity.getId());
        event.setVersion(0L);
        event.setType(action);
        event.setPayload(entity);
        return event;
    }
}
