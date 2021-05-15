package omu.dddd.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import omu.dddd.presentation.PartyCreateParam;

@AllArgsConstructor
@Data
public class Party {
    
    Integer id;
    String name;

    public Party(PartyCreateParam param) {
        this.name = param.getName();
    }
    
}
