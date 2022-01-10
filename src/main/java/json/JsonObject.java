package json;
import java.util.HashMap;
import java.util.Map;
/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class JsonObject extends Json {
    HashMap<String, Json> jsonObject;
    public JsonObject(JsonPair... jsonPairs) {
        jsonObject = new HashMap<>();
        for (JsonPair jsonPair : jsonPairs) {
            jsonObject.put(jsonPair.key, jsonPair.value);
        }
    }

    @Override
    public String toJson() {
        String toJson = "{";
        for (String key: jsonObject.keySet()) {
            toJson += key + ": " + jsonObject.get(key).toJson() + ", ";
        }
        if (toJson.equals("{")) {
            return "{}";
        }
        return toJson.substring(0, toJson.length() - 2) + "}";
    }

    public void add(JsonPair jsonPair) {
        this.jsonObject.put(jsonPair.key, jsonPair.value);
    }

    public Json find(String name) {
        return this.jsonObject.get(name);
    }

    public JsonObject projection(String... names) {
        JsonObject projection = new JsonObject();
        for (String name :
                names) {
            if (jsonObject.get(name) != null) {
                projection.add(new JsonPair(name, jsonObject.get(name)));
            }
        }
        return projection;
    }
}
