package luna.clubverse.backend.financetable.entity;

import lombok.Getter;
import lombok.experimental.Accessors;
import luna.clubverse.backend.common.entity.BaseEntity;
import luna.clubverse.backend.financedata.entity.FinanceData;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;


@Entity
@Getter
@Accessors(fluent = true)
@Table(name = "finance_table")
public class FinanceTable extends BaseEntity {

    private boolean totalMoney;

    // private Club club;

    @OneToMany()
   private Set<FinanceData> financeData;


}
