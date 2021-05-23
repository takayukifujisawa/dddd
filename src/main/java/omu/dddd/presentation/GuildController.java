package omu.dddd.presentation;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import omu.dddd.application.AdventurerCreateUseCase;
import omu.dddd.application.JoinPartyUseCase;
import omu.dddd.application.PartyCreateUseCase;
import omu.dddd.domain.Adventurer;
import omu.dddd.domain.IAdventurerRepository;
import omu.dddd.domain.IPartyRepository;
import omu.dddd.domain.Party;
import omu.dddd.domain.PartyMemberDuplicatedException;
import omu.dddd.domain.PartyMembers;
import omu.dddd.domain.validationRule.DefaultOrder;

@RestController
@RequestMapping("/api/guild")
@AllArgsConstructor
public class GuildController {
    
    private final AdventurerCreateUseCase adventurerCreateUseCase;
    private final IAdventurerRepository adventurerRepository;
    private final PartyCreateUseCase partyCreateUseCase;
    private final IPartyRepository partyRepository;
    private final JoinPartyUseCase joinPartyUseCase;

    @GetMapping("/adventurers")
    public List<Adventurer> adventurers() {
        List<Adventurer> adventurers = adventurerRepository.findAll();
        return adventurers;
    }

    @PostMapping("/adventurer/create")
    public Adventurer createAdventurer(@Validated(DefaultOrder.class) @RequestBody AdventurerCreateParam param) {
        return adventurerCreateUseCase.create(param);
    }

    @GetMapping("/parties")
    public List<Party> parties() {
        List<Party> parties = partyRepository.findAll();
        return parties;
    }

    @PostMapping("/party/create")
    public Party createParty(@Validated(DefaultOrder.class) @RequestBody PartyCreateParam param) {
        return partyCreateUseCase.create(param);
    }

    @PostMapping("party/join")
    public PartyMembers joinParty(@Validated(DefaultOrder.class) @RequestBody JoinPartyParam param) throws PartyMemberDuplicatedException {
        return joinPartyUseCase.join(param);
    }

}