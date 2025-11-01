package midtermExtra2.decorator;

// 문자열을 직접 입력받아 처리하는 ConcreteComponent
public class StringSource implements ITextSource {
    private final String data;

    public StringSource(String data) {
        this.data = data;
    }

    @Override
    public String read() {
        return data;
    }

}
