package avlyakulov.timur.mapper;

import avlyakulov.timur.dto.MatchResponse;
import avlyakulov.timur.model.Match;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MatchMapper {
    MatchMapper INSTANCE = Mappers.getMapper(MatchMapper.class);

    MatchResponse mapToMatchResponse(Match match);
}