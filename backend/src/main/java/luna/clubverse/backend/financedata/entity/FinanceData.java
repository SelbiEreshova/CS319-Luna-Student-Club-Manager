package luna.clubverse.backend.financedata.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import luna.clubverse.backend.common.entity.BaseEntity;
import luna.clubverse.backend.financedata.enumuration.FinanceDataStatus;
import luna.clubverse.backend.financetable.entity.FinanceTable;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Accessors(fluent = true)
@Table(name = "finance_data")
public class FinanceData extends BaseEntity {

    @Column(name = "amount_of_money")
    private double amountOfMoney;

    @Column(name = "status")
    private FinanceDataStatus status;

    @Column(name = "explanation")
    private String explanation;

    @Column(name = "date")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "finance_table_id")
    private FinanceTable financeTable;
    
    protected FinanceData() {
    }

    public FinanceData(double amountOfMoney, FinanceDataStatus status, String explanation, LocalDate date) {
        this.amountOfMoney = amountOfMoney;
        this.status = status;
        this.explanation = explanation;
        this.date = date;
    }

    public void setFinanceTable(FinanceTable financeTable) {
        this.financeTable = financeTable;
    }

    public void update(FinanceData financeData) {

        this.financeTable().updateTransaction(this, financeData);

        this.amountOfMoney = financeData.amountOfMoney;
        this.status = financeData.status;
        this.explanation = financeData.explanation;
        this.date = financeData.date;


    }
}
