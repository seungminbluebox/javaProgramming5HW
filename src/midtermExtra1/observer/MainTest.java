package midtermExtra1.observer;

// 옵저버 패턴 테스트
// LocationManager에 여러 옵저버 등록
// 위치 변경 이벤트 발생시키고 전파되는지 테스트
// 거리 추적, 근접 경보, 위치 표시 기능 테스트

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainTest {
    public MainTest() throws Exception {

        String filepath = "NewPOI.json";
        List<POI> pois = POIGsonFileLoader.load(filepath);
        pois.forEach(System.out::println);

        // 위치 Subject(5미터, 500ms)
        LocationManager locationManager = new LocationManager(5, 500);

        // Park 사용자 UserDisplay 등록
        UserDisplay park = new UserDisplay("Park");
        locationManager.attatchObserver(park);

        // 광화문을 처음 시작 위치로 한 DistanceTracker 등록
        DistanceTracker tracker = new DistanceTracker(new Location(37.576119, 126.976885));
        locationManager.attatchObserver(tracker);

        // 다른 POI를 가진 여러 ProximityAlert 옵저버 등록
        // 특정 옵저버 인스턴스를 찾아 해제하기 위해 Map 에 저장
        Map<String, ProximityAlert> poiAlertMap = new HashMap<>();
        for (POI poi : pois) {
            ProximityAlert alert = new ProximityAlert(poi, 100);
            locationManager.attatchObserver(alert);
            poiAlertMap.put(poi.getName(), alert);
        }

        // 위치 이동 시뮬
        Location[] path = new Location[] {
                new Location(37.5779, 126.9760),
                new Location(37.5784, 126.9766),
                new Location(37.5789, 126.9772), // 근정전 근접 진입 가능
                new Location(37.5795, 126.9787),
                new Location(37.5797, 126.9794), // 향원정 근접 진입 가능
                new Location(37.5800, 126.9800) // 향원정 이탈 가능
        };

        // 위치가 업데이트 되면 등록된 Observer들이 반응
        for (Location g : path) {
            locationManager.updateLocation(g);
            Thread.sleep(600);
        }

        // park 옵저버 제거 및 lee 사용자 옵저버 등록
        locationManager.detatchObserver(park);
        UserDisplay lee = new UserDisplay("Lee");
        locationManager.attatchObserver(lee);

        System.out.println("\n--- DistanceTracker는 다시 광화문에서 시작 ---");
        tracker.setTotalDistance(0);
        tracker.setStartLocation(new Location(37.575119, 126.976885));

        System.out.println("\n--- 특정 POI 옵저버 해제 요청 ---");
        String targetName1 = "경복궁 근정문 및 행각";

        ProximityAlert alert1 = poiAlertMap.get(targetName1);
        if (alert1 != null) {
            locationManager.detatchObserver(alert1);
            System.out.println("옵저버 해제 완료 " + targetName1);
        } else {
            System.out.println("옵저버를 찾을 수 없음 " + targetName1);
        }
        for (Location loc : path) {
            locationManager.updateLocation(loc);
            Thread.sleep(600);
        }

    }
}