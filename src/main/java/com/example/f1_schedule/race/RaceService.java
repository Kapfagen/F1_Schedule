package com.example.f1_schedule.race;

import com.example.f1_schedule.database.RaceRecord;
import com.example.f1_schedule.database.RaceRepository;
import com.example.f1_schedule.parser.RaceParser;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class RaceService {

    @Autowired
    private RaceParser raceParser;

    @Autowired
    private RaceRepository raceRepository;

    public List<RaceDTO> getAll() throws IOException {
        List<Race> races = raceParser.get();
        return races.stream()
                .map((race) -> {
                    String date = RaceDTO.formatDateRange(race.getStartDate(), race.getEndDate());
                    return new RaceDTO(race.getCountry(),
                            date,
                            race.getTrack(),
                            race.getEvents(),
                            raceRepository.existsByCountryIgnoreCase(race.getCountry()));
                })
                .toList();
    }

    public void save(RaceRecord raceRecord) {
        raceRepository.save(raceRecord);
    }

    public void remove(String name) {
        RaceRecord raceRecord = raceRepository.findByCountryIgnoreCase(name);
        if (raceRecord != null) {
            raceRepository.deleteById(raceRecord.getId());
        }
    }

    public byte[] getExcel() throws IOException {
        List<RaceRecord> raceRecords = raceRepository.findAll();
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Race Records");
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("ID");
            headerRow.createCell(1).setCellValue("Country");
            headerRow.createCell(2).setCellValue("Date");
            int rowNum = 1;
            for (RaceRecord record : raceRecords) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(record.getId());
                row.createCell(1).setCellValue(record.getCountry());
                row.createCell(2).setCellValue(record.getDate());
            }
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            return outputStream.toByteArray();
        }
    }
}
