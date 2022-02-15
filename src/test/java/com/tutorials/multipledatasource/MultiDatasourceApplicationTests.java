package com.tutorials.multipledatasource;

import com.tutorials.multipledatasource.primary.domain.PrimaryEntity;
import com.tutorials.multipledatasource.primary.repo.PrimaryRepo;
import com.tutorials.multipledatasource.second.domain.SecondEntity;
import com.tutorials.multipledatasource.second.repo.SecondRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class MultiDatasourceApplicationTests {


    @Autowired
    private PrimaryRepo primaryRepo;

    @Autowired
    private SecondRepo secondRepo;


    @Test
    void addPrimaryEntity() {
        final String comment = "this is a comment in the primary table";
        PrimaryEntity primaryEntity = new PrimaryEntity(comment);
        final PrimaryEntity savedEntity = primaryRepo.save(primaryEntity);
        Assertions.assertTrue(savedEntity.getComment().contains(comment));

    }

    @Test
    void addSecondEntity() {
        final String comment = "this is a comment in the second table";
        SecondEntity secondEntity = new SecondEntity(comment);
        final SecondEntity savedEntity = secondRepo.save(secondEntity);
        Assertions.assertTrue(savedEntity.getComment().contains(comment));

    }

}
