package luna.clubverse.backend.financedata.entity;


import lombok.Getter;
import lombok.experimental.Accessors;
import luna.clubverse.backend.common.entity.BaseEntity;

import javax.persistence.Entity;

@Entity
@Getter
@Accessors(fluent = true)
public class FinanceData extends BaseEntity {

    private double amountOfMoney;

    private String status;

    private String explanation;

    private String date;



}
