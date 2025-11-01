package midtermExtra2.decorator;

import midtermExtra2.strategy.*;
import midtermExtra2.strategy.TextProcessor;
import java.util.List;
import java.util.Arrays;

public class MainTest {
    public MainTest() throws Exception {
        String input = " Hello to the world!!!      ( 한국어-테스트 😊) !!!  ,123,  ... b";

        // pipeline 1
        ITextSource src = new StringSource(input);
        System.out.println("[Original Text Source]");
        System.out.println(src.read());
        ITextSource step1 = new EmojiStripDecorator(src);
        System.out.println("[Step1 EmojiStrip]");
        System.out.println(step1.read());
        ITextSource step2 = new LowercaseDecorator(step1);
        System.out.println("[Step2 Lowercase]");
        System.out.println(step2.read());
        ITextSource step3 = new PunctuationStripDecorator(step2);
        System.out.println("[Step3 PunctuationStrip]");
        System.out.println(step3.read());
        ITextSource step4 = new CollapseWhitespaceDecorator(step3);
        System.out.println("[Step4 CollapseWhitespace]");
        System.out.println(step4.read());
        System.out.println("== Final [StringSource] ==");
        System.out.println(step4.read());

        // pipeline 2 파일소스 (input.txt)에 위와 동일한 text
        ITextSource input2 = new FileSource("input.txt");
        ITextSource pipeline2 = new CollapseWhitespaceDecorator(
                new PunctuationStripDecorator(
                        new LowercaseDecorator(
                                new EmojiStripDecorator(input2))));
        System.out.println("== Final [FileSource] ==");
        System.out.println(pipeline2.read());

        // pipeline3 TextNormalizationUtils을 사용하여 정규화 작업
        String normalized = TextNormalizationUtils.collapseWhitespace(
                TextNormalizationUtils.stripPunctuation(
                        TextNormalizationUtils.toLowerCase(
                                TextNormalizationUtils.stripEmoji(input))));
        System.out.println("== Final [TextNormalizationUtils] ==");
        System.out.println(normalized);

        // pipeline4 Strategy 패턴을 사용하여 정규화 작업
        List<ITextNormalizer> normalizers = Arrays.asList(
                new EmojiStripeNormalizer(),
                new LowercaseNormalizer(),
                new PunctuationStripNormalizer(),
                new CollapseWhitespaceNormalizer());
        TextProcessor pipeline4 = new TextProcessor(normalizers);
        String normalized2 = pipeline4.process(input);
        System.out.println("== Final [TextProcessor] ==");
        System.out.println(normalized2);
    }

}
