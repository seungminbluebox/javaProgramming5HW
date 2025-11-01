package midtermExtra1.observer;

// 위치 관리자 클래스 - 옵저버 패턴의 ConcreteSubject
// 위치 업데이트 관리 및 옵저버들에게 알림 전파
// 거리 임계값, 최소 업데이트 간격 설정 가능
// 위치 변경 이벤트 및 전파 담당

import java.util.ArrayList;

public class LocationManager implements ILocationSubject {
    private ArrayList<ILocationObserver> observers = new ArrayList<>();
    private Location last = null;
    private long lastTsMs = 0;
    private final double distanceThresholdMeters;
    private final long minUpdateIntervalMs;

    public LocationManager(double distanceThresholdMeters, long minUpdateIntervalMs) {
        this.distanceThresholdMeters = distanceThresholdMeters;
        this.minUpdateIntervalMs = minUpdateIntervalMs;
    }

    public void attatchObserver(ILocationObserver observer) {
        observers.add(observer);
    }

    public void detatchObserver(ILocationObserver observer) {
        observers.remove(observer);
    }

    public void notifyLocationObservers() {
        for (ILocationObserver observer : observers) {
            observer.onChangedLocation(last);
        }
    }

    // public void updateLocation(Location curr) {
    // long now = System.currentTimeMillis();
    // if (last != null) {
    // double dist = GeoUtil.distanceMeters(last, curr);
    // if (dist < distanceThresholdMeters) {
    // return;
    // }
    // if (now - lastTsMs < minUpdateIntervalMs) {
    // return;
    // }
    // last = curr;
    // lastTsMs = now;
    // notifyLocationObservers();
    // }
    // }
    public void updateLocation(Location cur) {
        long now = System.currentTimeMillis();
        if (last != null) {
            double dist = GeoUtil.distanceMeters(last, cur);

            if (dist < distanceThresholdMeters)
                return;
            if (now - lastTsMs < minUpdateIntervalMs)
                return;
        }
        last = cur;
        lastTsMs = now;
        notifyLocationObservers();
    }
}
