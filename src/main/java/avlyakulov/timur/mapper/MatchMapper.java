package avlyakulov.timur.mapper;

import avlyakulov.timur.dto.MatchScoreModelResponse;
import avlyakulov.timur.dto.MatchInProgressResponse;
import avlyakulov.timur.model.Match;
import avlyakulov.timur.model.MatchScoreModel;
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
    MatchScoreModelResponse mapToMatchFinishedResponse(MatchScoreModel matchScoreModel);

    List<MatchScoreModelResponse> mapToListMatchFinishedResponse(List<MatchScoreModel> matchScoreModelList);
}