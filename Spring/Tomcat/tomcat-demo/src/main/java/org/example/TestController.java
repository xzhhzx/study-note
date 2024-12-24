package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Test API: print 0~9 for 10 seconds
 */
@RestController
public class TestController {

    private static final Logger log = LoggerFactory.getLogger(TestController.class);

    @RequestMapping("/ping")
    public String ping() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);
            log.info(String.valueOf(i));
        }
        return "PONG";
    }
}