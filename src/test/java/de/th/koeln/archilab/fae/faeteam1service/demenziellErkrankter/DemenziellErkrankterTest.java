package de.th.koeln.archilab.fae.faeteam1service.demenziellErkrankter;

import de.th.koeln.archilab.fae.faeteam1service.DemenziellErkrankter.DemenziellErkrankter;
import de.th.koeln.archilab.fae.faeteam1service.DemenziellErkrankter.DemenziellErkrankterRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

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

    @Before
    public void init()
    {
        demenziellErkrankter = new DemenziellErkrankter();
    }

    @Test
    public void demenziellErkranterShouldBeAddedSuccessfully() throws Exception {
        demenziellErkrankterRepository.save(demenziellErkrankter);

        mockMvc.perform(get("/demenziell-erkrankte"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$[0].id").value(demenziellErkrankter.getId().toString()))
                .andExpect(jsonPath("$[0].name").isEmpty())
                .andExpect(jsonPath("$[0].vorname").isEmpty())
                .andExpect(jsonPath("$[0].zustimmung").isEmpty());
    }
}
