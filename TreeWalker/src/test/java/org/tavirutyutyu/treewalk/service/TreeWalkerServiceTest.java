package org.tavirutyutyu.treewalk.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.tavirutyutyu.treewalk.model.AccessedFilesDTO;
import org.tavirutyutyu.treewalk.repository.AccessEventRepository;
import org.tavirutyutyu.treewalk.repository.AccessedFilesRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

class TreeWalkerServiceTest {
    private AccessEventRepository accessEventRepository;
    private AccessedFilesRepository accessedFilesRepository;
    private TreeWalkerService treeWalkerService;

    @BeforeEach
    void setUp() {
        accessEventRepository = mock(AccessEventRepository.class);
        accessedFilesRepository = mock(AccessedFilesRepository.class);
        treeWalkerService = new TreeWalkerService(accessEventRepository, accessedFilesRepository);
    }

    @Test
    public void testTreeWalkerWithValidInput() throws IOException {
        Path tempDir = Files.createTempDirectory("treewalker");
        Files.createFile(Files.createDirectories(tempDir.resolve("folder1")).resolve("a.txt"));
        Files.createFile(Files.createDirectories(tempDir.resolve("folder2")).resolve("b.txt"));
        Files.createFile(Files.createDirectories(tempDir.resolve("folder3")).resolve("a.txt")); // same name, different folder

        AccessedFilesDTO accessedFiles = treeWalkerService.getUnique(tempDir.toString());

        assertNotNull(accessedFiles);
        assertEquals(Set.of("a.txt", "b.txt"), accessedFiles.fileNames());
    }
}