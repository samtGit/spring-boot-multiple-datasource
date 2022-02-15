package com.tutorials.multipledatasource.second.repo;

import com.tutorials.multipledatasource.second.domain.SecondEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecondRepo extends JpaRepository<SecondEntity, Long> {
}
