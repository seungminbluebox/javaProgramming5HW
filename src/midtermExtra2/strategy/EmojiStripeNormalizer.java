package midtermExtra2.strategy;

// 이모지 제거하는 정규화 전략
// Unicode So와 Cn 문자 제거
// 정규식 패턴 사용
public class EmojiStripeNormalizer implements ITextNormalizer {
    @Override
    public String normalize(String raw) {
        return raw.replaceAll("[\\p{So}\\p{Cn}]", "");
    }

}
