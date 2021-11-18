package luna.clubverse.backend.financetable.service;

import luna.clubverse.backend.financedata.controller.response.FinanceDataQueryResponse;
import luna.clubverse.backend.financetable.entity.FinanceTable;
import luna.clubverse.backend.financetable.repository.FinanceTableRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class FinanceTableService {

    private final FinanceTableRepository financeTableRepository;

    public FinanceTableService(FinanceTableRepository financeTableRepository) {
        this.financeTableRepository = financeTableRepository;
    }

    public List<FinanceDataQueryResponse> getFinanceDataList(Long financeTableId) {
        FinanceTable financeTableDB = financeTableRepository.findById(financeTableId)
                .orElseThrow(()->new EntityNotFoundException("The finance table with the id " + financeTableId + " could not be found."));

        return financeTableDB.financeData().stream().map(FinanceDataQueryResponse::new).toList();
    }
}
