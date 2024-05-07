package ru.grak.brt.service.billing;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.grak.brt.repository.ClientRepository;

@Service
@RequiredArgsConstructor
public class AuthorizationService {

    private final ClientRepository clientsRepository;

    /**
     * Проверяет, авторизован ли абонент с указанным msisdn.
     * <p>
     * Результат метода кэшируется с использованием Spring Cache.
     * Реализация с map/set не была использована, т.к не подходит
     * для распределенных приложений (хранится в памяти одного процесса).
     *
     * @param msisdn номер телефона абонента.
     * @return true, если абонент авторизован, false в противном случае.
     */
    @Cacheable("msisdns")
    public boolean isAuthorizedMsisdn(String msisdn) {
        return clientsRepository
                .existsByPhoneNumber(msisdn);
    }

}
