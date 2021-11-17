package luna.clubverse.backend.financedata.service;


import luna.clubverse.backend.financedata.entity.FinanceData;
import luna.clubverse.backend.financedata.enumuration.FinanceDataStatus;
import luna.clubverse.backend.financedata.repository.FinanceDataRepository;
import luna.clubverse.backend.financetable.entity.FinanceTable;
import luna.clubverse.backend.financetable.repository.FinanceTableRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
@Transactional
public class FinanceDataService {
    private final FinanceDataRepository financeDataRepository;
    private final FinanceTableRepository financeTableRepository;

    public FinanceDataService(FinanceDataRepository financeDataRepository, FinanceTableRepository financeTableRepository) {
        this.financeDataRepository = financeDataRepository;
        this.financeTableRepository = financeTableRepository;
    }

    public void addDataToFinanceTable(Long financeTableId, FinanceData financeData) {

        FinanceTable financeTableDB = financeTableRepository.findById(financeTableId)
                .orElseThrow(()->new EntityNotFoundException("The finance table with the id " + financeTableId + " could not be found."));
        double change = financeData.amountOfMoney();

        if(financeData.status()== FinanceDataStatus.OUTCOME){
            change=-change;
        }
        double newTotal = financeTableDB.totalMoney()+change;

        financeTableDB.totalMoney(newTotal);
        financeTableRepository.save(financeTableDB);

        financeData.setFinanceTable(financeTableDB);
        financeDataRepository.save(financeData);
    }



}
