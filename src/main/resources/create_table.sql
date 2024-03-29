create table if not exists Players
(
    ID   int primary key auto_increment,
    Name varchar unique
);

create table if not exists Matches
(
    ID      int primary key auto_increment,
    Player1 int,
    Player2 int,
    Winner  int,
    FOREIGN KEY (Player1) REFERENCES Players (ID),
    FOREIGN KEY (Player2) REFERENCES Players (ID),
    FOREIGN KEY (Winner) REFERENCES Players (ID)
);


INSERT INTO Players(Name)
VALUES ('Alex'),
       ('Katya'),
       ('Lera'),
       ('Denis'),
       ('Semen');

INSERT INTO Matches(Player1, Player2, Winner)
VALUES (1, 2, 1),
       (1, 3, 3),
       (2, 3, 3),
       (4, 5, 5),
       (1, 5, 1),
       (1, 5, 5),
       (2, 5, 5),
       (1, 5, 1),
       (1, 2, 1),
       (2, 3, 3);

CREATE INDEX index_player_name ON Players (Name);