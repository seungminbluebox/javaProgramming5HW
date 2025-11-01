package midtermExtra1.observer;

// 이동 거리 추적기 - 옵저버 패턴의 ConcreteObserver
// 위치 변경마다 이동 거리 누적 계산
// 시작 위치와 이전 위치 기록
// 총 이동거리 계산하는 옵저버
public class DistanceTracker implements ILocationObserver {

    private double totalDistance = 0.0;
    private Location startLocation;
    private Location prev;

    public DistanceTracker(Location startLocation) {
        this.startLocation = startLocation;
        this.prev = startLocation;
    }

    public Location getStartLocation() {
        return this.startLocation;
    }

    public void setStartLocation(Location startLocation) {
        this.startLocation = startLocation;
    }

    public double getTotalDistance() {
        return this.totalDistance;
    }

    public void setTotalDistance(double totalDistance) {
        this.totalDistance = totalDistance;
    }

    @Override
    public void onChangedLocation(Location location) {
        totalDistance += GeoUtil.distanceMeters(prev, location);
        System.out.println("[DISTANCE TRACKER]: 이동거리 (" + getTotalDistance() + ") meters");
    }
}
