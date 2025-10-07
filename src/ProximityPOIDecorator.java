public class ProximityPOIDecorator extends POIDecorator {
    private Location currentLocation;
    private double radiusMeter;

    public ProximityPOIDecorator(IPOI decoratedPOI, Location currentLocation, double radiusMeter) {
        super(decoratedPOI);
        this.currentLocation = currentLocation;
        this.radiusMeter = radiusMeter;
    }

    public String getInformation() {
        String baseInfo = super.getInformation();

        POI originalPOI = POIDecorator.unwrapPOI(decoratedPOI);
        double distance = GeoUtil.distanceMeters(originalPOI.location, currentLocation);

        if (distance <= radiusMeter) {
            return baseInfo + "WithIn";
        } else {
            return baseInfo;
        }
    }
}
