package luna.clubverse.backend.location.controller;


import luna.clubverse.backend.location.controller.request.AddLocationRequest;
import luna.clubverse.backend.location.service.LocationService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/location")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @CrossOrigin
    @PostMapping("/add")
    public String addLocation(@RequestBody @Valid final AddLocationRequest addLocationRequest) {
        locationService.addLocation(addLocationRequest.toLocation());
        return "success";
    }
}
