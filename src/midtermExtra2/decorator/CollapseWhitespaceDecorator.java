package midtermExtra2.decorator;

// 연속된 공백을 하나로 합치는 데코레이터
// 1개 이상 연속된 공백문자 찾아서 단일 공백으로 교체
// trim()으로 앞뒤 공백도 제거
public class CollapseWhitespaceDecorator extends TextDecorator {
    public CollapseWhitespaceDecorator(ITextSource inner) {
        super(inner);
    }

    @Override
    protected String process(String s) {
        return s.replaceAll("\\s+", " ").trim();
    }

}
