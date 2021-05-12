package omu.dddd.infrastructure;

import java.util.List;

import org.springframework.stereotype.Repository;

import omu.dddd.domain.Adventurer;
import omu.dddd.domain.IAdventurerRepository;

@Repository
public class AdventurerDatasource implements IAdventurerRepository {

    AdventurerMapper mapper;

    public AdventurerDatasource(AdventurerMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Adventurer findById(int id) {
        return mapper.findById(id);
    }

    @Override
    public List<Adventurer> findAll() {
        return mapper.findAll();
    }

    @Override
    public void save(Adventurer newAdventurer) {
        mapper.insert(newAdventurer);
    }
    
}