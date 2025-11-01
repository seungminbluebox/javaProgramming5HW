package midtermExtra1.observer;

public class POI {
    public final String name;
    public final Location location;

    public POI(String name, Location location) {
        this.name = name;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return this.location;
    }

    // IPOI 인터페이스 구현: 가장 기본적인 정보(이름 + 위치)를 문자열로 반환
    public String getInformation() {
        return name + " @ (" + location + ")";
    }

    @Override
    public String toString() {
        return "{" +
                " name='" + getName() + "'" +
                ", location='" + getLocation() + "'" +
                "}";
    }
}