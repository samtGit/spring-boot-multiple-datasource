package com.tutorials.multipledatasource.primary.repo;

import com.tutorials.multipledatasource.primary.domain.PrimaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrimaryRepo extends JpaRepository<PrimaryEntity, Long> {
}
