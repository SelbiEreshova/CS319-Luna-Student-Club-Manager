package luna.clubverse.backend.location.service;


import luna.clubverse.backend.location.entity.Location;
import luna.clubverse.backend.location.repository.LocationRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class LocationService {

    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository){this.locationRepository = locationRepository;}

    public void addLocation(Location location) {
        locationRepository.save(location);
    }
}
