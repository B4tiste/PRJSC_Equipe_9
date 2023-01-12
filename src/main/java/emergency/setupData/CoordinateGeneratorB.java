package emergency.setupData;

import java.util.ArrayList;
import java.util.List;

class CoordinateGeneratorB {
    public static final double EARTH_RADIUS_KM = 6371.0; // Earth's radius in kilometers

    public static ArrayList<double[]> createGrid(double lat1, double lng1, double lat2, double lng2, double distance, int N) {
        ArrayList<double[]> coordinates = new ArrayList<double[]>();

        double minLat = Math.min(lat1, lat2);
        double maxLat = Math.max(lat1, lat2);
        double minLng = Math.min(lng1, lng2);
        double maxLng = Math.max(lng1, lng2);

        int latCount = (int) Math.ceil((maxLat - minLat) / (distance / EARTH_RADIUS_KM));
        int lngCount = (int) Math.ceil((maxLng - minLng) / (distance / (EARTH_RADIUS_KM)));

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
    public static double haversineDistance(double lat1, double lng1, double lat2, double lng2) {
        double lat1Rad = Math.toRadians(lat1);
        double lat2Rad = Math.toRadians(lat2);
        double deltaLat = Math.toRadians(lat2 - lat1);
        double deltaLng = Math.toRadians(lng2 - lng1);

        double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2) +
                Math.cos(lat1Rad) * Math.cos(lat2Rad) *
                        Math.sin(deltaLng / 2) * Math.sin(deltaLng / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS_KM * c;
    }

    public static List<double[]> generateGrid(double lat1, double lng1, double lat2, double lng2, double distance) {
        List<double[]> grid = new ArrayList<double[]>();

        // Convert distance from kilometers to degrees
        double distanceInDegrees = distance / EARTH_RADIUS_KM;

        // Find the minimum and maximum latitudes and longitudes
        double minLat = Math.min(lat1, lat2);
        double maxLat = Math.max(lat1, lat2);
        double minLng = Math.min(lng1, lng2);
        double maxLng = Math.max(lng1, lng2);

        // Iterate over the latitudes and longitudes to create the grid
        for (double lat = minLat; lat < maxLat; lat += distanceInDegrees) {
            for (double lng = minLng; lng < maxLng; lng += distanceInDegrees) {
                double[] coordinates = { lat, lng };
                grid.add(coordinates);
            }
        }

        return grid;
    }

    public static List<double[]> getCoordinateGridB(double lat1, double lng1, double lat2, double lng2, int N, double distance) {
        List<double[]> grid = new ArrayList<double[]>();
        double distanceKm = haversineDistance(lat1, lng1, lat2, lng2);
        int numSquares = (int) Math.ceil(distanceKm / distance);
        double latStep = (lat2 - lat1) / numSquares;
        double lngStep = (lng2 - lng1) / numSquares;
        double currentLat = lat1;
        double currentLng = lng1;
        for (int i = 0; i < numSquares; i++) {
            grid.add(new double[]{currentLat, currentLng});
            currentLat += latStep;
            currentLng += lngStep;
        }
        return grid.subList(0, N);
    }
    public static List<double[]> getCoordinateGrid(double lat1, double lng1, double lat2, double lng2, int N, double distance) {
        List<double[]> grid = new ArrayList<double[]>();
        double latDiff = Math.abs(lat2 - lat1);
        double lonDiff = Math.abs(lng2 - lng1);
        int numLat = (int) Math.ceil(latDiff / distance);
        int numLon = (int) Math.ceil(lonDiff / distance);
        int numSquares = numLat * numLon;
        double latStep = latDiff / numLat;
        double lonStep = lonDiff / numLon;
        double currentLat = lat1;
        double currentLon = lng1;
        for (int i = 0; i < numSquares; i++) {
            if (i % numLon == 0 && i != 0) {
                currentLat += latStep;
                currentLon = lng1;
            }
            grid.add(new double[]{currentLat, currentLon});
            currentLon += lonStep;
        }
        return grid.subList(0, N);
    }
    public static List<double[]> generateIntermediatePoints(double[] start, double[] end, int n, double m) {
        List<double[]> intermediatePoints = new ArrayList<>();

        // The distance between the start and end coordinates
        double totalDistance = distance(start, end);

        // The bearing (angle) from the start point to the end point
        double bearing = bearing(start, end);

        // The x and y increments for each intermediate point
        double xIncrement = Math.cos(bearing) * m;
        double yIncrement = Math.sin(bearing) * m;

        // The current point
        double[] current = new double[]{start[0], start[1]};

        // Generate the intermediate points
        for (int i = 0; i < n; i++) {
            // Move the current point by the x and y increments
            current[0] += xIncrement;
            current[1] += yIncrement;

            // Check if the current point is still within the area defined by the start and end coordinates
            if (current[0] >= Math.min(start[0], end[0]) && current[0] <= Math.max(start[0], end[0]) &&
                    current[1] >= Math.min(start[1], end[1]) && current[1] <= Math.max(start[1], end[1]) &&
                    distance(start, current) <= totalDistance) {
                // The current point is valid, so add it to the list of intermediate points
                intermediatePoints.add(new double[]{current[0], current[1]});
            } else {
                // The current point is not valid, so stop generating points
                break;
            }
        }

        return intermediatePoints;
    }

    public static double distance(double[] start, double[] end) {
        // The distance between two coordinates using the Haversine formula
        double lat1 = Math.toRadians(start[0]);
        double lon1 = Math.toRadians(start[1]);
        double lat2 = Math.toRadians(end[0]);
        double lon2 = Math.toRadians(end[1]);

        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;

        double a = Math.pow(Math.sin(dlat / 2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dlon / 2), 2);
        double c = 2 * Math.asin(Math.sqrt(a));

        return 6371 * c;
    }

    public static double bearing(double[] start, double[] end) {
        // The bearing (angle) from the start point to the end point
        double lat1 = Math.toRadians(start[0]);
        double lon1 = Math.toRadians(start[1]);
        double lat2 = Math.toRadians(end[0]);
        double lon2 = Math.toRadians(end[1]);
        double dLon = lon2 - lon1;

        double y = Math.sin(dLon) * Math.cos(lat2);
        double x = Math.cos(lat1) * Math.sin(lat2) - Math.sin(lat1) * Math.cos(lat2) * Math.cos(dLon);

        return Math.atan2(y, x);
    }
}