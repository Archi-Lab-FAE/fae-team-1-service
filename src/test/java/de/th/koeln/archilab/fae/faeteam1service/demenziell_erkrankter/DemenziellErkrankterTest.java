package de.th.koeln.archilab.fae.faeteam1service.demenziell_erkrankter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
    public void init() {
        demenziellErkrankter = new DemenziellErkrankter();
        kontaktperson = new Kontaktperson();
    }

    //Test GET /demenziell-erkrankte
    @Test
    public void demenziellErkranterShowsDataCorrectly() throws Exception {
        demenziellErkrankterRepository.save(demenziellErkrankter);

        mockMvc.perform(get("/demenziell-erkrankte"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$[0].id").value(demenziellErkrankter.getId()))
                .andExpect(jsonPath("$[0].name").isEmpty())
                .andExpect(jsonPath("$[0].vorname").isEmpty())
                .andExpect(jsonPath("$[0].zustimmung").isEmpty());
    }

    //Test successful GET /demenziell-erkrankte/{id}
    @Test
    public void demenziellErkranterWithIDShowsDataCorrectly() throws Exception {
        demenziellErkrankterRepository.save(demenziellErkrankter);

        mockMvc.perform(get("/demenziell-erkrankte/" + demenziellErkrankter.getId()))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id").value(demenziellErkrankter.getId()))
                .andExpect(jsonPath("$.name").isEmpty())
                .andExpect(jsonPath("$.vorname").isEmpty())
                .andExpect(jsonPath("$.zustimmung").isEmpty());
    }

    //Test failed GET /demenziell-erkrankte/{id}
    @Test
    public void demenziellErkranterWithIDisFailed() throws Exception {
        mockMvc.perform(get("/demenziell-erkrankte/1"))
                .andExpect(status().is4xxClientError());
    }

    /*
    TODO: fix erroneous tests
    //Test successful POST /demenziell-erkrankte
    @Test
    public void demenziellErkranterCreatesCorrectly() throws Exception {
        DemenziellErkrankter newDemenziellErkrankter = new DemenziellErkrankter();
        mockMvc.perform(post("/demenziell-erkrankte", newDemenziellErkrankter))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id").value(newDemenziellErkrankter.getId()))
                .andExpect(content().contentType("\"application/json;charset=UTF-8\""));
    }

    //Test successful PUT /demenziell-erkrankte/{id}
    @Test
    public void demenziellErkranterChangesCorrectly() throws Exception {
        demenziellErkrankter.setName("TestValue");
        mockMvc.perform(post("/demenziell-erkrankte/" + demenziellErkrankter.getId(), demenziellErkrankter))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType("\"application/json;charset=UTF-8\""));
    }*/

    //Test successful /demenziell-erkrankte/{id}
    @Test
    public void demenziellErkranterDeletesCorrectly() throws Exception {
        demenziellErkrankterRepository.save(demenziellErkrankter);

        mockMvc.perform(delete("/demenziell-erkrankte/" + demenziellErkrankter.getId()))
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(get("/demenziell-erkrankte/" + demenziellErkrankter.getId()))
                .andExpect(status().is4xxClientError());
    }

    //TODO until 12.01 @AyhanG
    //Body
    //REST
    //Class


}
