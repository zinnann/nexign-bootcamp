package ru.grak.cdr.service;

import org.springframework.stereotype.Service;
import ru.grak.common.dto.CallDataRecordDto;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@Service
public class FileService {

    //в prop вынести
    private static final String CDR_FOLDER_PATH = "cdr/";
    private static final String CDR_FILE_PREFIX = "cdr";
    private static final String CDR_FILE_EXTENSION = ".txt";

    private static int counter;

    public void saveCallDataRecords(List<CallDataRecordDto> chronologicalCdrList) throws IOException, SQLException {

        String cdrFileName = CDR_FOLDER_PATH + CDR_FILE_PREFIX + "_" + counter + CDR_FILE_EXTENSION;

        try (PrintWriter writer = new PrintWriter(new FileWriter(cdrFileName))) {

            for (CallDataRecordDto callDataRecord : chronologicalCdrList) {
                writer.println(cdrFormat(callDataRecord));
            }
        }
    }

//    public void getCallDataRecords() throws IOException, SQLException {}

    private static String cdrFormat(CallDataRecordDto dataRecord) {
        return dataRecord.getTypeCall().getNumericValueOfType() + ", "
                + dataRecord.getMsisdnFirst() + ", "
                + dataRecord.getMsisdnSecond() + ", "
                + dataRecord.getDateTimeStartCall() + ", "
                + dataRecord.getDateTimeEndCall();
    }

    private void createDirectory(String path) {
        new File(path).mkdirs();
    }
}
