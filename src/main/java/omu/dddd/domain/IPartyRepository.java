package omu.dddd.domain;

import java.util.List;

public interface IPartyRepository {

    Party findById(int int1);

    void save(Party newParty);

    List<Party> findAll();

    PartyMembers getPartyMembers(int i);

    void addPartyMember(Integer targetPartyId, Integer targetAdventurerId);

    Adventurer getPartyMember(Integer targetPartyId, Integer targetAdventurerId);

}
