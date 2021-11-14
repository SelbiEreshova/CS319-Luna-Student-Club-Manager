package luna.clubverse.backend.financetable.entity;

import lombok.Getter;
import lombok.experimental.Accessors;
import luna.clubverse.backend.common.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Getter
@Accessors(fluent = true)
@Table(name = "finance_table")
public class FinanceTable extends BaseEntity {

    private boolean totalMoney;

    // private Club club;

   // private FinanceData[] financeData = null ;


}
