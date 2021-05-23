CREATE TABLE IF NOT EXISTS adventurer (
    id INTEGER PRIMARY KEY auto_increment,
    name VARCHAR(40) NOT NULL,
    race VARCHAR(20) NOT NULL,    
    vitality INTEGER NOT NULL,
    strength INTEGER NOT NULL,
    dexterity INTEGER NOT NULL,
    reflex INTEGER NOT NULL,
    intelligence INTEGER NOT NULL,
    wisdom INTEGER NOT NULL,
    mind INTEGER NOT NULL,
    agility INTEGER NOT NULL,
    luck  INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS party (
    id INTEGER PRIMARY KEY auto_increment,
    name VARCHAR(40) NOT NULL
);

CREATE TABLE IF NOT EXISTS party_member (
    id INTEGER PRIMARY KEY auto_increment,
    party_id INTEGER NOT NULL,
    adventurer_id INTEGER NOT NULL
);