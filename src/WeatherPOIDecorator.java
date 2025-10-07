import java.util.Random;

// Your Code!
// POI의 위치에 따른 날씨 정보를 "장식"하는 데코레이터
public class WeatherPOIDecorator extends POIDecorator {

    private String weather;

    public WeatherPOIDecorator(IPOI decoratedPOI) {
        super(decoratedPOI);
        // 데코레이터가 생성될 때 날씨를 결정한다.
        this.weather = determineWeather();
    }

    @Override
    public String getInformation() {
        // 기존 정보에 날씨 정보를 덧붙인다.
        return super.getInformation() + " [Weather: " + this.weather + "]";
    }

    // 위치 좌표를 기반으로 날씨를 결정하는 private 헬퍼 메서드
    private String determineWeather() {
        // 장식을 벗겨내고 원본 POI의 위치 정보를 가져온다.

        // 위도와 경도를 더한 값의 마지막 자리를 기준으로 날씨를 결정 (단순하지만 그럴듯한 방식)
        Random rand = new Random();
        int weatherType = rand.nextInt(3); // 0, 1, 2 중 하나의 값이 나옴

        switch (weatherType) {
            case 0:
                return "Sunny";
            case 1:
                return "Cloudy";
            case 2:
                return "Rainy";
            default:
                return "none";
        }
    }
}