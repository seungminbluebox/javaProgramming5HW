package midtermExtra1.factoryBuilder;

// 위치 정보를 담는 기본 클래스
// 위도(lat)와 경도(lon) 저장
// 불변 객체로 구현됨
public class Location {
    public final double lat;
    public final double lon;

    public Location(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    @Override
    public String toString() {
        return "{" +
                " lat='" + getLat() + "'" +
                ", lon='" + getLon() + "'" +
                "}";
    }
}
