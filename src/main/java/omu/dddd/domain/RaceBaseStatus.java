package omu.dddd.domain;

public enum RaceBaseStatus {
    Human(
        15, // vitalityBase
        15, // strengthBase
        15, // dexterityBase
        15, // reflexBase
        15, // intelligenceBase
        15, // widomBase
        15, // mindBase
        15, // agilityBase
        15  // luckBase
    ),
    Elf(
        15, // vitalityBase
        15, // strengthBase
        15, // dexterityBase
        15, // reflexBase
        15, // intelligenceBase
        15, // widomBase
        15, // mindBase
        15, // agilityBase
        15  // luckBase
    ),
    Dwarf(
        15, // vitalityBase
        15, // strengthBase
        15, // dexterityBase
        15, // reflexBase
        15, // intelligenceBase
        15, // widomBase
        15, // mindBase
        15, // agilityBase
        15  // luckBase
    ),
    HalfElf(
        15, // vitalityBase
        15, // strengthBase
        15, // dexterityBase
        15, // reflexBase
        15, // intelligenceBase
        15, // widomBase
        15, // mindBase
        15, // agilityBase
        15  // luckBase
    );

    private RaceBaseStatus(Integer vitalityBase, Integer strengthBase, Integer dexterityBase, Integer reflexBase,
            Integer intelligenceBase, Integer wisdomBase, Integer mindBase, Integer agilityBase, Integer luckBase) {
        this.vitalityBase = vitalityBase;
        this.strengthBase = strengthBase;
        this.dexterityBase = dexterityBase;
        this.reflexBase = reflexBase;
        this.intelligenceBase = intelligenceBase;
        this.wisdomBase = wisdomBase;
        this.mindBase = mindBase;
        this.agilityBase = agilityBase;
        this.luckBase = luckBase;
    }

    public final Integer vitalityBase;
    public final Integer strengthBase;
    public final Integer dexterityBase;
    public final Integer reflexBase;
    public final Integer intelligenceBase;
    public final Integer wisdomBase;
    public final Integer mindBase;
    public final Integer agilityBase;
    public final Integer luckBase;

}