package com.example.f1_schedule.web;

import com.example.f1_schedule.database.RaceRecord;
import com.example.f1_schedule.race.RaceService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
public class ScheduleController {

    @Autowired
    private RaceService raceService;

    @GetMapping("/schedule")
    public String getSchedule(Model model) throws IOException {
        model.addAttribute("races", raceService.getAll());
        return "schedule";
    }

    @PostMapping("/schedule")
    public String saveSchedule(@ModelAttribute RaceRecord raceRecord, Model model) throws IOException {
        raceService.save(raceRecord);
        model.addAttribute("races", raceService.getAll());
        return "schedule";
    }

    @PostMapping("/schedule/remove")
    public String saveSchedule(@RequestParam String country, Model model) throws IOException {
        raceService.remove(country);
        return "redirect:/schedule";
    }

    @PostMapping("/export")
    @ResponseBody
    public ResponseEntity<byte[]> exportExcel(HttpServletResponse response) throws IOException {
        byte[] excelContent = raceService.getExcel();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "filename.xlsx");

        return new ResponseEntity<>(excelContent, headers, HttpStatus.OK);
    }
}
