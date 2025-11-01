package midtermExtra2.strategy;

// 문장부호 제거하는 정규화 전략
// \p{Punct}로 제거
public class PunctuationStripNormalizer implements ITextNormalizer {
    @Override
    public String normalize(String raw) {
        return raw.replaceAll("\\p{Punct}", "");
    }

}
