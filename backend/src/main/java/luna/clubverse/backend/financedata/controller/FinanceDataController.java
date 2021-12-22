package luna.clubverse.backend.financedata.controller;


import luna.clubverse.backend.financedata.controller.request.AddFinanceDataRequest;
import luna.clubverse.backend.financedata.service.FinanceDataService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/financeData")
public class FinanceDataController {

    private final FinanceDataService financeDataService;

    public FinanceDataController(FinanceDataService financeDataService) {
        this.financeDataService = financeDataService;
    }

    @CrossOrigin
    @PutMapping("/{clubId}/addToFinanceTable")
    public String addFinanceDataToFinanceTable(@PathVariable Long clubId, @RequestBody @Valid final AddFinanceDataRequest addFinanceDataRequest) {
        financeDataService.addDataToFinanceTable(clubId,addFinanceDataRequest.toFinanceData());
        return "success ";
    }
}
