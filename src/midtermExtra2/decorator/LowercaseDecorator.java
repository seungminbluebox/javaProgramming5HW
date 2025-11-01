package midtermExtra2.decorator;

// 모든 문자를 소문자로 변환하는 데코레이터
public class LowercaseDecorator extends TextDecorator {
    public LowercaseDecorator(ITextSource inner) {
        super(inner);
    }

    @Override
    protected String process(String s) {
        return s.toLowerCase();
    }

}
