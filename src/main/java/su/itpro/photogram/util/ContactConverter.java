package su.itpro.photogram.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.json.JsonMapper;
import java.util.HashMap;
import java.util.Map;
import su.itpro.photogram.exception.JsonConvertException;

public class ContactConverter {

  private static final JsonMapper mapper = new JsonMapper();

  private ContactConverter() {
  }

  public static String toJson(Map<String, String> map) {
    try {
      return mapper.writeValueAsString(map);
    } catch (JsonProcessingException e) {
      throw new JsonConvertException("Error convert Map to Json");
    }
  }

  public static Map<String, String> toMap(String json) {
    try {
      return mapper.readValue(json, new TypeReference<HashMap<String, String>>() { });
    } catch (JsonProcessingException e) {
      throw new JsonConvertException("Error convert Json to Map");
    }
  }
}
