package de.th.koeln.archilab.fae.faeteam1service.demenziell_erkrankter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import scala.reflect.internal.Symbols;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    private DemenziellErkrankter demenziellErkrankterOhneKontaktperson;
    private Kontaktperson kontaktperson;
    private Kontaktperson kontaktperson2;
    private List<Kontaktperson> kontaktpersonen;
    private Positionssender positionssender;
    private Positionssender positionssender2;
    private List<Positionssender> positionssenderListe;

    @Before
    public void init() {
        kontaktperson = new Kontaktperson(new KontaktpersonDTO("KPName","KPVorname","KPTel",true));
        kontaktperson2 = new Kontaktperson(new KontaktpersonDTO("KP2Name","KP2Vorname","KP2Tel",true));
        kontaktpersonen = new ArrayList<>();
        kontaktpersonen.add(kontaktperson);
        kontaktpersonen.add(kontaktperson2);
        positionssender = new Positionssender(new PositionssenderDTO(new Date()));
        positionssender2 = new Positionssender(new PositionssenderDTO(new Date()));
        positionssenderListe = new ArrayList<>();
        positionssenderListe.add(positionssender);
        positionssenderListe.add(positionssender2);
        demenziellErkrankter = new DemenziellErkrankter(new DemenziellErkrankterDTO("TestName","TestVorname",true,kontaktpersonen,positionssenderListe));
        demenziellErkrankterOhneKontaktperson = new DemenziellErkrankter(new DemenziellErkrankterDTO("TestName","TestVorname",true,null,positionssenderListe));
    }

    //Test GET /demenziell-erkrankte
    @Test
    public void demenziellErkranterShowsDataCorrectly() throws Exception {
        demenziellErkrankterRepository.save(demenziellErkrankter);

        mockMvc.perform(get("/demenziell-erkrankte"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$[0].name").isNotEmpty())
                .andExpect(jsonPath("$[0].vorname").isNotEmpty())
                .andExpect(jsonPath("$[0].zustimmung").isNotEmpty());
    }

    //Test successful GET /demenziell-erkrankte/{id}
    @Test
    public void demenziellErkranterWithIDShowsDataCorrectly() throws Exception {
        demenziellErkrankterRepository.save(demenziellErkrankter);

        mockMvc.perform(get("/demenziell-erkrankte/" + demenziellErkrankter.getId()))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id").value(demenziellErkrankter.getId()))
                .andExpect(jsonPath("$.name").isNotEmpty())
                .andExpect(jsonPath("$.vorname").isNotEmpty())
                .andExpect(jsonPath("$.zustimmung").isNotEmpty());
    }

    //Test failed GET /demenziell-erkrankte/{id}
    @Test
    public void demenziellErkranterWithIDisFailed() throws Exception {
        mockMvc.perform(get("/demenziell-erkrankte/" + this.demenziellErkrankter.getId()))
                .andExpect(status().is4xxClientError());
    }

    //Test error POST /demenziell-erkrankte
    @Test
    public void demenziellErkranterCreatesCorrectly() throws Exception {
        this.demenziellErkrankter = new DemenziellErkrankter(new DemenziellErkrankterDTO("TestName","TestVorname",true,kontaktpersonen,positionssenderListe));
        mockMvc.perform(post("/demenziell-erkrankte", this.demenziellErkrankter))
                .andExpect(status().is4xxClientError());
    }

    //Test error PUT /demenziell-erkrankte/{id}
    @Test
    public void demenziellErkranterChangesCorrectly() throws Exception {
        this.demenziellErkrankter.setName("TestNewName");
        mockMvc.perform(put("/demenziell-erkrankte/" + this.demenziellErkrankter.getId(), this.demenziellErkrankter))
                .andExpect(status().is4xxClientError());
    }

    //Test DELETE successful /demenziell-erkrankte/{id}
    @Test
    public void demenziellErkranterDeletesCorrectly() throws Exception {
        demenziellErkrankterRepository.save(this.demenziellErkrankter);

        mockMvc.perform(delete("/demenziell-erkrankte/" + this.demenziellErkrankter.getId()))
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(get("/demenziell-erkrankte/" + this.demenziellErkrankter.getId()))
                .andExpect(status().is4xxClientError());
    }

    //Test GET /demenziell-erkrankte/{demenziellErkrankterId}/kontaktpersonen
    @Test
    public void demenziellErkranterShowsKontaktpersonenCorrectly() throws Exception {
        demenziellErkrankterRepository.save(demenziellErkrankter);

        mockMvc.perform(get("/demenziell-erkrankte/" + demenziellErkrankter.getId() + "/kontaktpersonen"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$[0].id").value(demenziellErkrankter.getKontaktpersonen().get(0).getId()))
                .andExpect(jsonPath("$[0].name").isNotEmpty())
                .andExpect(jsonPath("$[0].vorname").isNotEmpty())
                .andExpect(jsonPath("$[0].telefonnummer").isNotEmpty())
                .andExpect(jsonPath("$[0].aktiv").isNotEmpty())
                .andExpect(jsonPath("$[1].id").value(demenziellErkrankter.getKontaktpersonen().get(1).getId()))
                .andExpect(jsonPath("$[1].name").isNotEmpty())
                .andExpect(jsonPath("$[1].vorname").isNotEmpty())
                .andExpect(jsonPath("$[1].telefonnummer").isNotEmpty())
                .andExpect(jsonPath("$[1].aktiv").isNotEmpty());
    }

    //Test GET With Error /demenziell-erkrankte/{demenziellErkrankterId}/kontaktpersonen
    @Test
    public void demenziellErkranterShowsKontaktpersonenIDIsFalse() throws Exception
    {
        mockMvc.perform(get("/demenziell-erkrankte/TestID-12345678/kontaktpersonen"))
                .andExpect(status().is4xxClientError());
    }

    //Test GET With Error /demenziell-erkrankte/{demenziellErkrankterId}/kontaktpersonen/{kontaktPersonId}
    @Test
    public void demenziellErkranterShowsKontaktpersonIDIsFalse() throws Exception
    {
        demenziellErkrankterRepository.save(demenziellErkrankter);

        mockMvc.perform(get("/demenziell-erkrankte/" + this.demenziellErkrankter.getId() + "/kontaktpersonen/TestID-123456"))
                .andExpect(status().is4xxClientError());
    }

    //Test error POST /demenziell-erkrankte/{demenziellErkrankterId}/kontaktpersonen
    @Test
    public void kontaktpersonCreatesCorrectly() throws Exception
    {
        mockMvc.perform(post("/demenziell-erkrankte/" + this.demenziellErkrankterOhneKontaktperson.getId() + "/kontaktpersonen", this.kontaktperson))
                .andExpect(status().is4xxClientError());
    }

    //Test error POST /demenziell-erkrankte/{demenziellErkrankterId}/kontaktpersonen
    @Test
    public void kontaktpersonCouldntCreateCorrectly() throws Exception
    {
        mockMvc.perform(post("/demenziell-erkrankte/TestID-12345678/kontaktpersonen", this.kontaktperson))
                .andExpect(status().is4xxClientError());
    }

    //Test error PUT /demenziell-erkrankte/{demenziellErkrankterId}/kontaktpersonen/{kontaktPersonId}
    @Test
    public void kontaktpersonChangesCorrectly() throws Exception
    {
        demenziellErkrankterRepository.save(demenziellErkrankter);

        this.kontaktperson.setName("TestNewKPName");
        mockMvc.perform(put("/demenziell-erkrankte/" + this.demenziellErkrankter.getId() + "/kontaktpersonen/" + this.demenziellErkrankter.getKontaktpersonen().get(0).getId(), this.kontaktperson))
                .andExpect(status().is4xxClientError());
    }

    //Test error PUT /demenziell-erkrankte/{demenziellErkrankterId}/kontaktpersonen/{kontaktPersonId}
    @Test
    public void kontaktpersonDoesntChange() throws Exception
    {
        demenziellErkrankterRepository.save(demenziellErkrankter);

        this.kontaktperson.setName("TestNewKPName");
        mockMvc.perform(put("/demenziell-erkrankte/" + this.demenziellErkrankter.getId() + "/kontaktpersonen/" + this.demenziellErkrankter.getKontaktpersonen().get(0).getId(), this.kontaktperson))
                .andExpect(status().is4xxClientError());
    }

    //Test DELETE successful /demenziell-erkrankte/{demenziellErkrankterId}/kontaktpersonen/{kontaktPersonId}
    @Test
    public void kontaktpersonDeletesCorrectly() throws Exception {
        demenziellErkrankterRepository.save(this.demenziellErkrankter);

        mockMvc.perform(delete("/demenziell-erkrankte/" + this.demenziellErkrankter.getId() + "/kontaktpersonen/" + this.demenziellErkrankter.getKontaktpersonen().get(1).getId()))
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(get("/demenziell-erkrankte/" + this.demenziellErkrankter.getId() + "/kontaktpersonen/" + demenziellErkrankter.getKontaktpersonen().get(1).getId()))
                .andExpect(status().is4xxClientError());
    }

    //Test DELETE error /demenziell-erkrankte/{demenziellErkrankterId}/kontaktpersonen/{kontaktPersonId}
    @Test
    public void kontaktpersonCouldntDelete() throws Exception {
        demenziellErkrankterRepository.save(this.demenziellErkrankter);

        mockMvc.perform(delete("/demenziell-erkrankte/TestID-12345678/kontaktpersonen/" + this.demenziellErkrankter.getKontaktpersonen().get(1).getId()))
                .andExpect(status().is4xxClientError());
    }

    //Test GET successful /demenziell-erkrankte/{demenziellErkrankterId}/positionssender
    @Test
    public void positionssendersShowsDataCorrectly() throws Exception {
        demenziellErkrankterRepository.save(demenziellErkrankter);

        mockMvc.perform(get("/demenziell-erkrankte/" + this.demenziellErkrankter.getId() + "/positionssender"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$[0].letzteWartung").isNotEmpty())
                .andExpect(jsonPath("$[1].letzteWartung").isNotEmpty());
    }

    //Test GET error /demenziell-erkrankte/{demenziellErkrankterId}/positionssender
    @Test
    public void positionssendersDoesntShowData() throws Exception {
        demenziellErkrankterRepository.save(demenziellErkrankter);

        mockMvc.perform(get("/demenziell-erkrankte/TestID-12345678/positionssender"))
                .andExpect(status().is4xxClientError());
    }

    //Test GET error /demenziell-erkrankte/{demenziellErkrankterId}/positionssender/{positionssenderId}
    @Test
    public void positionssenderDoesntShowData() throws Exception {
        demenziellErkrankterRepository.save(demenziellErkrankter);

        mockMvc.perform(get("/demenziell-erkrankte/TestID-12345678/positionssender/TestID2-12345678"))
                .andExpect(status().is4xxClientError());
    }

    //Test POST error /demenziell-erkrankte/{demenziellErkrankterId}/positionssender
    @Test
    public void positionssenderCreatesCorrectly() throws Exception
    {
        mockMvc.perform(post("/demenziell-erkrankte/" + this.demenziellErkrankter.getId() + "/positionssender", this.positionssender2))
                .andExpect(status().is4xxClientError());
    }

    //Test POST error /demenziell-erkrankte/{demenziellErkrankterId}/positionssender
    @Test
    public void positionssenderDoesntCreateCorrectly() throws Exception
    {
        mockMvc.perform(post("/demenziell-erkrankte/TestID-12345678/positionssender", this.positionssender2))
                .andExpect(status().is4xxClientError());
    }

    //Test error PUT /demenziell-erkrankte/{demenziellErkrankterId}/positionssender/{positionssenderId}
    @Test
    public void positionssenderChangesCorrectly() throws Exception
    {
        demenziellErkrankterRepository.save(demenziellErkrankter);

        this.positionssender.setLetzteWartung(new Date());
        mockMvc.perform(put("/demenziell-erkrankte/" + this.demenziellErkrankter.getId() + "/positionssender/" + this.positionssender.getId(), this.positionssender))
                .andExpect(status().is4xxClientError());
    }

    //Test error PUT /demenziell-erkrankte/{demenziellErkrankterId}/positionssender/{positionssenderId}
    @Test
    public void positionssenderDoesntChange() throws Exception
    {
        demenziellErkrankterRepository.save(demenziellErkrankter);

        this.positionssender.setLetzteWartung(new Date());
        mockMvc.perform(put("/demenziell-erkrankte/" + this.demenziellErkrankter.getId() + "/positionssender/TestID-12345678", this.positionssender))
                .andExpect(status().is4xxClientError());
    }

    //Test successful DELETE /demenziell-erkrankte/{demenziellErkrankterId}/positionssender/{positionssenderId}
    @Test
    public void positionssenderDeletesCorrectly() throws Exception {
        demenziellErkrankterRepository.save(this.demenziellErkrankter);

        mockMvc.perform(delete("/demenziell-erkrankte/" + this.demenziellErkrankter.getId() + "/positionssender/" + this.demenziellErkrankter.getPositionssender().get(0).getId()))
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(get("/demenziell-erkrankte/" + this.demenziellErkrankter.getId() + "/positionssender/" + this.demenziellErkrankter.getKontaktpersonen().get(0).getId()))
                .andExpect(status().is4xxClientError());
    }

    //Test error DELETE /demenziell-erkrankte/{demenziellErkrankterId}/positionssender/{positionssenderId}
    @Test
    public void positionssenderDoesntDelete() throws Exception {
        demenziellErkrankterRepository.save(this.demenziellErkrankter);

        mockMvc.perform(delete("/demenziell-erkrankte/TestID-12345678/positionssender/TestID2-12345678"))
                .andExpect(status().is4xxClientError());
    }
}
