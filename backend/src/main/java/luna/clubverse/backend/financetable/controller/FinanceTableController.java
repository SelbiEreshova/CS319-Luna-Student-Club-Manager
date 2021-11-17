package luna.clubverse.backend.financetable.controller;

import luna.clubverse.backend.event.controller.response.EventQueryResponse;
import luna.clubverse.backend.financedata.controller.response.FinanceDataQueryResponse;
import luna.clubverse.backend.financetable.service.FinanceTableService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/financeTable")
public class FinanceTableController {

    private final FinanceTableService financeTableService;

    public FinanceTableController(FinanceTableService financeTableService) {
        this.financeTableService = financeTableService;
    }

    @CrossOrigin
    @GetMapping("/getFinanceDataList/{id}")
    public List<FinanceDataQueryResponse> getClubEvents(@PathVariable Long id) {
        return financeTableService.getFinanceDataList(id);
    }

}
