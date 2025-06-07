package org.tavirutyutyu.treewalk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tavirutyutyu.treewalk.model.AccessedFilesDTO;
import org.tavirutyutyu.treewalk.repository.AccessEventRepository;
import org.tavirutyutyu.treewalk.repository.AccessedFilesRepository;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashSet;
import java.util.Set;

@Service
public class TreeWalkerService {
    private final AccessEventRepository eventRepository;
    private final AccessedFilesRepository filesRepository;

    @Autowired
    public TreeWalkerService(AccessEventRepository eventRepository, AccessedFilesRepository filesRepository) {
        this.eventRepository = eventRepository;
        this.filesRepository = filesRepository;
    }

    public AccessedFilesDTO getUnique(String directory) throws IOException {
        Set<String> fileNames = new HashSet<>();
        Files.walkFileTree(Path.of(directory), new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs){
                fileNames.add(file.getFileName().toString());
                return FileVisitResult.CONTINUE;
            }
            @Override
            public FileVisitResult visitFileFailed(Path dir, IOException exc){
                return FileVisitResult.CONTINUE;
            }
        });
        return new AccessedFilesDTO(fileNames);
    }
}
