package midtermExtra1.observer;

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
