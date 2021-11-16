package luna.clubverse.backend.financedata.repository;

import luna.clubverse.backend.financedata.entity.FinanceData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FinanceDataRepository extends JpaRepository<FinanceData,Long> {
}
