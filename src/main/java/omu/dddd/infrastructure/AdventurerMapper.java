package omu.dddd.infrastructure;

import org.apache.ibatis.annotations.Mapper;

import omu.dddd.domain.Adventurer;

@Mapper
public interface AdventurerMapper {
    
    Adventurer findById(int id);

}