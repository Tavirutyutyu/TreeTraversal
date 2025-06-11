package org.tavirutyutyu.treewalk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tavirutyutyu.treewalk.model.AccessedFilesEntity;
import org.tavirutyutyu.treewalk.model.DTO.HistoryDTO;
import org.tavirutyutyu.treewalk.repository.AccessEventRepository;
import org.tavirutyutyu.treewalk.repository.AccessedFilesRepository;

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

    public List<HistoryDTO> getHistory() {
        return accessEventRepository.findAll().stream().map(event -> {
            Set<String> accessedFiles = accessedFilesRepository
                    .findAllByEventId(event.getId())
                    .stream()
                    .map(AccessedFilesEntity::getFilename).collect(Collectors.toSet());
            return new HistoryDTO(event.getUsername(), event.getAccessTime(), event.getRequest(), accessedFiles);
        }).collect(Collectors.toList());
    }
}
