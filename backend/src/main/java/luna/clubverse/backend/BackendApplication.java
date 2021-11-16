package luna.clubverse.backend;

import luna.clubverse.backend.financedata.entity.FinanceData;
import luna.clubverse.backend.financedata.enumuration.FinanceDataStatus;
import luna.clubverse.backend.financedata.repository.FinanceDataRepository;
import luna.clubverse.backend.financetable.entity.FinanceTable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;


@SpringBootApplication
public class BackendApplication {





	public static void main(String[] args) {


		SpringApplication.run(BackendApplication.class, args);
	}

}
