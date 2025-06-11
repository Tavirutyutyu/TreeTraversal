package org.tavirutyutyu.treewalk.model.DTO;

import java.time.LocalDateTime;
import java.util.Set;

public record HistoryDTO(String username, LocalDateTime accessTime, String request, Set<String> returnedFileNames) {
}
