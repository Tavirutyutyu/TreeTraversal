package org.tavirutyuty.history.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tavirutyuty.history.model.HistoryDTO;
import org.tavirutyuty.history.service.HistoryService;

import java.util.List;

@RestController
public class HistoryController {
    private final HistoryService service;

    @Autowired
    public HistoryController(HistoryService service) {
        this.service = service;
    }

    @GetMapping("/history")
    public List<HistoryDTO> getAllHistory(){
        return service.getAllHistory();
    }
}
