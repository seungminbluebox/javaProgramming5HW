package midtermExtra2.decorator;

// 이모지 제거하는 데코레이터
// Unicode So, Cn 카테고리 문자 제거
// 정규식 사용해서 이모지 패턴 찾아 제거
public class EmojiStripDecorator extends TextDecorator {
    public EmojiStripDecorator(ITextSource inner) {
        super(inner);
    }

    @Override
    protected String process(String s) {
        return s.replaceAll("[\\p{So}\\p{Cn}]", "");
    }

}
