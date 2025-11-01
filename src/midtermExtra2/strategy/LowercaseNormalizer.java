package midtermExtra2.strategy;

// 소문자 변환 정규화 전략
public class LowercaseNormalizer implements ITextNormalizer {
    @Override
    public String normalize(String raw) {
        return raw.toLowerCase();
    }

}
