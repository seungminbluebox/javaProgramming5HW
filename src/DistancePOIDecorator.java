public class DistancePOIDecorator extends POIDecorator {
    private Location currentLocation;

    public DistancePOIDecorator(IPOI decoratedPOI, Location currentLocation) {
        super(decoratedPOI);
        this.currentLocation = currentLocation;
    }

    @Override
    public String getInformation() {
        POI originalPOI = POIDecorator.unwrapPOI(decoratedPOI);
        double distance = GeoUtil.distanceMeters(originalPOI.location, currentLocation);

        return super.getInformation() + String.format(" (Distance: %.1fm)", distance);

    }
}
