package de.th.koeln.archilab.fae.faeteam1service.demenziellErkrankter;

import de.th.koeln.archilab.fae.faeteam1service.DemenziellErkrankter.DemenziellErkrankter;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

import de.th.koeln.archilab.fae.faeteam1service.DemenziellErkrankter.DemenziellErkrankterRepository;
import de.th.koeln.archilab.fae.faeteam1service.DemenziellErkrankter.Kontaktperson;
import de.th.koeln.archilab.fae.faeteam1service.DemenziellErkrankter.Positionssender;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
