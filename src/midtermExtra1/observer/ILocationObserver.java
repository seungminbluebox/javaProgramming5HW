package midtermExtra1.observer;

// 옵저버 패턴의 Observer 인터페이스
public interface ILocationObserver {
    void onChangedLocation(Location newLocation);
}
