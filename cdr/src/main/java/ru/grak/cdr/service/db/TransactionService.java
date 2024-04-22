package ru.grak.cdr.service.db;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.grak.cdr.entity.Transaction;
import ru.grak.cdr.repository.TransactionRepository;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;

    //dto в транзакцию mapper
    public void saveTransaction(Transaction callDataRecord){
        transactionRepository.save(callDataRecord);
    }

}
