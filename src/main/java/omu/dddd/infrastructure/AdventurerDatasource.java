package omu.dddd.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import omu.dddd.domain.Adventurer;
import omu.dddd.domain.IAdventurerRepository;

@Repository
public class AdventurerDatasource implements IAdventurerRepository {

    @Autowired
    AdventurerMapper mapper;

    @Override
    public Adventurer findById(int id) {
        return mapper.findById(id);
    }
    
}