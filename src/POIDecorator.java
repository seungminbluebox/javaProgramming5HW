public abstract class POIDecorator implements IPOI {
    protected IPOI decoratedPOI;

    public POIDecorator(IPOI decoratedPOI) {
        this.decoratedPOI = decoratedPOI;
    }

    @Override
    public String getInformation() {
        return decoratedPOI.getInformation();

    }

    // 재귀 호출을 사용하여 데코레이터 껍질을 벗겨내는 방식
    static POI unwrapPOI(IPOI node) {
        // node가 원본 POI 객체이면, 바로 반환
        if (node instanceof POI) {
            return (POI) node;
        }
        // node가 데코레이터라면, 그 데코레이터가 감싸고 있는 다음 객체를 넣어서 다시 unwrapPOI 호출
        else {
            return unwrapPOI(((POIDecorator) node).decoratedPOI);
        }
    }
}
