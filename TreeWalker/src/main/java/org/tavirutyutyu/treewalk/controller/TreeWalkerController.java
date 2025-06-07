package org.tavirutyutyu.treewalk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @GetMapping("/get_unique")
    public AccessedFilesDTO getUnique(@RequestBody DirectoryDTO directory) throws IOException {
        return service.getUnique(directory.directory());
    }
}
