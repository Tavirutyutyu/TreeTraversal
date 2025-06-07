package org.tavirutyutyu.treewalk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tavirutyutyu.treewalk.model.AccessEventEntity;

@Repository
public interface AccessEventRepository extends JpaRepository<AccessEventEntity, Long> {
}
