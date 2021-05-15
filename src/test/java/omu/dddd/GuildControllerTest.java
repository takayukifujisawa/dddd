package omu.dddd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import omu.dddd.domain.Adventurer;
import omu.dddd.domain.IAdventurerRepository;
import omu.dddd.domain.Race;
import omu.dddd.presentation.AdventurerCreateParam;

@SpringBootTest
@AutoConfigureMockMvc
public class GuildControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
  
    @Autowired
    private IAdventurerRepository adventurerRepository;

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
            .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        JSONObject responseJson = new JSONObject(response);

        Adventurer adventurer = adventurerRepository.findById(responseJson.getInt("id"));
        assertEquals(acp.getName(), adventurer.getName());
    }

    @Test
    public void testCreateAdventurer_OverMaxTotalParameters() throws Exception {

        AdventurerCreateParam acp = new AdventurerCreateParam();
        acp.setName("testCreateAdventurer_OverMaxTotalParameters");
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
}