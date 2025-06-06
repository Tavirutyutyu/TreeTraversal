package org.tavirutyutyu.treewalk.service;

import org.springframework.stereotype.Service;

@Service
public class TreeWalkerService {
    private AccessEventRepository eventRepository;
    private AccessedFilesRepository filesRepository;
}
