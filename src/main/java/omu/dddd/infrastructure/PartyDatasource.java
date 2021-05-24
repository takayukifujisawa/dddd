package omu.dddd.infrastructure;

import java.util.List;

import org.springframework.stereotype.Repository;

import omu.dddd.domain.Adventurer;
import omu.dddd.domain.IPartyRepository;
import omu.dddd.domain.Party;
import omu.dddd.domain.PartyMembers;

@Repository
public class PartyDatasource implements IPartyRepository {

    PartyMapper mapper;

    public PartyDatasource(PartyMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Party findById(int id) {
        return mapper.findById(id);
    }

    @Override
    public void save(Party newParty) {
        mapper.insert(newParty);
    }

    @Override
    public List<Party> findAll() {
        return mapper.findAll();
    }

    @Override
    public PartyMembers getPartyMembers(int partyId) {
        List<Adventurer> partyMembers = mapper.findPartyMembersByPartyId(partyId);
        return new PartyMembers(partyMembers);
    }

    @Override
    public void addPartyMember(Integer partyId, Integer adventurerId) {
        mapper.insertPartyMember(partyId, adventurerId);
    }

    @Override
    public Adventurer getPartyMember(Integer partyId, Integer adventurerId) {
        return mapper.findPartyMemberByPartyIdAndAdventurerId(partyId, adventurerId);
    }

}