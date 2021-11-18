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
    @PutMapping("/{financeTableId}/addToFinanceTable")
    public String addFinanceDataToFinanceTable(@PathVariable Long financeTableId, @RequestBody @Valid final AddFinanceDataRequest addFinanceDataRequest) {
        financeDataService.addDataToFinanceTable(financeTableId,addFinanceDataRequest.toFinanceData());
        return "success "; // return type will be changed, except from get requests, there will be same type of response
    }
}
