package thy.aviation_system.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import thy.aviation_system.constants.AviationSystemValidationRules;
import thy.aviation_system.entity.TransportationType;
import thy.aviation_system.controller.request.SearchRoutesRequest;
import thy.aviation_system.controller.response.RouteSearchResponse;
import thy.aviation_system.controller.response.RouteSegmentResponse;
import thy.aviation_system.controller.response.RoutesResponse;
import thy.aviation_system.entity.Location;
import thy.aviation_system.entity.Transportation;
import thy.aviation_system.exception.BusinessValidationException;
import thy.aviation_system.repository.LocationRepository;
import thy.aviation_system.repository.TransportationRepository;
import thy.aviation_system.service.RoutesService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoutesServiceImpl implements RoutesService {

    private final LocationRepository locationRepository;
    private final TransportationRepository transportationRepository;

    @Override
    public RouteSearchResponse searchRoutes(SearchRoutesRequest request) {
        Location origin = locationRepository.getLocationByLocationCode(request.getOriginCode())
                .orElseThrow(() -> new BusinessValidationException(AviationSystemValidationRules.LOCATION_NOT_FOUND_BY_CODE));

        Location destination = locationRepository.getLocationByLocationCode(request.getDestinationCode())
                .orElseThrow(() -> new BusinessValidationException(AviationSystemValidationRules.LOCATION_NOT_FOUND_BY_CODE));

        List<RoutesResponse> routes = findAllValidRoutes(origin, destination, request.getDate());

        return new RouteSearchResponse(
                request.getOriginCode(),
                request.getDestinationCode(),
                request.getDate(),
                routes.size(),
                routes
        );
    }

    private List<RoutesResponse> findAllValidRoutes(Location origin, Location destination, LocalDate date) {
        List<RoutesResponse> allRoutes =
                new ArrayList<>();

        List<Transportation> allFlights = transportationRepository
                .findByTransportationType(TransportationType.FLIGHT);

        for (Transportation flight : allFlights) {

            List<Transportation> beforeFlightTransfers = findTransfers(
                    origin.getId(),
                    flight.getOriginLocation().getId(),
                    false
            );

            List<Transportation> afterFlightTransfers = findTransfers(
                    flight.getDestinationLocation().getId(),
                    destination.getId(),
                    false
            );

            // Direct Flight
            if (flight.getOriginLocation().getId().equals(origin.getId()) &&
                    flight.getDestinationLocation().getId().equals(destination.getId())) {

                List<Transportation> segments = List.of(flight);
                if (isValidForDate(segments, date)) {
                    allRoutes.add(buildRouteResponse(segments, origin, destination));
                }
            }

            // Before Transfer + Flight
            if (!beforeFlightTransfers.isEmpty() &&
                    flight.getDestinationLocation().getId().equals(destination.getId())) {

                for (Transportation beforeTransfer : beforeFlightTransfers) {
                    List<Transportation> segments = List.of(beforeTransfer, flight);
                    if (isValidForDate(segments, date)) {
                        allRoutes.add(buildRouteResponse(segments, origin, destination));
                    }
                }
            }

            // Flight + After Transfer
            if (flight.getOriginLocation().getId().equals(origin.getId()) &&
                    !afterFlightTransfers.isEmpty()) {

                for (Transportation afterTransfer : afterFlightTransfers) {
                    List<Transportation> segments = List.of(flight, afterTransfer);
                    if (isValidForDate(segments, date)) {
                        allRoutes.add(buildRouteResponse(segments, origin, destination));
                    }
                }
            }

            // Before Transfer + Flight + After Transfer
            if (!beforeFlightTransfers.isEmpty() && !afterFlightTransfers.isEmpty()) {

                for (Transportation beforeTransfer : beforeFlightTransfers) {
                    for (Transportation afterTransfer : afterFlightTransfers) {
                        List<Transportation> segments = List.of(beforeTransfer, flight, afterTransfer);
                        if (isValidForDate(segments, date)) {
                            allRoutes.add(buildRouteResponse(segments, origin, destination));
                        }
                    }
                }
            }
        }
        return allRoutes;
    }

    private List<Transportation> findTransfers(Long originLocationId, Long destinationLocationId, boolean includeFlight) {
        List<Transportation> transportations = transportationRepository.findByOriginLocationIdAndDestinationLocationId(originLocationId, destinationLocationId);

        if (!includeFlight) {
            return transportations.stream()
                    .filter(t -> t.getTransportationType().isGroundTransport())
                    .toList();
        }

        return transportations;
    }

    private boolean isValidForDate(List<Transportation> segments, LocalDate date) {
        if (date == null) {return true;}

        int dayOfWeek = date.getDayOfWeek().getValue();

        for (Transportation segment : segments) {
            if (!segment.operatesOnDay(dayOfWeek))
                return false;
        }
        return true;
    }


    private RoutesResponse buildRouteResponse(List<Transportation> segments,
                                             Location origin,
                                             Location destination) {
        List<RouteSegmentResponse> segmentResponses = new ArrayList<>();
        StringBuilder descriptionBuilder = new StringBuilder();

        for (int i = 0; i < segments.size(); i++) {
            Transportation segment = segments.get(i);

            RouteSegmentResponse segmentResponse = new RouteSegmentResponse(
                    i + 1,
                    segment.getOriginLocation().getName(),
                    segment.getOriginLocation().getLocationCode(),
                    segment.getDestinationLocation().getName(),
                    segment.getDestinationLocation().getLocationCode(),
                    segment.getTransportationType(),
                    segment.getOperatingDays(),
                    String.format("%s from %s to %s",
                            segment.getTransportationType(),
                            segment.getOriginLocation().getName(),
                            segment.getDestinationLocation().getName())
            );

            segmentResponses.add(segmentResponse);

            if (i == 0)
                descriptionBuilder.append(segment.getOriginLocation().getLocationCode());

            descriptionBuilder.append(" → (")
                    .append(segment.getTransportationType())
                    .append(") → ")
                    .append(segment.getDestinationLocation().getLocationCode());
        }

        return new RoutesResponse(
                origin.getLocationCode(),
                destination.getLocationCode(),
                segmentResponses,
                segments.size(),
                descriptionBuilder.toString()
        );
    }

}
