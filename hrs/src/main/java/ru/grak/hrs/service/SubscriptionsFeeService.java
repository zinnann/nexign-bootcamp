//package ru.grak.hrs.service;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import ru.grak.common.dto.CallDataRecordDto;
//import ru.grak.common.enums.TypeTariff;
//
//import java.math.BigDecimal;
//import java.time.Instant;
//import java.time.LocalDate;
//import java.time.ZoneOffset;
//import java.util.Comparator;
//import java.util.Map;
//
//@Service
//@RequiredArgsConstructor
//public class SubscriptionsFeeService {
//
////    private final TrafficRepository trafficRepository;
////    private final TariffRepository tariffRepository;
//
//    public void subscriptionsFeeWithdrawal(CallDataRecordDto cdr){
//        String msisdn = cdr.getMsisdnFirst();
//        int currentMonth = extractMonth(cdr.getDateTimeStartCall());
//        int previousMonth = extractPreviousMonthFromTraffic(msisdn);
//
//        if (currentMonth > previousMonth){
//            Client client = clientRepository.findByPhoneNumber(msisdn);
//            BigDecimal monthlySubscriptionsFee = getTariffSubscriptionsFee(client);
//
//            if (monthlySubscriptionsFee.compareTo(BigDecimal.ZERO) > 0){
//                BigDecimal balance = client.getBalance();
//                int monthsForPayment = currentMonth - previousMonth;
//                BigDecimal updatedBalance = balance.subtract(
//                        monthlySubscriptionsFee.multiply(BigDecimal.valueOf(monthsForPayment)));
//                client.setBalance(updatedBalance);
//
//                clientRepository.save(client);
//            }
//        }
//    }
//
//    public void withdrawSubscriptionsFee(Map<String, Integer> usedMinutes){
//        for (var b: usedMinutes.entrySet()) {
//
//
//        }
//    }
//
//    private BigDecimal getTariffSubscriptionsFee(Client client){
//        TypeTariff tariff_id = client.getTariff();
//        return tariffRepository.findById(tariff_id).get()
//                .getSubscriptionFee();
//    }
//
//    private int extractMonth(long dateTime) {
//        return LocalDate.
//                ofInstant(Instant.ofEpochSecond(dateTime), ZoneOffset.UTC)
//                .getMonthValue();
//    }
//
//    //sql optimization
//    private int extractPreviousMonthFromTraffic(String msisdn){
//        return trafficRepository.findAllByMsisdn(msisdn)
//                .stream()
//                .max(Comparator.comparing(Traffic::getMonth)).get()
//                .getMonth();
//    }
//}
