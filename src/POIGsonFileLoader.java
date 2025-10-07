import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class POIGsonFileLoader {
    public static List<POI> load(String filePath) throws java.io.IOException {
        List<POI> result = new ArrayList<>();

        try (FileReader reader = new FileReader(filePath)) {
            // Parse the JSON file
            JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
            JsonArray poisArray = jsonObject.getAsJsonArray("pois");

            // Iterate over pois array in JSON
            for (JsonElement element : poisArray) {

                JsonObject obj = element.getAsJsonObject();
                // name
                String name = obj.get("name").getAsString();
                // location
                JsonObject locObj = obj.getAsJsonObject("location");
                if (locObj == null)
                    continue;

                Double lat = readDouble(locObj, "latitude", "lat");
                Double lon = readDouble(locObj, "longitude", "lon");
                if (lat == null || lon == null)
                    continue;

                result.add(new POI(name, new Location(lat, lon)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static List<IPOI> loadAsDecorated(String filePath) throws java.io.IOException {
        // 1. 먼저 load() 메서드를 호출해서 원본 POI 리스트를 가져온다.
        List<POI> originalPois = load(filePath);
        List<IPOI> decoratedList = new ArrayList<>();

        // 2. 원본 POI 리스트를 순회하면서 데코레이터를 적용한다.
        for (POI poi : originalPois) {
            // 예시: 모든 POI에 'Historic Site' 카테고리와 'seoul', 'palace' 해시태그를 추가
            IPOI decoratedPOI = new CategoryPOIDecorator(poi, "Historic Site");
            decoratedPOI = new HashtagPOIDecorator(decoratedPOI, "seoul", "palace");
            decoratedList.add(decoratedPOI);
        }

        return decoratedList;
    }

    // Allow a number or number string (if none, then null)
    private static Double readDouble(JsonObject o, String primary, String alternate) {
        JsonElement e = o.get(primary);
        if (e == null || e.isJsonNull())
            e = o.get(alternate);
        if (e == null || e.isJsonNull())
            return null;

        if (e.isJsonPrimitive()) {
            try {
                if (e.getAsJsonPrimitive().isNumber())
                    return e.getAsDouble();
                if (e.getAsJsonPrimitive().isString())
                    return Double.parseDouble(e.getAsString().trim());
            } catch (NumberFormatException ignore) {
            }
        }
        return null;
    }
}
