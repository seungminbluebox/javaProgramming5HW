package midtermExtra1.observer;

// 근접 경보 - 옵저버 패턴의 ConcreteObserver
// POI와의 거리가 임계값 이내면 알림 발생
// 특정 POI에 대한 접근 감시
// 거리 기반 알림 기능 구현
public class ProximityAlert implements ILocationObserver {
    private final POI poi;
    private final double thresholdMeters;

    public ProximityAlert(POI poi, double thresholdMeters) {
        this.poi = poi;
        this.thresholdMeters = thresholdMeters;
    }

    @Override
    public void onChangedLocation(Location newLocation) {
        Location poiLocation = poi.getLocation();
        double distance = GeoUtil.distanceMeters(poiLocation, newLocation);
        if (distance <= thresholdMeters) {
            System.out.println("[PROXIMITY ALERT: " + poi.getName()
                    + "]: 목표지점(Lat: " + poi.getLocation().getLat() + ", Lon:"
                    + poi.getLocation().getLon() + ")에 근접했습니다. 거리: " + distance);
        }
    }

}
