// POI가 특정 반경 내에 있는지 여부를 추가
public class ProximityPOIDecorator extends POIDecorator {
    // 기준이 될 현재 위치와 반경 정보 저장
    private Location currentLocation;
    private double radiusMeter;

    // 생성자: 장식할 객체, 현재 위치, 반경
    public ProximityPOIDecorator(IPOI decoratedPOI, Location currentLocation, double radiusMeter) {
        super(decoratedPOI);
        this.currentLocation = currentLocation;
        this.radiusMeter = radiusMeter;
    }

    // 거리를 계산해 반경 내에 있을 경우에만 "WithIn" 문자열 추가
    @Override
    public String getInformation() {
        String baseInfo = super.getInformation();
        POI originalPOI = POIDecorator.unwrapPOI(decoratedPOI);
        double distance = GeoUtil.distanceMeters(originalPOI.location, currentLocation);

        // 거리가 반경 이내일 때만 정보 추가
        if (distance <= radiusMeter) {
            return baseInfo + " (WithIn)";
        } else {
            return baseInfo;
        }
    }
}