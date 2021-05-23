INSERT INTO adventurer
(name,         race,    vitality,   strength,   dexterity,  reflex, intelligence,   wisdom, mind,   agility,    luck)
values
('冒険者A',    'Human', 15,         15,         15,         15,     10,             10,     10,     15,         10),
('冒険者B',    'Elf',   10,         10,         10,         10,     15,             15,     15,     10,         10),
('冒険者C',    'Dwarf',  8,          12,         20,         20,     10,             10,     10,     20,         15)
;

INSERT INTO party
(name)
values
('パーティA'),
('パーティB'),
('パーティC')
;

INSERT INTO party_member
(party_id, adventurer_id)
values
(1, 1),
(1, 2),
(2, 3)
;
