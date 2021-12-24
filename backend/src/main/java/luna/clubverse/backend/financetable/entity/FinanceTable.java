package luna.clubverse.backend.financetable.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import luna.clubverse.backend.common.entity.BaseEntity;
import luna.clubverse.backend.financedata.entity.FinanceData;
import luna.clubverse.backend.financedata.enumuration.FinanceDataStatus;

import javax.persistence.*;
import java.util.Set;


@Entity
@Getter
@Setter
@Accessors(fluent = true)
@Table(name = "finance_table")
public class FinanceTable extends BaseEntity {

    private double totalMoney;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,mappedBy = "financeTable")
    private Set<FinanceData> financeData;

    public boolean addTransaction(FinanceData newFinanceData) {

        if(newFinanceData.status() == null) {
            return false;
        }
        financeData.add(newFinanceData);

        updateMoney(newFinanceData);

        return true;
    }

    public boolean updateTransaction(FinanceData oldOne, FinanceData newOne) {

        if( (newOne.status() == null) || (oldOne.status() == null)) {
            return false;
        }

        deleteMoney(oldOne);
        updateMoney(newOne );


        return true;
    }

    private void deleteMoney(FinanceData oldOne) {

            if(oldOne.status().equals(FinanceDataStatus.INCOME)) {
            totalMoney = totalMoney - oldOne.amountOfMoney();
        } else {
            totalMoney = totalMoney + oldOne.amountOfMoney();
        }
    }

    private void updateMoney(FinanceData newFinanceData) {
        if(newFinanceData.status().equals(FinanceDataStatus.INCOME)) {
            totalMoney = totalMoney + newFinanceData.amountOfMoney();
        } else {
            totalMoney = totalMoney - newFinanceData.amountOfMoney();
        }
    }


}
