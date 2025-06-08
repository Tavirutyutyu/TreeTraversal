package org.tavirutyutyu.treewalk.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tavirutyutyu.treewalk.model.AccessedFilesDTO;
import org.tavirutyutyu.treewalk.model.DirectoryDTO;
import org.tavirutyutyu.treewalk.service.TreeWalkerService;

import java.io.IOException;

@RestController
public class TreeWalkerController {
    private final TreeWalkerService service;

    @Autowired
    public TreeWalkerController(TreeWalkerService service) {
        this.service = service;
    }

    @Operation(summary = "This endpoint will return all file names from a given directory, without any duplications. The endpoint will need an absolute path in a String given as request parameter.")
    @GetMapping("/get_unique")
    public AccessedFilesDTO getUnique(@RequestParam DirectoryDTO directory) throws IOException {
        return service.getUnique(directory.directory());
    }
}
