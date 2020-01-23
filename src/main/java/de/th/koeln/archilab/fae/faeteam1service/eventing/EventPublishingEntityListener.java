package de.th.koeln.archilab.fae.faeteam1service.eventing;

import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

public class EventPublishingEntityListener {

    @PrePersist
    void onPersist(EventEntity entity) { publishEvent(entity, "created"); }

    @PreUpdate
    public void onUpdate(EventEntity entity) {
        publishEvent(entity, "updated");
    }

    @PreRemove
    public void onRemove(EventEntity entity) {
        publishEvent(entity, "deleted");
    }

    private void publishEvent(EventEntity entity, String action) {
        EventPublisher.lookup().publishEvent(EventFactory.createEvent(entity, action));
    }
}
