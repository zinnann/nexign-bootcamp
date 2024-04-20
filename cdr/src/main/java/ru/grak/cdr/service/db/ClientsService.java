package ru.grak.cdr.service.db;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.grak.cdr.entity.Clients;
import ru.grak.cdr.repository.ClientsRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientsService {

    private final ClientsRepository clientsRepository;

    public List<Clients> getClientsList(){
        return clientsRepository.findAll();
    }

}
