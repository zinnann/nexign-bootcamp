package ru.grak.brt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import ru.grak.brt.service.BrtService;
import ru.grak.common.dto.CallDataRecordDto;

import java.io.IOException;
import java.util.List;

@SpringBootApplication
@EnableCaching
public class BrtApplication {

    public static void main(String[] args)throws IOException {
        ApplicationContext context = SpringApplication.run(BrtApplication.class, args);

        BrtService brtService = context.getBean(BrtService.class);

        List<CallDataRecordDto> authorizedCdr = brtService.getAuthorizedCallDataRecord();

        System.out.println("Authorized CDR:");
        for (CallDataRecordDto cdr : authorizedCdr) {
            System.out.println(cdr);
        }
    }
}
