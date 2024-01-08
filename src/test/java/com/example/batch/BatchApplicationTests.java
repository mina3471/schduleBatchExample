package com.example.batch;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BatchApplicationTests {

    @Autowired
    TaskService taskService;
    @Test
    void contextLoads() {
    taskService.create_csv();
    }

}
