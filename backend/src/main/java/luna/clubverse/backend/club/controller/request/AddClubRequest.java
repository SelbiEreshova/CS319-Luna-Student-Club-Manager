package luna.clubverse.backend.club.controller.request;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import luna.clubverse.backend.club.entity.Club;
import luna.clubverse.backend.financetable.entity.FinanceTable;

import javax.validation.constraints.NotNull;

@RequiredArgsConstructor
@Getter
@ToString
public class AddClubRequest {

    @NotNull(message = "The name of the club cannot be blank")
    private String name;
    private String logo;
    private String description;

    public Club toClub() {
        return new Club(name,logo,description, toFinanceTable());

    }

    public FinanceTable toFinanceTable(){
        return new FinanceTable();
    }



}
