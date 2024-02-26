package avlyakulov.timur.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Matches")
@Getter
@Setter
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
}