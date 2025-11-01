package midtermExtra2.strategy;

// 텍스트 처리의 컨텍스트 역할
// 전략 패턴의 Context 역할
// 여러 정규화 전략을 리스트로 관리, 순차적으로 적용

import java.util.List;

public class TextProcessor {
    private List<ITextNormalizer> textNormalizers;

    public TextProcessor(List<ITextNormalizer> textNormalizers) {
        this.textNormalizers = textNormalizers;
    }

    public String process(String input) {
        String output = input;
        for (ITextNormalizer normalizer : textNormalizers) {
            output = normalizer.normalize(output);
        }
        return output;
    }

}
