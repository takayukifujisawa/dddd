package omu.dddd.application;

import org.springframework.stereotype.Service;

import omu.dddd.domain.Adventurer;
import omu.dddd.domain.IAdventurerRepository;
import omu.dddd.presentation.AdventurerCreateParam;

@Service
public class AdventurerCreateUseCase {

    private IAdventurerRepository adventurerRepository;

	public AdventurerCreateUseCase(IAdventurerRepository adventurerRepository) {
        this.adventurerRepository = adventurerRepository;
    }

    public Adventurer create(AdventurerCreateParam param) {
        Adventurer newAdventurer = new Adventurer(param);
        adventurerRepository.save(newAdventurer);
        return newAdventurer;
    }

}