package org.tavirutyuty.history.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.tavirutyuty.history.model.AccessEventEntity;
import org.tavirutyuty.history.model.AccessedFilesEntity;
import org.tavirutyuty.history.model.HistoryDTO;
import org.tavirutyuty.history.repository.AccessEventRepository;
import org.tavirutyuty.history.repository.AccessedFilesRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class HistoryServiceTest {
    private HistoryService service;
    private AccessedFilesRepository accessedFilesRepository;
    private AccessEventRepository accessEventRepository;

    @BeforeEach
    public void setUp() {
        accessEventRepository = mock(AccessEventRepository.class);
        accessedFilesRepository = mock(AccessedFilesRepository.class);
        service = new HistoryService(accessEventRepository, accessedFilesRepository);
    }

    @Test
    public void testGetAllHistory() {
        String request = "/home/user";
        String filename1 = "file1.txt";
        String filename2 = "file2.txt";

        Long event_id = 42L;
        AccessEventEntity accessEventEntity = new AccessEventEntity();
        accessEventEntity.setId(event_id);
        accessEventEntity.setAccessTime(LocalDateTime.now());
        accessEventEntity.setRequest(request);

        Long accessedFile1_id = 43L;
        AccessedFilesEntity accessedFile1 = new AccessedFilesEntity();
        accessedFile1.setId(accessedFile1_id);
        accessedFile1.setFilename(filename1);
        accessedFile1.setEvent(accessEventEntity);

        Long accessedFile2_id = 44L;
        AccessedFilesEntity accessedFile2 = new AccessedFilesEntity();
        accessedFile2.setId(accessedFile2_id);
        accessedFile2.setFilename(filename2);
        accessedFile2.setEvent(accessEventEntity);

        when(accessEventRepository.findAll()).thenReturn(List.of(accessEventEntity));
        when(accessedFilesRepository.findAll()).thenReturn(List.of(accessedFile1, accessedFile2));

        List<HistoryDTO> result = service.getAllHistory();
        assertEquals(1, result.size());
        HistoryDTO historyDTO = result.getFirst();
        assertEquals(request, historyDTO.request());
        assertEquals(Set.of(filename1, filename2), historyDTO.returnedFileNames());
    }
}