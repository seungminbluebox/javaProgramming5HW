package midtermExtra2.strategy;

// 연속된 공백을 하나로 합치는 정규화 전략
// 공백문자 찾아서 단일 공백으로 변환
// trim()으로 앞뒤 공백도 함께 제거
public class CollapseWhitespaceNormalizer implements ITextNormalizer {
    @Override
    public String normalize(String raw) {
        return raw.replaceAll("\\s+", " ").trim();
    }

}
