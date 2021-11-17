package luna.clubverse.backend.financedata.controller.request;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import luna.clubverse.backend.financedata.entity.FinanceData;
import luna.clubverse.backend.financedata.enumuration.FinanceDataStatus;

import java.time.LocalDate;

@RequiredArgsConstructor
@Getter
@ToString
public class AddFinanceDataRequest {

    private double amountOfMoney;

    private FinanceDataStatus status;

    private String explanation;

    private String date;

    public FinanceData toFinanceData() {
        return new FinanceData(amountOfMoney,status,explanation, LocalDate.parse(date));

    }
}
