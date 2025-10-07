import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        // list of poi (NewPOI.json)
        List<POI> pois = POIGsonFileLoader.load("NewPOI.json");
        pois.forEach(System.out::println);

        // category & hashtag decorator (used in POIGsonFileLoader.loadAsDecorated
        // method)
        List<IPOI> decoratedBase = POIGsonFileLoader.loadAsDecorated("NewPOI.json");
        System.out.println("== Base decorated (from JSON) ==");
        decoratedBase.forEach(d -> System.out.println(" - " + d.getInformation()));

        Location[] path = new Location[] {
                new Location(37.5779, 126.9760),
                new Location(37.5784, 126.9766),
                new Location(37.5789, 126.9772),
                new Location(37.5795, 126.9787),
                new Location(37.5797, 126.9794),
                new Location(37.5800, 126.9800)
        };

        System.out.println("\n== Along path ==");
        for (Location cur : path) {
            System.out.printf("\n-- step @ (%.5f, %.5f)%n", cur.getLat(), cur.getLon());
            for (IPOI baseChain : decoratedBase) {
                // distance & proximity 데코레이터 적용해서 출력
                IPOI distanceDecorated = new DistancePOIDecorator(baseChain, cur);
                IPOI proximityDecorated = new ProximityPOIDecorator(distanceDecorated, cur, 0.1); // 0.1km 예시
                IPOI finalDecorated = new WeatherPOIDecorator(proximityDecorated);

                System.out.println(finalDecorated.getInformation());
            }
        }
    }
}