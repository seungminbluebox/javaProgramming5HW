public class CategoryPOIDecorator extends POIDecorator {
    private String category;

    public CategoryPOIDecorator(IPOI decoratedPOI, String category) {
        super(decoratedPOI);
        this.category = category;
    }

    @Override
    public String getInformation() {
        return super.getInformation() + " [Category: " + category + "]";
    }
}
