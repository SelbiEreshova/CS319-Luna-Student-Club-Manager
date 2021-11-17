package luna.clubverse.backend.financetable.entity;

import lombok.Getter;
import lombok.experimental.Accessors;
import luna.clubverse.backend.common.entity.BaseEntity;
import luna.clubverse.backend.financedata.entity.FinanceData;

import javax.persistence.*;
import java.util.Set;


@Entity
@Getter
@Accessors(fluent = true)
@Table(name = "finance_table")
public class FinanceTable extends BaseEntity {

    private boolean totalMoney;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "finance_data_id")
    private Set<FinanceData> financeData;

}
