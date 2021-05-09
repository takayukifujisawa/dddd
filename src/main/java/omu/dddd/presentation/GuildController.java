package omu.dddd.presentation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import omu.dddd.domain.Adventurer;
import omu.dddd.domain.IAdventurerRepository;

@RestController
@RequestMapping("/api/guild")
public class GuildController {
    
    @Autowired
    private IAdventurerRepository adventurerRepository;

    @GetMapping("/adventurers")
    public List<Adventurer> adventurers() {

        List<Adventurer> adventurers = adventurerRepository.findAll();
        return adventurers;
    }

}