package thy.aviation_system.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.Mockito.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import thy.aviation_system.entity.TransportationType;
import thy.aviation_system.controller.request.SearchRoutesRequest;
import thy.aviation_system.controller.response.RouteSearchResponse;
import thy.aviation_system.entity.Location;
import thy.aviation_system.entity.Transportation;
import thy.aviation_system.repository.LocationRepository;
import thy.aviation_system.repository.TransportationRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
class RoutesServiceImplTest {

    @Mock
    private LocationRepository locationRepository;

    @Mock
    private TransportationRepository transportationRepository;

    @InjectMocks
    private RoutesServiceImpl routesService;

    private Location origin;
    private Location destination;
    private Location istanbulAirport;
    private Location heathrowAirport;
    private Transportation beforeTransferBus;
    private Transportation flight;
    private Transportation afterTransferBus;
    private Transportation afterTransferUber;

    @BeforeEach
    void setUp() {
        origin = Location.builder()
                .id(1L)
                .locationCode("TAKSIM")
                .name("Taksim Square")
                .country("Turkey")
                .city("Istanbul")
                .build();

        destination = Location.builder()
                .id(2L)
                .locationCode("WEMBLEY")
                .name("Wembley Stadium")
                .country("UK")
                .city("London")
                .build();

        istanbulAirport = Location.builder()
                .id(3L)
                .locationCode("IST")
                .name("Istanbul Airport")
                .country("Turkey")
                .city("Istanbul")
                .build();

        heathrowAirport = Location.builder()
                .id(4L)
                .locationCode("LHR")
                .name("London Heathrow Airport")
                .country("UK")
                .city("London")
                .build();

        beforeTransferBus = Transportation.builder()
                .id(1L)
                .originLocation(origin)
                .destinationLocation(istanbulAirport)
                .transportationType(TransportationType.BUS)
                .operatingDays("1,2,3,4,5,6,7")
                .build();

        flight = Transportation.builder()
                .id(4L)
                .originLocation(istanbulAirport)
                .destinationLocation(heathrowAirport)
                .transportationType(TransportationType.FLIGHT)
                .operatingDays("1,2,5,6,7")
                .build();

        afterTransferBus = Transportation.builder()
                .id(5L)
                .originLocation(heathrowAirport)
                .destinationLocation(destination)
                .transportationType(TransportationType.BUS)
                .operatingDays("1,2,3,4,5,6,7")
                .build();

        afterTransferUber = Transportation.builder()
                .id(6L)
                .originLocation(heathrowAirport)
                .destinationLocation(destination)
                .transportationType(TransportationType.UBER)
                .operatingDays("1,2,3,4,5,6,7")
                .build();
    }

    @Test
    @DisplayName("Valid Case")
    void shouldReturnNoRouteResponse_whenNotFoundAnyFlights() {
        SearchRoutesRequest request = new SearchRoutesRequest();
        request.setOriginCode("TAKSIM");
        request.setDestinationCode("WEMBLEY");

        when(locationRepository.getLocationByLocationCode(request.getOriginCode()))
                .thenReturn(Optional.ofNullable(origin));
        when(locationRepository.getLocationByLocationCode(request.getDestinationCode()))
                .thenReturn(Optional.ofNullable(destination));
        when(transportationRepository.findByTransportationType(TransportationType.FLIGHT))
                .thenReturn(List.of());

        RouteSearchResponse response = routesService.searchRoutes(request);

        assertEquals(List.of(), response.getRoutes());
        assertEquals(0, response.getTotalRoutes());
    }

    @Test
    @DisplayName("Valid Case")
    void shouldOneFlightRoute_whenNotFoundAnyDifferentRoutes() {
        SearchRoutesRequest request = new SearchRoutesRequest();
        request.setOriginCode("IST");
        request.setDestinationCode("LHR");

        when(locationRepository.getLocationByLocationCode(request.getOriginCode()))
                .thenReturn(Optional.ofNullable(istanbulAirport));
        when(locationRepository.getLocationByLocationCode(request.getDestinationCode()))
                .thenReturn(Optional.ofNullable(heathrowAirport));
        when(transportationRepository.findByTransportationType(TransportationType.FLIGHT))
                .thenReturn(List.of(flight));
        when(transportationRepository.findByOriginLocationIdAndDestinationLocationId(Mockito.any(), Mockito.any()))
                .thenReturn(List.of());

        RouteSearchResponse response = routesService.searchRoutes(request);

        assertEquals(1, response.getTotalRoutes());
        assertEquals(1, response.getRoutes().get(0).getTotalSegments());
        assertEquals("IST → (FLIGHT) → LHR", response.getRoutes().get(0).getDescription());
    }

    @Test
    @DisplayName("Valid Case")
    void shouldFindRouteBeforeFlightAndFlight_whenJustFoundOneGroundTransportation() {
        SearchRoutesRequest request = new SearchRoutesRequest();
        request.setOriginCode("TAKSIM");
        request.setDestinationCode("LHR");

        when(locationRepository.getLocationByLocationCode(request.getOriginCode()))
                .thenReturn(Optional.ofNullable(origin));
        when(locationRepository.getLocationByLocationCode(request.getDestinationCode()))
                .thenReturn(Optional.ofNullable(heathrowAirport));
        when(transportationRepository.findByTransportationType(TransportationType.FLIGHT))
                .thenReturn(List.of(flight));
        when(transportationRepository.findByOriginLocationIdAndDestinationLocationId(origin.getId(), istanbulAirport.getId()))
                .thenReturn(List.of(beforeTransferBus));

        RouteSearchResponse response = routesService.searchRoutes(request);

        assertEquals(1, response.getTotalRoutes());
        assertEquals(2, response.getRoutes().get(0).getTotalSegments());
        assertEquals("TAKSIM → (BUS) → IST → (FLIGHT) → LHR", response.getRoutes().get(0).getDescription());
    }

    @Test
    @DisplayName("Valid Case")
    void shouldTwoTransportationsInRouteAfterFlightAndFlight_whenJustFoundOneGroundTransportationAfterFlight() {
        SearchRoutesRequest request = new SearchRoutesRequest();
        request.setOriginCode("IST");
        request.setDestinationCode("WEMBLEY");

        when(locationRepository.getLocationByLocationCode(request.getOriginCode()))
                .thenReturn(Optional.ofNullable(istanbulAirport));
        when(locationRepository.getLocationByLocationCode(request.getDestinationCode()))
                .thenReturn(Optional.ofNullable(destination));
        when(transportationRepository.findByTransportationType(TransportationType.FLIGHT))
                .thenReturn(List.of(flight));
        when(transportationRepository.findByOriginLocationIdAndDestinationLocationId(heathrowAirport.getId(), destination.getId()))
                .thenReturn(List.of(afterTransferBus));
        when(transportationRepository.findByOriginLocationIdAndDestinationLocationId(istanbulAirport.getId(), istanbulAirport.getId()))
                .thenReturn(List.of());


        RouteSearchResponse response = routesService.searchRoutes(request);

        assertEquals(1, response.getTotalRoutes());
        assertEquals(2, response.getRoutes().get(0).getTotalSegments());
        assertEquals("IST → (FLIGHT) → LHR → (BUS) → WEMBLEY", response.getRoutes().get(0).getDescription());
    }

    @Test
    @DisplayName("Valid Case")
    void shouldThreeTransportationsInRouteAfterFlightAndBeforeFlightAndFlight_whenFoundTwoGroundTransportationBeforeAndAfterFlight() {
        SearchRoutesRequest request = new SearchRoutesRequest();
        request.setOriginCode("TAKSIM");
        request.setDestinationCode("WEMBLEY");

        when(locationRepository.getLocationByLocationCode(request.getOriginCode()))
                .thenReturn(Optional.ofNullable(origin));
        when(locationRepository.getLocationByLocationCode(request.getDestinationCode()))
                .thenReturn(Optional.ofNullable(destination));
        when(transportationRepository.findByTransportationType(TransportationType.FLIGHT))
                .thenReturn(List.of(flight));
        when(transportationRepository.findByOriginLocationIdAndDestinationLocationId(origin.getId(), istanbulAirport.getId()))
                .thenReturn(List.of(beforeTransferBus));
        when(transportationRepository.findByOriginLocationIdAndDestinationLocationId(heathrowAirport.getId(), destination.getId()))
                .thenReturn(List.of(afterTransferBus));

        RouteSearchResponse response = routesService.searchRoutes(request);

        assertEquals(1, response.getTotalRoutes());
        assertEquals(3, response.getRoutes().get(0).getTotalSegments());
        System.out.println(response.getRoutes());
        assertEquals("TAKSIM → (BUS) → IST → (FLIGHT) → LHR → (BUS) → WEMBLEY", response.getRoutes().get(0).getDescription());
    }
}