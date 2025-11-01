package midtermExtra1.observer;

// 옵저버 패턴의 Subject 인터페이스
// 위치 변경 알림을 받을 옵저버들 관리
// 옵저버 추가/제거/알림 기능 정의
public interface ILocationSubject {
    void attatchObserver(ILocationObserver observer);

    void detatchObserver(ILocationObserver observer);

    void notifyLocationObservers();
}
