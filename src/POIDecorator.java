// 모든 데코레이터 클래스들의 부모가 되는 추상 클래스
// 데코레이터의 기본 구조와 공통 기능을 정의
public abstract class POIDecorator implements IPOI {
    // 감쌀 대상이 되는 객체를 저장하는 변수
    protected IPOI decoratedPOI;

    // 생성자
    public POIDecorator(IPOI decoratedPOI) {
        this.decoratedPOI = decoratedPOI;
    }

    // 자신이 감싸고 있는 객체의 getInformation을 그대로 호출
    @Override
    public String getInformation() {
        return decoratedPOI.getInformation();
    }

    // 여러 겹으로 쌓인 데코레이터를 재귀적으로 벗겨내는 메서드
    static POI unwrapPOI(IPOI node) {
        // 만약 현재 노드가 원본 POI 객체이면, 그대로 반환
        if (node instanceof POI) {
            return (POI) node;
        }
        // 데코레이터라면, 한 단계 안쪽의 객체를 대상으로 다시 unwrapPOI를 호출
        else {
            return unwrapPOI(((POIDecorator) node).decoratedPOI);
        }
    }
}