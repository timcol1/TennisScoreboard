package avlyakulov.timur.mapper;

import avlyakulov.timur.dto.MatchFinishedResponse;
import avlyakulov.timur.dto.MatchScoreModelResponse;
import avlyakulov.timur.dto.MatchInProgressResponse;
import avlyakulov.timur.model.Match;
import avlyakulov.timur.model.MatchScoreModel;
import avlyakulov.timur.model.Player;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MatchMapper {
    MatchMapper INSTANCE = Mappers.getMapper(MatchMapper.class);

    MatchInProgressResponse mapToMatchResponse(Match match);

    @Mapping(target = "playerOneName", source = "playerOne.name")
    @Mapping(target = "playerTwoName", source = "playerTwo.name")
    @Mapping(target = "winnerName", source = "winner.name")
    MatchScoreModelResponse mapToMatchScoreModelResponse(MatchScoreModel matchScoreModel);

    List<MatchScoreModelResponse> mapToListMatchScoreModelResponse(List<MatchScoreModel> matchScoreModelList);

    @Mapping(target = "playerOneName", source = "match.playerOne.name")
    @Mapping(target = "playerTwoName", source = "match.playerTwo.name")
    @Mapping(target = "playerOneSet", source = "match.setPlayerOne")
    @Mapping(target = "playerTwoSet", source = "match.setPlayerTwo")
    @Mapping(target = "winnerName", source = "winner.name")
    MatchFinishedResponse mapToMatchFinishedResponse(Match match, Player winner);
}