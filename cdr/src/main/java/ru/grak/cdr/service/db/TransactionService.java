package ru.grak.cdr.service.db;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.grak.cdr.entity.CallDataRecord;
import ru.grak.cdr.repository.TransactionRepository;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public void saveTransaction(CallDataRecord callDataRecord){
        transactionRepository.save(callDataRecord);
    }

}
