import org.example.User.*;
import org.example.Enrichment.*;
import org.example.Exceptions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnrichmentServiceTest {
  @Test
  void shouldSucceedEnrichmentInConcurrentEnvironmentSuccessfully() throws InterruptedException, InvalidPhoneFormatException {
    UserRepository userRepository = new MemoryRepository();
    userRepository.updateByMSISDN("88005553535", new User("Vasya", "Ivanov"));

    ArrayList<EnrichmentProcessor> processors = new ArrayList<>();
    processors.add(new MsisdnEnrichment(userRepository));
    EnrichmentService service = new EnrichmentService(processors);

    Map<String, String> stringStringMap = new java.util.HashMap<>();
    stringStringMap.put("action", "button_click");
    stringStringMap.put("page", "book_card");
    stringStringMap.put("msisdn", "88005553535");
    Message message = new Message(stringStringMap, Message.EnrichmentType.MSISDN);
    List<Message> results = new CopyOnWriteArrayList<>();
    ExecutorService executorService = Executors.newFixedThreadPool(5);
    CountDownLatch latch = new CountDownLatch(5);

    for (int i = 0; i < 5; i++) {

      executorService.submit(() -> {
        results.add(service.enrich(message));
        latch.countDown();
      });
    }

    latch.await();

    for (Message enrichedMessage : results) {
      assertEquals("Vasya", enrichedMessage.getContent().get("firstName"));
      assertEquals("Ivanov", enrichedMessage.getContent().get("lastName"));
    }

    executorService.shutdown();
  }
}
