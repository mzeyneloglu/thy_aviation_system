package thy.aviation_system.mapper;

import org.mapstruct.*;
import thy.aviation_system.controller.request.InsertLocationRequest;
import thy.aviation_system.controller.request.UpdateLocationRequest;
import thy.aviation_system.dto.LocationDTO;
import thy.aviation_system.entity.Location;
import java.util.List;

@Mapper(componentModel = "spring")
public interface LocationMapper {

    @Mapping(target = "id", ignore = true)
    Location toEntity(InsertLocationRequest request);

    @Mapping(target = "id", ignore = true)
    void updateEntity(UpdateLocationRequest request, @MappingTarget Location entity);

    LocationDTO toDTO(Location entity);

    List<LocationDTO> toDTOList(List<Location> entities);

}
