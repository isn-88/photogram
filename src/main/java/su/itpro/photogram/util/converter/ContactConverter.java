package su.itpro.photogram.util.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.json.JsonMapper;
import java.util.HashMap;
import java.util.Map;

public class ContactConverter {

  private static final JsonMapper mapper = new JsonMapper();

  private ContactConverter() {
  }

  public static String toJson(Map<String, String> contacts) {
    if (contacts == null) {
      return null;
    }
    try {
      return mapper.writeValueAsString(contacts);
    } catch (JsonProcessingException e) {
      throw new JsonConvertException("Error convert Map to Json");
    }
  }

  public static Map<String, String> toMap(String json) {
    if (json == null) {
      return null;
    }
    try {
      return mapper.readValue(json, new TypeReference<HashMap<String, String>>() { });
    } catch (JsonProcessingException e) {
      throw new JsonConvertException("Error convert Json to Map");
    }
  }
}
