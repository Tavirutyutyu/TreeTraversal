package org.tavirutyutyu.treewalk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tavirutyutyu.treewalk.model.entity.AccessEventEntity;
import org.tavirutyutyu.treewalk.model.DTO.AccessedFilesDTO;
import org.tavirutyutyu.treewalk.model.entity.AccessedFilesEntity;
import org.tavirutyutyu.treewalk.repository.AccessEventRepository;
import org.tavirutyutyu.treewalk.repository.AccessedFilesRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        Set<String> fileNames = getFileNames(directory);
        save(directory, fileNames);
        return new AccessedFilesDTO(fileNames);
    }

    private Set<String> getFileNames(String path) throws NoSuchFileException {
        String fullPath = isRunningInContainer() ? "/host" + path : path;
        try (Stream<Path> paths = Files.walk(Paths.get(fullPath))) {
            return paths.filter(Files::isRegularFile).map(p -> p.getFileName().toString()).collect(Collectors.toSet());
        } catch (IOException e) {
            throw new NoSuchFileException(fullPath);
        }
    }

    private void save(String directory, Set<String> accessedFiles) {
        String username = System.getenv("HOST_USERNAME");
        AccessEventEntity accessEvent = new AccessEventEntity();
        accessEvent.setUsername(username);
        accessEvent.setRequest("Requested Directory: " + directory);
        accessEvent.setAccessTime(LocalDateTime.now());
        accessEvent = eventRepository.save(accessEvent);
        for (String fileName : accessedFiles) {
            AccessedFilesEntity accessedFile = new AccessedFilesEntity();
            accessedFile.setFilename(fileName);
            accessedFile.setEvent(accessEvent);
            filesRepository.save(accessedFile);
        }
    }

    private boolean isRunningInContainer() {
        String podmanEnv = System.getenv("container");
        return "podman".equalsIgnoreCase(podmanEnv);
    }

}
