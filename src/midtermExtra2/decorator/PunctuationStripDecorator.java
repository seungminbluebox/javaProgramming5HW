package midtermExtra2.decorator;

// 모든 문장부호를 제거하는 데코레이터
public class PunctuationStripDecorator extends TextDecorator {
    public PunctuationStripDecorator(ITextSource inner) {
        super(inner);
    }

    @Override
    protected String process(String s) {
        return s.replaceAll("\\p{Punct}", "");
    }

}
