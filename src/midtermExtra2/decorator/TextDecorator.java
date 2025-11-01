package midtermExtra2.decorator;

// 데코레이터의  추상 클래스
// 데코레이터 패턴의 Decorator 역할
// 내부에 ITextSource를 가지고 있어서 체인 형성 가능
// 실제 처리는 하위 클래스의 process()에 위임
public abstract class TextDecorator implements ITextSource {
    private final ITextSource inner;

    public TextDecorator(ITextSource inner) {
        this.inner = inner;
    }

    @Override
    public String read() throws Exception {
        String s = inner.read();
        return process(s);
    }

    protected abstract String process(String s);
}
