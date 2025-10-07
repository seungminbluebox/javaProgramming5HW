import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        // 1. 원본 POI 리스트를 로드하고 화면에 출력
        List<POI> pois = POIGsonFileLoader.load("NewPOI.json");
        pois.forEach(System.out::println);

        // 2. Category, Hashtag가 미리 장식된 POI 리스트를 로드하고 화면에 출력
        List<IPOI> decoratedBase = POIGsonFileLoader.loadAsDecorated("NewPOI.json");
        System.out.println("\n== Base decorated (from JSON) ==");
        decoratedBase.forEach(d -> System.out.println(" - " + d.getInformation()));

        // 3. 사용자의 가상 이동 경로를 배열로 정의
        Location[] path = new Location[] {
                new Location(37.5779, 126.9760),
                new Location(37.5784, 126.9766),
                new Location(37.5789, 126.9772),
                new Location(37.5795, 126.9787),
                new Location(37.5797, 126.9794),
                new Location(37.5800, 126.9800)
        };

        // 4. 경로를 따라 이동하며 데코레이터를 동적으로 적용하는 시뮬레이션 시작
        System.out.println("\n== Along path ==");
        for (Location cur : path) {
            // 현재 위치 정보 출력
            System.out.printf("\n-- step @ (%.5f, %.5f)%n", cur.getLat(), cur.getLon());

            // 각 POI에 대해 데코레이터들을 연쇄적으로 적용
            for (IPOI baseChain : decoratedBase) {
                // 거리 정보 장식 추가
                IPOI finalDecorated = new DistancePOIDecorator(baseChain, cur);
                // 근접 정보 장식 추가
                finalDecorated = new ProximityPOIDecorator(finalDecorated, cur, 200.0);
                // yourcode: 날씨 정보 장식 추가
                finalDecorated = new WeatherPOIDecorator(finalDecorated);

                // 모든 장식이 완료된 최종 정보 출력
                System.out.println(finalDecorated.getInformation());
            }
        }
    }
}