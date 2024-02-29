package avlyakulov.timur.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Matches")
@Getter
@Setter
@NoArgsConstructor
@NamedQueries({
        @NamedQuery(name = "HQL_FindAllMatches",
                query = "from MatchScoreModel m left join fetch m.playerOne left join fetch m.playerTwo left join fetch m.winner order by m.id"),
        @NamedQuery(name = "HQL_FindAllMatchesByPlayerName",
                query = "from MatchScoreModel m left join fetch m.playerOne left join fetch m.playerTwo left join fetch m.winner " +
                        "where m.playerOne.name = :name or m.playerTwo.name = :name order by m.id")
})
public class MatchScoreModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "Player1")
    private Player playerOne;

    @ManyToOne
    @JoinColumn(name = "Player2")
    private Player playerTwo;

    @ManyToOne
    @JoinColumn(name = "Winner")
    private Player winner;

    public MatchScoreModel(Player playerOne, Player playerTwo, Player winner) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.winner = winner;
    }
}