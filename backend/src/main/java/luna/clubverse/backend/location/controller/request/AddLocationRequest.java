package luna.clubverse.backend.location.controller.request;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import luna.clubverse.backend.location.entity.Location;

@RequiredArgsConstructor
@Getter
@ToString
public class AddLocationRequest {

    private boolean isInBilkent;

    private String building;

    private String description;

    private String classroom;

    public Location toLocation() {
        return new Location( building, description, classroom, isInBilkent);

    }
}
