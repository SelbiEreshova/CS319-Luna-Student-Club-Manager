package luna.clubverse.backend.club.controller.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import luna.clubverse.backend.financedata.controller.response.FinanceDataQueryResponse;
import luna.clubverse.backend.financetable.entity.FinanceTable;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class FinanceTableResponse {

    private double totalMoney;
    private List<FinanceDataQueryResponse> financeData;

    public FinanceTableResponse(final FinanceTable financeTable) {
        this.totalMoney = financeTable.totalMoney();
        this.financeData = financeTable.financeData()
                .stream().map(FinanceDataQueryResponse::new)
                .toList();
    }
}
