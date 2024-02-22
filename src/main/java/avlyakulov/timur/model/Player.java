package avlyakulov.timur.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Players")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@NamedQueries({
        @NamedQuery(name = "HQL_FindAllPlayers",
                query = "from Player p order by p.id"),
        @NamedQuery(name = "HQL_FindPlayerByName",
                query = "from Player where name = :playerName"),
        @NamedQuery(name = "HQL_FindPlayerNameById",
                query = "select name from Player where id = :id")
})
@ToString
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    private String name;

    public Player(String name) {
        this.name = name;
    }
}