public class POI implements IPOI {
    public final String name;
    public final Location location;

    public POI(String name, Location location) {
        this.name = name;
        this.location = location;
    }

    @Override
    public String getInformation() {
        // 가장 안쪽 객체의 정보를 반환한다.
        return name + " @ (" + location + ")";
    }

    @Override
    public String toString() {
        return name + " @ " + location;
    }
}
