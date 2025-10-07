// POI에 카테고리 정보를 추가하는 데코레이터
public class CategoryPOIDecorator extends POIDecorator {
    // 추가할 정보인 카테고리 문자열 저장
    private String category;

    // 생성자
    public CategoryPOIDecorator(IPOI decoratedPOI, String category) {
        super(decoratedPOI);
        this.category = category;
    }

    // 원본 정보 뒤에 카테고리 정보를 덧붙여서 반환
    @Override
    public String getInformation() {
        return super.getInformation() + " [Category: " + category + "]";
    }
}