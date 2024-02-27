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
        //todo fix N + 1 problem here
        @NamedQuery(name = "HQL_FindAllMatches",
                query = "from MatchScoreModel order by id ")
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