package midtermExtra1.factoryBuilder;

// POI 빌더 객체를 생성하는 팩토리 클래스
// 팩토리 패턴과 빌더 패턴을 조합
// 정적 팩토리 메서드로 빌더 인스턴스 생성
public class POIBuilderFactory {
    // simple factory
    public static ComplexPOI.POIBuilder createPOIBuilder(String name, Location location, String c, String[] hs) {
        ComplexPOI.POIBuilder builder = ComplexPOI.builder(name, location);
        builder.category(c);
        builder.hashtags(hs);
        return builder;
    }
}
