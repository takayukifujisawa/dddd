package omu.dddd.infrastructure;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import omu.dddd.domain.Adventurer;
import omu.dddd.domain.Party;

@Mapper
public interface PartyMapper {
    
    Party findById(int id);

    void insert(@Param("party") Party newParty);

    List<Party> findAll();

    List<Adventurer> findPartyMembersByPartyId(int partyId);

    void insertPartyMember(@Param("party_id") Integer partyId, @Param("adventurer_id") Integer adventurerId);

}