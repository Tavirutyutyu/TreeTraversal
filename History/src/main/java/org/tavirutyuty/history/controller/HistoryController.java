package org.tavirutyuty.history.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tavirutyuty.history.model.HistoryDTO;
import org.tavirutyuty.history.service.HistoryService;

import java.util.List;

@Tag(name = "History API", description = "Endpoints for fetching historical file access records.")
@RestController
public class HistoryController {
    private final HistoryService service;

    @Autowired
    public HistoryController(HistoryService service) {
        this.service = service;
    }

    @Operation(
            summary = "Retrieve all historical records",
            description = "Fetches the full history of previously accessed files stored in the database.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved history records",
                            content = @Content(schema = @Schema(implementation = HistoryDTO.class))
                    ),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @GetMapping("/history")
    public List<HistoryDTO> getAllHistory() {
        return service.getAllHistory();
    }
}
