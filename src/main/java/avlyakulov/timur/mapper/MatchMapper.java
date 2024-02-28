package avlyakulov.timur.mapper;

import avlyakulov.timur.dto.MatchInProgressResponse;
import avlyakulov.timur.model.Match;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MatchMapper {
    MatchMapper INSTANCE = Mappers.getMapper(MatchMapper.class);

    MatchInProgressResponse mapToMatchResponse(Match match);
}