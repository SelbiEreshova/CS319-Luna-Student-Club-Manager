package luna.clubverse.backend.financedata.controller.response;

import lombok.Getter;
import luna.clubverse.backend.financedata.entity.FinanceData;
import luna.clubverse.backend.financedata.enumuration.FinanceDataStatus;

import java.time.LocalDate;

@Getter
public class FinanceDataQueryResponse {
    private final double amountOfMoney;
    private final FinanceDataStatus status;
    private final String explanation;
    private final LocalDate date;

    public FinanceDataQueryResponse(final FinanceData financeData) {
        this.amountOfMoney = financeData.amountOfMoney();
        this.status = financeData.status();
        this.explanation = financeData.explanation();
        this.date = financeData.date();
    }
}
