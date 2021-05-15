package omu.dddd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import omu.dddd.domain.Adventurer;
import omu.dddd.domain.IAdventurerRepository;
import omu.dddd.domain.IPartyRepository;
import omu.dddd.domain.Party;
import omu.dddd.domain.Race;
import omu.dddd.presentation.AdventurerCreateParam;
import omu.dddd.presentation.PartyCreateParam;

@SpringBootTest
@AutoConfigureMockMvc
public class GuildControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
  
    @Autowired
    private IAdventurerRepository adventurerRepository;
    @Autowired
    private IPartyRepository partyRepository;

    @Test
    @Sql(scripts = "sql/testAdventurers.sql")
    public void testAdventurers() throws Exception {

        String response = 
            mockMvc.perform(
                get("/api/guild/adventurers")
                )
            .andExpect(status().isOk()).andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        JSONArray responseJsonArray = new JSONArray(response);
        
        assertEquals(3, responseJsonArray.length());
        assertEquals("冒険者1", responseJsonArray.getJSONObject(0).getString("name"));
        assertEquals("冒険者2", responseJsonArray.getJSONObject(1).getString("name"));
        assertEquals("冒険者3", responseJsonArray.getJSONObject(2).getString("name"));
    }

    @Test
    public void testCreateAdventurer() throws Exception {

        AdventurerCreateParam acp = new AdventurerCreateParam();
        acp.setName("testCreateAdventurer");
        acp.setRace(Race.Human);
        acp.setAgility(1);
        acp.setDexterity(2);
        acp.setIntelligence(3);
        acp.setLuck(4);
        acp.setMind(5);
        acp.setReflex(6);
        acp.setStrength(7);
        acp.setVitality(8);
        acp.setWisdom(9);
        ObjectMapper mapper = new ObjectMapper();

        String response = 
            mockMvc.perform(
                post("/api/guild/adventurer/create")
                    .content(mapper.writeValueAsString(acp))
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                )
            .andExpect(status().isOk()).andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        JSONObject responseJson = new JSONObject(response);

        Adventurer adventurer = adventurerRepository.findById(responseJson.getInt("id"));
        assertEquals(acp.getName(), adventurer.getName());
    }

    @Test
    public void testCreateAdventurer_OverMaxTotalParameters() throws Exception {

        AdventurerCreateParam acp = new AdventurerCreateParam();
        acp.setName("testCreateAdventurer");
        acp.setRace(Race.Human);
        acp.setAgility(1);
        acp.setDexterity(2);
        acp.setIntelligence(3);
        acp.setLuck(4);
        acp.setMind(5);
        acp.setReflex(6);
        acp.setStrength(7);
        acp.setVitality(8);
        acp.setWisdom(10);
        ObjectMapper mapper = new ObjectMapper();

        mockMvc.perform(
            post("/api/guild/adventurer/create")
                .content(mapper.writeValueAsString(acp))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
            )
        .andExpect(status().isBadRequest());
    }

    @Test
    @Sql(scripts = "sql/testParties.sql")
    public void testParties() throws Exception {

        String response = 
            mockMvc.perform(
                get("/api/guild/parties")
                )
            .andExpect(status().isOk()).andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        JSONArray responseJsonArray = new JSONArray(response);
        
        assertEquals(3, responseJsonArray.length());
        assertEquals("パーティ1", responseJsonArray.getJSONObject(0).getString("name"));
        assertEquals("パーティ2", responseJsonArray.getJSONObject(1).getString("name"));
        assertEquals("パーティ3", responseJsonArray.getJSONObject(2).getString("name"));
    }

    @Test
    public void testCreateParty() throws Exception {

        PartyCreateParam pcp = new PartyCreateParam();
        pcp.setName("testCreateParty");

        ObjectMapper mapper = new ObjectMapper();

        String response = 
            mockMvc.perform(
                post("/api/guild/party/create")
                    .content(mapper.writeValueAsString(pcp))
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                )
            .andExpect(status().isOk()).andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        JSONObject responseJson = new JSONObject(response);

        Party party = partyRepository.findById(responseJson.getInt("id"));
        assertEquals(pcp.getName(), party.getName());
    }

}