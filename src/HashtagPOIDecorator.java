public class HashtagPOIDecorator extends POIDecorator {
    private String[] hashTags;

    public HashtagPOIDecorator(IPOI decoratedPOI, String... tags) {
        super(decoratedPOI);
        this.hashTags = tags;
    }

    @Override
    public String getInformation() {
        // String.join을 사용하면 배열의 각 요소를 "#"으로 시작하게 하고 ", "으로 쉽게 합칠 수 있다.
        String hashtagString = String.join(", ", java.util.Arrays.stream(hashTags)
                .map(tag -> "#" + tag)
                .toArray(String[]::new));
        return super.getInformation() + " [" + hashtagString + "]";

    }
}
