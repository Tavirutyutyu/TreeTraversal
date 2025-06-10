package org.tavirutyuty.history.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tavirutyuty.history.model.AccessedFilesEntity;

import java.util.Set;

@Repository
public interface AccessedFilesRepository extends JpaRepository<AccessedFilesEntity, Long> {
    Set<AccessedFilesEntity> findAllByEventId(Long id);
}
