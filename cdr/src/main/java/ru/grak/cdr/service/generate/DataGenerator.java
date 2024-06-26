package ru.grak.cdr.service.generate;

import org.antlr.v4.runtime.misc.Pair;
import org.springframework.stereotype.Service;
import ru.grak.cdr.entity.Abonent;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class DataGenerator {

    public long generateRandomCallDuration(int maxCallDurationInMinutes) {
        return ThreadLocalRandom.current().nextInt(maxCallDurationInMinutes * 60) + 1;
    }

    public Pair<String, String> getRandomPairOfMsisdn(List<Abonent> abonents) {

        String firstMsisdn = abonents.get(ThreadLocalRandom.current().nextInt(abonents.size()))
                .getPhoneNumber();
        String secondMsisdn = abonents.get(ThreadLocalRandom.current().nextInt(abonents.size()))
                .getPhoneNumber();

        while (secondMsisdn.equals(firstMsisdn)) {
            secondMsisdn = abonents.get(ThreadLocalRandom.current().nextInt(abonents.size()))
                    .getPhoneNumber();
        }

        return new Pair(firstMsisdn, secondMsisdn);
    }

    public long generateRandomGap() {
        return ThreadLocalRandom.current().nextInt(1000000, 1500000);
    }

    @Deprecated
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
