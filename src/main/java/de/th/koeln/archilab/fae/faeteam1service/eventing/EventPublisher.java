package de.th.koeln.archilab.fae.faeteam1service.eventing;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

@Component
public class EventPublisher implements ApplicationContextAware {
    private static final Logger LOGGER = LoggerFactory.getLogger(EventPublisher.class);
    private static ApplicationContext applicationContext;
    private final KafkaTemplate<String, String> template;
    private final ObjectMapper objectMapper;
    private final String topic;

    @Autowired
    public EventPublisher(final KafkaTemplate<String, String> template,
                          final ObjectMapper objectMapper,
                          @Value("${product.topic}") final String topic) {
        this.template = template;
        this.objectMapper = objectMapper;
        this.topic = topic;
    }

    public static EventPublisher lookup() {
        return applicationContext.getBean(EventPublisher.class);
    }

    public ListenableFuture<SendResult<String, String>> publishEvent(final Event event) {
        LOGGER.info("Publishing event {} to topic {}", event.getId(), this.topic);
        return template.send(this.topic, event.getKey(), this.eventToJson(event));
    }

    private String eventToJson(Event event) {
        try {
            return this.objectMapper.writeValueAsString(event);
        } catch (JsonProcessingException e) {
            LOGGER.error("Could not parse event {} : {}", event.toString(), e.getMessage());
            return "";
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        EventPublisher.applicationContext = applicationContext;
    }
}
