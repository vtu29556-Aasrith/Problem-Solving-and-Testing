import java.util.HashMap;
import java.util.Map;

class UndergroundSystem {

    // Class to store check-in details for a customer
    private static class CheckInInfo {
        String stationName;
        int checkInTime;

        CheckInInfo(String stationName, int checkInTime) {
            this.stationName = stationName;
            this.checkInTime = checkInTime;
        }
    }

    // Class to aggregate total travel time and trip count between two stations
    private static class RouteData {
        double totalTime = 0;
        int tripCount = 0;

        void addTrip(int travelTime) {
            this.totalTime += travelTime;
            this.tripCount++;
        }

        double getAverage() {
            return totalTime / tripCount;
        }
    }

    // Maps passenger ID to their active check-in information
    private final Map<Integer, CheckInInfo> checkInMap;
    // Maps station route key ("startStation->endStation") to aggregated route data
    private final Map<String, RouteData> routeDataMap;

    public UndergroundSystem() {
        checkInMap = new HashMap<>();
        routeDataMap = new HashMap<>();
    }
    
    public void checkIn(int id, String stationName, int t) {
        checkInMap.put(id, new CheckInInfo(stationName, t));
    }
    
    public void checkOut(int id, String stationName, int t) {
        CheckInInfo checkInInfo = checkInMap.remove(id);
        
        String routeKey = checkInInfo.stationName + "->" + stationName;
        int travelTime = t - checkInInfo.checkInTime;

        routeDataMap.computeIfAbsent(routeKey, k -> new RouteData()).addTrip(travelTime);
    }
    
    public double getAverageTime(String startStation, String endStation) {
        String routeKey = startStation + "->" + endStation;
        return routeDataMap.get(routeKey).getAverage();
    }
}