package midtermExtra1.observer;

// 사용자 위치 표시기 - 옵저버 패턴의 ConcreteObserver
// 위치 변경마다 콘솔에 출력
public class UserDisplay implements ILocationObserver {
    private String userName;

    public UserDisplay(String userName) {
        this.userName = userName;
    }

    @Override
    public void onChangedLocation(Location cur) {
        System.out.println("[" + userName + "]: 새로운 위치 수신됨. (위도: "
                + cur.getLat() + ", 경도: " + cur.getLon() + ")");
    }

}
