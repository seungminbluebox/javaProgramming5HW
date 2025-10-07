// POI와 현재 위치 사이의 거리 정보를 추가
public class DistancePOIDecorator extends POIDecorator {
    // 거리 계산의 기준이 될 현재 위치 정보 저장
    private Location currentLocation;

    // 생성자: 장식할 객체와 현재 위치를 받음
    public DistancePOIDecorator(IPOI decoratedPOI, Location currentLocation) {
        super(decoratedPOI);
        this.currentLocation = currentLocation;
    }

    // 원본 POI의 위치와 현재 위치를 기준으로 거리를 계산하여 덧붙여 반환
    @Override
    public String getInformation() {
        // unwrapPOI로 원본 POI를 찾아 위치 정보를 획득
        POI originalPOI = POIDecorator.unwrapPOI(decoratedPOI);
        double distance = GeoUtil.distanceMeters(originalPOI.location, currentLocation);

        // 형식에 맞춰 거리 정보를 추가
        return super.getInformation() + String.format(" (Distance: %.1fm)", distance);
    }
}