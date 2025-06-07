package org.tavirutyuty.history.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tavirutyuty.history.model.AccessEventEntity;

@Repository
public interface AccessEventRepository extends JpaRepository<AccessEventEntity, Long> {
}
