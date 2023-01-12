package emergency.setupData;

import emergency.modelView.CapteurDataLocateViewModel;

import java.util.List;

public class SensorUtils {
    private static final double EARTH_RADIUS = 6371; // Earth's radius in kilometers

    public static int countSensorsInRadius(List<CapteurDataLocateViewModel> sensors, double centerLat, double centerLng, double radius) {
        int count = 0;
        for (CapteurDataLocateViewModel sensor : sensors) {
            double lat = sensor.lat;
            double lng = sensor.lng;
            double distance = distanceInKmBetweenEarthCoordinates(lat, lng, centerLat, centerLng);
            if (distance <= radius) {
                count+=sensor.intCapteur;
            }
        }
        return count;
    }

    private static double distanceInKmBetweenEarthCoordinates(double lat1, double lon1, double lat2, double lon2) {
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return EARTH_RADIUS * c;
    }
}





