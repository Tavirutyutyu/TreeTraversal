package org.tavirutyutyu.treewalk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tavirutyutyu.treewalk.model.AccessedFilesEntity;

@Repository
public interface AccessedFilesRepository extends JpaRepository<AccessedFilesEntity, Long> {
}
