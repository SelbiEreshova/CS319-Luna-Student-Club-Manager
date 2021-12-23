package luna.clubverse.backend.event.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EventHomePageResponse {

    private String editEvent;
    private String cancelEvent;
    private String publishEvent;
    private String deleteEvent;

}
