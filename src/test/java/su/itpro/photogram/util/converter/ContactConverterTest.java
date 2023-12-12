package su.itpro.photogram.util.converter;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

class ContactConverterTest {

  private static final String EMPTY_JSON = "{}";

  @Test
  void toJson_isNullValue() {
    Map<String, String> contacts = null;

    String actualResult = ContactConverter.toJson(contacts);

    assertThat(actualResult).isNull();
  }

  @Test
  void toJson_isEmptyValue() {
    Map<String, String> contacts = new HashMap<>();

    String actualResult = ContactConverter.toJson(contacts);

    assertThat(actualResult).isNotNull();
    assertThat(actualResult).isEqualTo(EMPTY_JSON);
  }

  @Test
  void toJson_isPresentValue() {
    String contactName = "contactName";
    String contactValue = "contactValue";
    Map<String, String> contacts = new HashMap<>();
    contacts.put(contactName, contactValue);

    String actualResult = ContactConverter.toJson(contacts);

    assertThat(actualResult).isNotNull();
    assertThat(actualResult).contains(contactName);
    assertThat(actualResult).contains(contactValue);
  }

  @Test
  void toMap_isNullValue() {
    String jsonString = null;

    Map<String, String> actualResult = ContactConverter.toMap(jsonString);

    assertThat(actualResult).isNull();
  }

  @Test
  void toMap_isEmptyValue() {
    String jsonString = "{}";

    Map<String, String> actualResult = ContactConverter.toMap(jsonString);

    assertThat(actualResult).isNotNull();
    assertThat(actualResult).isEmpty();
  }

  @Test
  void toMap_isPresentValue() {
    String contactName = "Name";
    String contactValue = "Contact";

    String jsonString = "{\"" + contactName + "\": \"" + contactValue + "\"}";

    Map<String, String> actualResult = ContactConverter.toMap(jsonString);

    assertThat(actualResult).isNotNull();
    assertThat(actualResult).containsEntry(contactName, contactValue);
  }
}