package luna.clubverse.backend.financedata.service;


import luna.clubverse.backend.club.entity.Club;
import luna.clubverse.backend.club.repository.ClubRepository;
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
    private final ClubRepository clubRepository;

    public FinanceDataService(FinanceDataRepository financeDataRepository, FinanceTableRepository financeTableRepository, ClubRepository clubRepository) {
        this.financeDataRepository = financeDataRepository;
        this.financeTableRepository = financeTableRepository;
        this.clubRepository = clubRepository;
    }

    public void addDataToFinanceTable(Long clubId, FinanceData financeData) {

        Club club = clubRepository.findById(clubId)
                .orElseThrow(()->new EntityNotFoundException("The Club with the id " + clubId + " could not be found."));

        FinanceTable financeTableDB =  club.getFinanceTable();

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
