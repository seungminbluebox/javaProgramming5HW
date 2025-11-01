package midtermExtra1.factoryBuilder;

import java.util.List;

// Factory Method와 Builder 패턴 테스트
// JSON 파일에서 POI 데이터 로드
// 빌더로 ComplexPOI 객체 생성
// 팩토리로 POI 빌더 생성하는 과정 테스트
public class MainTest {
    public MainTest() throws Exception {
        // POI with category and hashtag
        List<ComplexPOI> list = POIGsonFileLoader.loadComplexPOI("NewPOI.json");
        System.out.println("=== Complex POI List ===");
        list.forEach(System.out::println);
    }
}
