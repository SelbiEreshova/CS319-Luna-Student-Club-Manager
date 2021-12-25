package luna.clubverse.backend.emptyform;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EmptyFormController {

    @RequestMapping("/app/club_form")
    public String openClubForm() {
        return "create_application_form";
    }

    @RequestMapping("/app/fill_club_form/{clubId}")
    public String fillClubForm(Model model, @PathVariable Long clubId) {
        model.addAttribute("clubId", clubId);
        return "application_form";
    }
}
