package org.example;

import org.example.Enrichment.EnrichmentType;
import org.example.Enrichment.MsisdnEnrichment;
import org.example.Enrichment.EnrichmentProcessor;
import org.example.Enrichment.EnrichmentService;
import org.example.Exception.InvalidPhoneFormatException;
import org.example.User.MemoryRepository;
import org.example.User.Message;
import org.example.User.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {
  public static void main(String[] args) throws InvalidPhoneFormatException {
    User me = new User("Anton", "Khazin");
    MemoryRepository repa = new MemoryRepository();
    Map<String, String> content = new HashMap<>();
    ArrayList<EnrichmentProcessor> processors = new ArrayList<>();

    repa.updateByMSISDN("89201337006", me);
    content.put("action", "send hometask");
    content.put("expected assessment", "10");
    content.put("msisdn", "89201337006");
    processors.add(new MsisdnEnrichment(repa));
    Message message = new Message(content, EnrichmentType.MSISDN);
    EnrichmentService enrichment = new EnrichmentService(processors);

    Message enrichedMessage = enrichment.enrich(message);
    System.out.println(enrichedMessage.getContent());
  }
}