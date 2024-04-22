package ru.grak.cdr.service.db;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.grak.cdr.entity.Abonent;
import ru.grak.cdr.repository.AbonentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AbonentService {

    private final AbonentRepository abonentRepository;

    public List<Abonent> getClientsList(){
        return abonentRepository.findAll();
    }

}
