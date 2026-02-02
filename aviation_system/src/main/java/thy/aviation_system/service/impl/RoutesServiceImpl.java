package thy.aviation_system.service.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import thy.aviation_system.repository.LocationRepository;
import thy.aviation_system.repository.TransportationRepository;
import thy.aviation_system.service.RoutesService;

@Service
@RequiredArgsConstructor
public class RoutesServiceImpl implements RoutesService {

    Logger logger = LoggerFactory.getLogger(RoutesServiceImpl.class);
    private final LocationRepository locationRepository;
    private final TransportationRepository transportationRepository;






}
