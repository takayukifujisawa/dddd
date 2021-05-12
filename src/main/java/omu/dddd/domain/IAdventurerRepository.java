package omu.dddd.domain;

import java.util.List;

public interface IAdventurerRepository {
    
    public Adventurer findById(int id);

    public List<Adventurer> findAll();

    public void save(Adventurer newAdventurer);

}