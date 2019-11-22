package de.th.koeln.archilab.fae.faeteam1service.alarmknopf;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AlarmknopfController.class)
public class AlarmknopfControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AlarmknopfRepository alarmknopfRepository;

    @Test
    void getAllAlarmknoepfe() throws Exception {
        Point2D.Double position = new Point2D.Double(3.3436, 2.8894302);
        String telefonnummer = "90274398";
        Alarmknopf alarmknopf = new Alarmknopf(position, telefonnummer);
        UUID uuid = alarmknopf.getId();
        List<Alarmknopf> alarmknoepfe = new ArrayList<>();
        alarmknoepfe.add(alarmknopf);

        given(this.alarmknopfRepository.findAll()).willReturn(alarmknoepfe);

        this.mvc.perform(get("/alarmknoepfe")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(uuid.toString()))
                .andExpect(jsonPath("$[0].position.x").value(position.x))
                .andExpect(jsonPath("$[0].position.y").value(position.y))
                .andExpect(jsonPath("$[0].telefonnummer").value(telefonnummer));
    }

    @Test
    void getAlarmknopf() throws Exception {
        Point2D.Double position = new Point2D.Double(3.3436, 2.8894302);
        String telefonnummer = "90274398";
        Alarmknopf alarmknopf = new Alarmknopf(position, telefonnummer);
        UUID uuid = alarmknopf.getId();

        given(this.alarmknopfRepository.findById(uuid)).willReturn(Optional.of(alarmknopf));

        this.mvc.perform(get("/alarmknoepfe/" + uuid.toString())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(uuid.toString()))
                .andExpect(jsonPath("$.position.x").value(position.x))
                .andExpect(jsonPath("$.position.y").value(position.y))
                .andExpect(jsonPath("$.telefonnummer").value(telefonnummer));
    }

    @Test
    void postAlarmknopf() throws Exception {
        Point2D.Double position = new Point2D.Double(3.3436, 2.8894302);
        String telefonnummer = "90274398";
        Alarmknopf alarmknopf = new Alarmknopf(position, telefonnummer);
        UUID uuid = alarmknopf.getId();

        given(this.alarmknopfRepository.save(alarmknopf)).willReturn(alarmknopf);

        this.mvc.perform(post("/alarmknoepfe")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(alarmknopf)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(uuid.toString()))
                .andExpect(jsonPath("$.position.x").value(position.x))
                .andExpect(jsonPath("$.position.y").value(position.y))
                .andExpect(jsonPath("$.telefonnummer").value(telefonnummer));
    }

    @Test
    void putAlarmknopf() throws Exception {
        Point2D.Double position = new Point2D.Double(3.3436, 2.8894302);
        String telefonnummer = "90274398";
        Alarmknopf alarmknopf = new Alarmknopf(position, telefonnummer);
        UUID uuid = alarmknopf.getId();

        given(this.alarmknopfRepository.save(alarmknopf)).willReturn(alarmknopf);

        this.mvc.perform(put("/alarmknoepfe/" + uuid.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(alarmknopf)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(uuid.toString()))
                .andExpect(jsonPath("$.position.x").value(position.x))
                .andExpect(jsonPath("$.position.y").value(position.y))
                .andExpect(jsonPath("$.telefonnummer").value(telefonnummer));
    }

    @Test
    void deleteAllAlarmknoepfe() throws Exception {
        this.mvc.perform(delete("/alarmknoepfe"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteAlarmknopf() throws Exception {
        Point2D.Double position = new Point2D.Double(3.3436, 2.8894302);
        String telefonnummer = "90274398";
        Alarmknopf alarmknopf = new Alarmknopf(position, telefonnummer);
        UUID uuid = alarmknopf.getId();

        this.mvc.perform(delete("/alarmknoepfe/" + uuid.toString()))
                .andExpect(status().isOk());
    }
}
