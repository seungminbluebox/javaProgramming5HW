package midtermExtra1.factoryBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import midtermExtra1.observer.POI;

import java.lang.reflect.Type;

// JSON 파일을 읽어 POI 객체 리스트를 생성하는 유틸리티 클래스
public class POIGsonFileLoader {
    public static List<ComplexPOI> loadComplexPOI(String filePath) throws java.io.IOException {
        List<ComplexPOI> result = new ArrayList<>();
        try (FileReader reader = new FileReader(filePath)) {
            JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
            JsonArray poisArray = jsonObject.getAsJsonArray("pois");

            for (JsonElement element : poisArray) {
                JsonObject obj = element.getAsJsonObject();

                String name = obj.get("name").getAsString();

                JsonObject locObj = obj.getAsJsonObject("location");

                String category = obj.get("category").getAsString();

                JsonArray hashtagsArray = obj.getAsJsonArray("hashtags");
                String[] hashtags = jsonArrayToStringArray(hashtagsArray);

                if (locObj == null)
                    continue;
                Double lat = readDouble(locObj, "latitude", "lat");
                Double lon = readDouble(locObj, "longitude", "lon");
                if (lat == null || lon == null)
                    continue;

                // 팩토리&빌더 패턴을 사용하여 complexPOI 객체 생성
                // 팩토리로 빌더 생성
                ComplexPOI.POIBuilder builder = POIBuilderFactory.createPOIBuilder(name, new Location(lat, lon),
                        category,
                        hashtags);
                // 빌더로 complexPOI 생성
                ComplexPOI poi = builder.build();
                result.add(poi);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
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

    private static final Gson GSON = new Gson();

    private static String[] jsonArrayToStringArray(JsonArray array) {
        if (array == null || array.size() == 0) {
            return null;
        }

        Type stringArrayType = new TypeToken<String[]>() {
        }.getType();
        return GSON.fromJson(array, stringArrayType);
    }
}