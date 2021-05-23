package omu.dddd.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class PartyMembers {

    List<Adventurer> members = new ArrayList<>();

    public PartyMembers(List<Adventurer> members) {
        this.members = members;
    }

    public void add(Adventurer adventurer) {
        members.add(adventurer);
    }

    public Integer menmberCount() {
        return members.size();
    }

    public boolean contains(Integer targetAdventurerId) {
        return members.stream().anyMatch(ad -> ad.getId() == targetAdventurerId);
    }

}
