package de.th.koeln.archilab.fae.faeteam1service.demenziellErkrankter;

import de.th.koeln.archilab.fae.faeteam1service.demenziell_erkrankter.DemenziellErkrankter;
import de.th.koeln.archilab.fae.faeteam1service.demenziell_erkrankter.DemenziellErkrankterRepository;
import de.th.koeln.archilab.fae.faeteam1service.demenziell_erkrankter.Kontaktperson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DemenziellErkrankterTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    DemenziellErkrankterRepository demenziellErkrankterRepository;

    private DemenziellErkrankter demenziellErkrankter;
    private Kontaktperson kontaktperson;

    @Before
    public void init()
    {
        demenziellErkrankter = new DemenziellErkrankter();
        kontaktperson = new Kontaktperson();
    }

    //Test GET /demenziell-erkrankte
    @Test
    public void demenziellErkranterShowsDataCorrectly() throws Exception {
        demenziellErkrankterRepository.save(demenziellErkrankter);

        mockMvc.perform(get("/demenziell-erkrankte"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$[0].id").value(demenziellErkrankter.getId().toString()))
                .andExpect(jsonPath("$[0].name").isEmpty())
                .andExpect(jsonPath("$[0].vorname").isEmpty())
                .andExpect(jsonPath("$[0].zustimmung").isEmpty());
    }

    //Test successful GET /demenziell-erkrankte/{id}
    @Test
    public void demenziellErkranterWithIDShowsDataCorrectly() throws Exception {
        demenziellErkrankterRepository.save(demenziellErkrankter);

        mockMvc.perform(get("/demenziell-erkrankte/" + demenziellErkrankter.getId().toString()))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$[0].id").value(demenziellErkrankter.getId().toString()))
                .andExpect(jsonPath("$[0].name").isEmpty())
                .andExpect(jsonPath("$[0].vorname").isEmpty())
                .andExpect(jsonPath("$[0].zustimmung").isEmpty());
    }

    //Test failed GET /demenziell-erkrankte/{id}
    @Test
    public void demenziellErkranterWithIDisFailed() throws Exception {
        mockMvc.perform(get("/demenziell-erkrankte/1"))
                .andExpect(status().is4xxClientError());
    }

    //Test successful POST /demenziell-erkrankte
    @Test
    public void demenziellErkranterCreatesCorrectly() throws Exception {
        mockMvc.perform(post("/demenziell-erkrankte"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType("\"application/json;charset=UTF-8\""));
    }

    //Test successful PUT /demenziell-erkrankte/{id}
    @Test
    public void demenziellErkranterChangesCorrectly() throws Exception {
        mockMvc.perform(post("/demenziell-erkrankte/{id}"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType("\"application/json;charset=UTF-8\""));
    }

    //Test successful /demenziell-erkrankte/{id}
    @Test
    public void demenziellErkranterDeletesCorrectly() throws Exception {
        demenziellErkrankterRepository.save(demenziellErkrankter);

        mockMvc.perform(delete("/demenziell-erkrankte/" + demenziellErkrankter.getId().toString()))
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(get("/demenziell-erkrankte/" +  + demenziellErkrankter.getId().toString()))
                .andExpect(status().is4xxClientError());
    }

    //TODO until 12.01 @AyhanG
    //Body
    //REST
    //Class


}
