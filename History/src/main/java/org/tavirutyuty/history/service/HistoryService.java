package org.tavirutyuty.history.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tavirutyuty.history.History;
import org.tavirutyuty.history.model.AccessEventEntity;
import org.tavirutyuty.history.model.AccessedFilesEntity;
import org.tavirutyuty.history.model.HistoryDTO;
import org.tavirutyuty.history.repository.AccessEventRepository;
import org.tavirutyuty.history.repository.AccessedFilesRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class HistoryService {
    private final AccessEventRepository accessEventRepository;
    private final AccessedFilesRepository accessedFilesRepository;

    @Autowired
    public HistoryService(AccessEventRepository accessEventRepository, AccessedFilesRepository accessedFilesRepository) {
        this.accessEventRepository = accessEventRepository;
        this.accessedFilesRepository = accessedFilesRepository;
    }

    public List<HistoryDTO> getAllHistory() {
        return accessEventRepository.findAll()
                .stream()
                .map(event -> {
                    Set<String> fileNames = accessedFilesRepository.findAllByEventId(event.getId())
                            .stream()
                            .map(AccessedFilesEntity::getFilename)
                            .collect(Collectors.toSet());

                    return new HistoryDTO(
                            event.getUsername(),
                            event.getAccessTime(),
                            event.getRequest(),
                            fileNames
                    );
                })
                .toList();
    }
}
