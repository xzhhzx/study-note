import org.example.DemoApplication;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest(
        classes = DemoApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
)
@TestPropertySource("classpath:application.properties")
@AutoConfigureMockMvc
public class DemoApplicationTests {

    private static final Logger log = LoggerFactory.getLogger(DemoApplicationTests.class);

    @Autowired
    ApplicationContext ac;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    @Test
    public void requestTest() throws InterruptedException {
        try {
            mockMvc
                    .perform(post("/ping"))
                    .andDo(print());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void concurrentRequestsTest() throws InterruptedException {
        final int threadNum = 4;
        CountDownLatch countDownLatch = new CountDownLatch(threadNum);

        for (int i = 0; i < threadNum; i++) {
            Thread.sleep(100);
            new Thread(() -> {
                try {
                    ResponseEntity<String> response = restTemplateBuilder.build()
                            .getForEntity("http://127.0.0.1:8080/ping", String.class);
                    System.out.println("response: === " + response);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                } finally {
                    countDownLatch.countDown();
                }
            }).start();
        }

        countDownLatch.await();
        System.out.println("Latch released");
    }

    /**
     * [CAUTION] Using mockMvc to perform concurrent requests is WRONG!
     * The requests do NOT pass through Tomcat
     */
//    @Test
    public void concurrentRequestsTestWithMockMVC() throws InterruptedException {
        final int threadNum = 4;
        CountDownLatch countDownLatch = new CountDownLatch(threadNum);

        for (int i = 0; i < threadNum; i++) {
            new Thread(() -> {
                try {
                    mockMvc
                            .perform(post("/ping"))
                            .andDo(print());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                } finally {
                    countDownLatch.countDown();
                }
            }).start();
        }

        countDownLatch.await();
        System.out.println("Latch released");
    }
}