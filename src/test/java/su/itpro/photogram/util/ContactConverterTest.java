package su.itpro.photogram.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import su.itpro.photogram.util.converter.ContactConverter;

class ContactConverterTest {

  @Test
  void toJson() {
    Map<String, String> dataMap = new HashMap<>();
    dataMap.put("key", "value");

    String jsonString = ContactConverter.toJson(dataMap);

    assertNotNull(jsonString);
    assertTrue(jsonString.contains("key"));
    assertTrue(jsonString.contains("value"));
  }

  @Test
  void toMap() {
    String jsonString = "{\"key\": \"value\"}";

    Map<String, String> contacts = ContactConverter.toMap(jsonString);

    assertNotNull(jsonString);
    assertTrue(contacts.containsKey("key"));
    assertEquals("value", contacts.get("key"));
  }
}