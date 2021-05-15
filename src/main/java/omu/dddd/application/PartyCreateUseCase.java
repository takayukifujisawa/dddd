package omu.dddd.application;

import org.springframework.stereotype.Service;

import omu.dddd.domain.IPartyRepository;
import omu.dddd.domain.Party;
import omu.dddd.presentation.PartyCreateParam;

@Service
public class PartyCreateUseCase {

    private IPartyRepository partyRepository;

	public PartyCreateUseCase(IPartyRepository partyRepository) {
        this.partyRepository = partyRepository;
    }

    public Party create(PartyCreateParam param) {
        Party newParty = new Party(param);
        partyRepository.save(newParty);
        return newParty;
    }

}
