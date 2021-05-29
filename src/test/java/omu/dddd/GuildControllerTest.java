package omu.dddd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import omu.dddd.domain.Adventurer;
import omu.dddd.domain.IAdventurerRepository;
import omu.dddd.domain.IPartyRepository;
import omu.dddd.domain.Party;
import omu.dddd.domain.PartyMembers;
import omu.dddd.domain.Race;
import omu.dddd.presentation.AdventurerCreateParam;
import omu.dddd.presentation.JoinPartyParam;
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
    
    @Nested
    class Mocked {
                
        @Autowired
        private MockMvc mockMvc;

        @MockBean
        private IAdventurerRepository adventurerRepository;

        @MockBean
        private IPartyRepository partyRepository;

        @Test
        public void testAdventurer() throws Exception {
            when(adventurerRepository.findById(1))
                .thenReturn(
                    new Adventurer(1, "冒険者1", Race.Human, 0,0,0,0,0,0,0,0,0)
                );
            
            String response = 
                mockMvc.perform(
                    get("/api/guild/adventurer/1")
                )
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
                
                JSONObject responseJson = new JSONObject(response);
                
                assertEquals("冒険者1", responseJson.getString("name"));
        }

        @Test
        public void testAdventurers() throws Exception {
            when(adventurerRepository.findAll())
                .thenReturn(new ArrayList<Adventurer>(
                    Arrays.asList(
                        new Adventurer(1, "冒険者1", Race.Human, 0,0,0,0,0,0,0,0,0),
                        new Adventurer(2, "冒険者2", Race.Human, 0,0,0,0,0,0,0,0,0),
                        new Adventurer(3, "冒険者3", Race.Human, 0,0,0,0,0,0,0,0,0)
                    )
                ));
            
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
        public void testParties() throws Exception {
            when(partyRepository.findAll())
                .thenReturn(new ArrayList<Party>(
                    Arrays.asList(
                        new Party(1, "パーティ1"),
                        new Party(2, "パーティ2"),
                        new Party(3, "パーティ3")
                    )
                ));

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
        public void testJoinParty() throws Exception {
            PartyMembers partyMembers = new PartyMembers(
                new ArrayList<Adventurer>(
                    Arrays.asList(
                        new Adventurer(1, "冒険者1", Race.Human, 0,0,0,0,0,0,0,0,0),
                        new Adventurer(2, "冒険者2", Race.Human, 0,0,0,0,0,0,0,0,0),
                        new Adventurer(3, "冒険者3", Race.Human, 0,0,0,0,0,0,0,0,0)
                    )
            ));
            when(partyRepository.getPartyMembers(1)).thenReturn(partyMembers);
    
            JoinPartyParam jpp = new JoinPartyParam();
            jpp.setTargetPartyId(1);
            jpp.setTargetAdventurerId(1);
            
            ObjectMapper mapper = new ObjectMapper();
    
            String response = 
                mockMvc.perform(
                    post("/api/guild/party/join")
                    .content(mapper.writeValueAsString(jpp))
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                )
            .andExpect(status().isOk()).andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
    
            JSONObject responseJson = new JSONObject(response);
            JSONArray responseJsonArray = responseJson.getJSONArray("members");
            
            assertEquals(3, responseJsonArray.length());
            assertEquals("冒険者1", responseJsonArray.getJSONObject(0).getString("name"));
            assertEquals("冒険者2", responseJsonArray.getJSONObject(1).getString("name"));
            assertEquals("冒険者3", responseJsonArray.getJSONObject(2).getString("name"));
        }
    
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