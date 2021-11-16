package luna.clubverse.backend.location.controller.request;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import luna.clubverse.backend.location.entity.Location;

import javax.validation.constraints.NotNull;

@RequiredArgsConstructor
@Getter
@ToString
public class AddLocationRequest {

    @NotNull(message = "inBilkent should be not null.")
    private Boolean inBilkent;

    @NotNull(message = "building should be not null.")
    private String building;

    @NotNull(message = "description should be not null.")
    private String description;

    @NotNull(message = "classroom should be not null.")
    private String classroom;

    public Location toLocation() {
        return new Location( building, description, classroom, inBilkent);

    }
}
