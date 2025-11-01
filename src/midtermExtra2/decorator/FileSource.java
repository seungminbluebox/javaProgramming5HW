package midtermExtra2.decorator;

// 파일에서 텍스트를 읽음
// 파일 경로를 받아 파일의 내용을 읽어옴
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;
import java.lang.Exception;

public class FileSource implements ITextSource {
    private final Path path;

    public FileSource(String filePath) {
        this.path = Paths.get(filePath);
    }

    @Override
    public String read() throws Exception {
        return Files.readString(path, StandardCharsets.UTF_8);
    }

}
