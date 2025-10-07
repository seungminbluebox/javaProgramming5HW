import java.util.Random;

// yourcode
// POI의 위치에 따른 날씨 정보를 추가
public class WeatherPOIDecorator extends POIDecorator {

    // 추가할 날씨 정보 저장
    private String weather;

    // 생성자: 객체가 생성될 때 날씨 정보를 결정하여 저장
    public WeatherPOIDecorator(IPOI decoratedPOI) {
        super(decoratedPOI);
        this.weather = determineWeather();
    }

    // 원본 정보 뒤에 날씨 정보를 덧붙여 반환
    @Override
    public String getInformation() {
        return super.getInformation() + " [Weather: " + this.weather + "]";
    }

    // 날씨를 결정하는 내부 로직
    private String determineWeather() {
        // 원본 POI의 위치 정보를 가져옴
        POI originalPOI = POIDecorator.unwrapPOI(this.decoratedPOI);
        double lat = originalPOI.location.lat;
        double lon = originalPOI.location.lon;

        // 위도와 경도 값을 기반으로 seed를 생성하여, 같은 장소는 항상 같은 날씨가 나오도록 구현
        long seed = (long) ((lat + lon) * 1000);
        Random rand = new Random(seed);
        int weatherType = rand.nextInt(3); // 0, 1, 2중 하나의 값 생성

        // 결정된 타입에 따라 날씨 문자열 반환
        switch (weatherType) {
            case 0:
                return "Sunny ☀️";
            case 1:
                return "Cloudy ☁️";
            case 2:
                return "Rainy 🌧️";
            default:
                return "Partly Cloudy 🌥️";
        }
    }
}