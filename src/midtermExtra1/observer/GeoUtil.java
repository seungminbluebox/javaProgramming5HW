package midtermExtra1.observer;

// 지리적 거리 계산 유틸리티
public final class GeoUtil {
    public static double distanceMeters(Location a, Location b) {
        final double R = 6371000.0;
        double dLat = Math.toRadians(b.getLat() - a.getLat());
        double dLon = Math.toRadians(b.getLon() - a.getLon());
        double la1 = Math.toRadians(a.getLat()), la2 = Math.toRadians(b.getLat());
        double h = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(la1) * Math.cos(la2) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(h),
                Math.sqrt(1 - h));
        return R * c;
    }
}