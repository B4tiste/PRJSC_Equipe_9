package emergency.setupData;

import java.util.ArrayList;
import java.util.List;

public class CoordinateGenerator {
    public static ArrayList<double[]> createGrid(double lat1, double lng1, double lat2, double lng2, double distance, int N) {
        ArrayList<double[]> coordinates = new ArrayList<double[]>();

        double minLat = Math.min(lat1, lat2);
        double maxLat = Math.max(lat1, lat2);
        double minLng = Math.min(lng1, lng2);
        double maxLng = Math.max(lng1, lng2);

        int latCount = (int) Math.ceil((maxLat - minLat) / (distance / 110.574));
        int lngCount = (int) Math.ceil((maxLng - minLng) / (distance / (111.320 * Math.cos(minLat))));

        double latStep = (maxLat - minLat) / latCount;
        double lngStep = (maxLng - minLng) / lngCount;

        int count = 0;
        for (double lat = minLat; lat < maxLat; lat += latStep) {
            for (double lng = minLng; lng < maxLng; lng += lngStep) {
                coordinates.add(new double[]{lat, lng});
                count++;
                if (count >= N) {
                    break;
                }
            }
            if (count >= N) {
                break;
            }
        }
        return coordinates;
    }
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