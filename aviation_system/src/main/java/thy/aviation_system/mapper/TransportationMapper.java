package thy.aviation_system.mapper;

import org.mapstruct.*;
import thy.aviation_system.controller.request.InsertTransportationRequest;
import thy.aviation_system.controller.request.UpdateTransportationRequest;
import thy.aviation_system.dto.TransportationDTO;
import thy.aviation_system.entity.Transportation;
import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = {LocationMapper.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface TransportationMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "originLocation", ignore = true)
    @Mapping(target = "destinationLocation", ignore = true)
    Transportation toEntity(InsertTransportationRequest request);

    @Mapping(source = "originLocation", target = "originLocation")
    @Mapping(source = "destinationLocation", target = "destinationLocation")
    TransportationDTO toDTO(Transportation entity);

    List<TransportationDTO> toDTOList(List<Transportation> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "originLocation", ignore = true)
    @Mapping(target = "destinationLocation", ignore = true)
    void updateEntity(UpdateTransportationRequest request, @MappingTarget Transportation entity);
}