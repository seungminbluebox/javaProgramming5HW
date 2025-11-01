package midtermExtra1.factoryBuilder;

// POI(관심지점) 정보를 저장하는 클래스
// 빌더 패턴으로 객체 생성
// name, location, category, hashtags 등 다양한 속성 보유
// 불변 객체로 구현

import java.lang.reflect.Array;

import midtermExtra1.observer.POI;
import java.util.Arrays;

public class ComplexPOI {
    private final String name;
    private final Location location;
    private final String category;
    private final String[] hashtags;

    public ComplexPOI(POIBuilder builder) {
        this.name = builder.name;
        this.location = builder.location;
        this.category = builder.category;
        this.hashtags = builder.hashtags;
    }

    public Location getLocation() {
        return this.location;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String[] getHashtags() {
        return hashtags;
    }

    @Override
    public String toString() {
        return String.format("ComplexPOI : %s (%.4f, %.4f)\n" + " -카테고리: %s\n" + " -해시태그: %s", name, location.getLat(),
                location.getLon(), (category != null ? category : "없음"),
                (hashtags != null ? Arrays.toString(hashtags) : "없음"));
    }

    // 빌더 클래스를 외부에 노출
    public static POIBuilder builder(String name, Location location) {
        return new POIBuilder(name, location);
    }

    public static class POIBuilder {
        private final String name;
        private final Location location;
        private String category;
        private String[] hashtags;

        public POIBuilder(String name, Location location) {
            this.name = name;
            this.location = location;
        }

        public POIBuilder category(String category) {
            this.category = category;
            return this;
        }

        public POIBuilder hashtags(String[] hashtags) {
            this.hashtags = hashtags;
            return this;
        }

        public ComplexPOI build() {
            return new ComplexPOI(this);
        }
    }
}
