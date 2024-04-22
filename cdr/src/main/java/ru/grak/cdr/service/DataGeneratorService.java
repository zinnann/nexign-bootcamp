package ru.grak.cdr.service;

import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.stereotype.Service;
import ru.grak.cdr.service.db.AbonentService;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class DataGeneratorService {

    private final AbonentService clientsService;

    public static int generateRandomCallDuration(int maxCallDurationInMinutes) {
        return ThreadLocalRandom.current().nextInt(maxCallDurationInMinutes * 60) + 1;
    }

    public Pair<String,String> getPairRandomMsisdnFromDB(){
        var clients = clientsService.getClientsList();

        String firstMsisdn = clients.get(ThreadLocalRandom.current().nextInt(clients.size()))
                .getPhoneNumber();
        String secondMsisdn = clients.get(ThreadLocalRandom.current().nextInt(clients.size()))
                .getPhoneNumber();

        while(secondMsisdn.equals(firstMsisdn)){
            secondMsisdn = clients.get(ThreadLocalRandom.current().nextInt(clients.size()))
                    .getPhoneNumber();
        }

        return new Pair(firstMsisdn,secondMsisdn);
    }

    public static long generateRandomDateTime(int year, int month) {
        var dateTime = LocalDateTime.of(
                year,
                month,
                ThreadLocalRandom.current().nextInt(28) + 1,
                ThreadLocalRandom.current().nextInt(24),
                ThreadLocalRandom.current().nextInt(60),
                ThreadLocalRandom.current().nextInt(60));

        return convertToUnixTime(dateTime);
    }

    public static long convertToUnixTime(LocalDateTime dateTime) {
        return dateTime.toEpochSecond(ZoneOffset.UTC);
    }

    @Deprecated
    public static String generatePhoneNumber() {
        StringBuilder phoneNumber = new StringBuilder("7");
        for (int i = 0; i < 10; i++) {
            phoneNumber.append(ThreadLocalRandom.current().nextInt(10));
        }
        return phoneNumber.toString();
    }
}
