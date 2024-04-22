package ru.grak.brt.service.billing;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.grak.brt.repository.ClientRepository;
import ru.grak.common.dto.CallDataRecordDto;

@Service
@RequiredArgsConstructor
public class AuthorizationService {

    private final ClientRepository clientsRepository;

    public boolean isAuthorizedRecord(CallDataRecordDto callDataRecord) {
        return clientsRepository
                .existsByPhoneNumber(callDataRecord.getMsisdnFirst());
    }
}
