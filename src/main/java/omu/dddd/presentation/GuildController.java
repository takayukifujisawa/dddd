package omu.dddd.presentation;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import omu.dddd.application.AdventurerCreateUseCase;
import omu.dddd.domain.Adventurer;
import omu.dddd.domain.IAdventurerRepository;

@RestController
@RequestMapping("/api/guild")
public class GuildController {
    
    private final IAdventurerRepository adventurerRepository;
    private final AdventurerCreateUseCase adventurerCreateUseCase;

    public GuildController(IAdventurerRepository adventurerRepository,
            AdventurerCreateUseCase adventurerCreateUseCase) {
        this.adventurerRepository = adventurerRepository;
        this.adventurerCreateUseCase = adventurerCreateUseCase;
    }

    @GetMapping("/adventurers")
    public List<Adventurer> adventurers() {
        List<Adventurer> adventurers = adventurerRepository.findAll();
        return adventurers;
    }

    @PostMapping("/adventurer/create")
    public Adventurer createAdventurer(@RequestBody AdventurerCreateParam param) {
        return adventurerCreateUseCase.create(param);
    }
}