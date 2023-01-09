package emergency.setupData;

import java.util.ArrayList;
import java.util.List;

public class CoordinateGenerator {
    public static List<double[]> generateCoordinates(double[] pos1, double[] pos2, int nbSensors, double radius) {
        //attention distance en km
        double distance = calculateDistance(pos1, pos2);
        int numCoordinates = (int) (distance / radius);
        double latDifference = pos2[0] - pos1[0];
        double lngDifference = pos2[1] - pos1[1];

        double latIncrement = latDifference / numCoordinates;
        double lngIncrement = lngDifference / numCoordinates;
        List<double[]> coordinates = new ArrayList<>();
        for (int i = 0; i < numCoordinates; i++) {
            double lat = pos1[0] + (latIncrement * i);
            double lng = pos1[1] + (lngIncrement * i);
            coordinates.add(new double[]{lat, lng});
        }
        return coordinates;
    }

    public static double calculateDistance(double[] pos1, double[] pos2) {
        double lat1 = Math.toRadians(pos1[0]);
        double lng1 = Math.toRadians(pos1[1]);
        double lat2 = Math.toRadians(pos2[0]);
        double lng2 = Math.toRadians(pos2[1]);
        double earthRadius = 6371;
        double distance = Math.acos(Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lng2 - lng1)) * earthRadius;

        return distance;
    }
}