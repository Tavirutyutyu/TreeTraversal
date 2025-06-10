package org.tavirutyutyu.treewalk.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tavirutyutyu.treewalk.model.AccessedFilesDTO;
import org.tavirutyutyu.treewalk.model.DirectoryDTO;
import org.tavirutyutyu.treewalk.service.DocumentationService;
import org.tavirutyutyu.treewalk.service.TreeWalkerService;

import java.io.IOException;

@Tag(name = "Tree Walker API", description = "Endpoints related to directory scanning and documentation.")
@RestController
public class TreeWalkerController {
    private final TreeWalkerService treeWalkerService;
    private final DocumentationService documentationService;

    @Autowired
    public TreeWalkerController(TreeWalkerService treeWalkerService, DocumentationService documentationService) {
        this.treeWalkerService = treeWalkerService;
        this.documentationService = documentationService;
    }

    @Operation(
            summary = "Get unique file names from a directory",
            description = "Returns all file names from a given absolute path, without any duplications.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved unique file names",
                            content = @Content(schema = @Schema(implementation = AccessedFilesDTO.class))
                    ),
                    @ApiResponse(responseCode = "404", description = "Directory not found."),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )    @GetMapping("/get_unique")
    public AccessedFilesDTO getUnique(@RequestParam DirectoryDTO directory) throws IOException {
        return treeWalkerService.getUnique(directory.directory());
    }

    @Operation(
            summary = "View project documentation",
            description = "Returns the project documentation from the README.md file hosted on the GitHub repository. The content is served as styled HTML.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved documentation",
                            content = @Content(mediaType = "text/html")
                    ),
                    @ApiResponse(responseCode = "500", description = "Could not fetch documentation")
            }
    )
    @GetMapping(value ="/documentation", produces = "text/html")
    public String getDocumentation() throws IOException {
        return documentationService.getDocumentation();
    }
}
