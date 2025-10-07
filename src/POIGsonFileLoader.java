import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.*;

// JSON 파일을 읽어 POI 객체 리스트를 생성하는 유틸리티 클래스
public class POIGsonFileLoader {
    public static List<POI> load(String filePath) throws java.io.IOException {
        List<POI> result = new ArrayList<>();
        try (FileReader reader = new FileReader(filePath)) {
            JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
            JsonArray poisArray = jsonObject.getAsJsonArray("pois");

            for (JsonElement element : poisArray) {
                JsonObject obj = element.getAsJsonObject();
                String name = obj.get("name").getAsString();
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

    // 과제 템플릿용 메서드
    // JSON 파일을 로드하여 각 POI에 Category와 Hashtag 데코레이터를 미리 적용한 리스트를 반환
    public static List<IPOI> loadAsDecorated(String filePath) throws java.io.IOException {
        // load() 메서드로 원본 POI 리스트를 먼저 로드
        List<POI> originalPois = load(filePath);
        List<IPOI> decoratedList = new ArrayList<>();

        // 각 POI를 순회하며 데코레이터를 적용
        for (POI poi : originalPois) {
            IPOI decoratedPOI = new CategoryPOIDecorator(poi, "Historic Site");
            decoratedPOI = new HashtagPOIDecorator(decoratedPOI, "seoul", "palace");
            decoratedList.add(decoratedPOI);
        }
        return decoratedList;
    }

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