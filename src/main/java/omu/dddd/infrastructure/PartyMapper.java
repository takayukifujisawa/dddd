package omu.dddd.infrastructure;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import omu.dddd.domain.Party;

@Mapper
public interface PartyMapper {
    
    Party findById(int id);

    void insert(@Param("party") Party newParty);

    List<Party> findAll();

    // List<Adventurer> findAll();
}