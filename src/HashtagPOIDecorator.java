// POI에 여러 개의 해시태그 정보를 추가
public class HashtagPOIDecorator extends POIDecorator {
    // 추가할 여러 해시태그를 문자열 배열로 저장
    private String[] hashTags;

    // 생성자: 장식할 객체와 가변 인자인 태그들을 받음
    public HashtagPOIDecorator(IPOI decoratedPOI, String... tags) {
        super(decoratedPOI);
        this.hashTags = tags;
    }

    // 원본 정보 뒤에 해시태그 목록을 형식에 맞춰 덧붙여 반환
    @Override
    public String getInformation() {
        // 태그 배열을 스트림으로 변환하고, 각 태그 앞에 "#"을 붙인 후, ", "로 연결
        String hashtagString = String.join(", ", java.util.Arrays.stream(hashTags)
                .map(tag -> "#" + tag)
                .toArray(String[]::new));
        return super.getInformation() + " [" + hashtagString + "]";
    }
}