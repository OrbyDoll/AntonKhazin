import org.example.User.*;
import org.example.Enrichment.*;
import org.example.Exception.*;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnrichmentServiceTest {
  private UserRepository userRepository = new MemoryRepository();
  private ArrayList<EnrichmentProcessor> processors = new ArrayList<>();
  private EnrichmentService service;
  Map<String, String> stringStringMap;
  Message message;

  @Before
  public void setup() throws InvalidPhoneFormatException {
    userRepository.updateByMSISDN("88005553535", new User("Vasya", "Ivanov"));
    processors.add(new MsisdnEnrichment(userRepository));
    service = new EnrichmentService(processors);
    stringStringMap = new HashMap<>();
    stringStringMap.put("action", "button_click");
    stringStringMap.put("page", "book_card");
    stringStringMap.put("msisdn", "88005553535");
    message = new Message(stringStringMap, EnrichmentType.MSISDN);
  }

  @Test
  void shouldSucceedEnrichmentInConcurrentEnvironmentSuccessfully() throws InterruptedException, InvalidPhoneFormatException {
    Message message = new Message(stringStringMap, EnrichmentType.MSISDN);
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
