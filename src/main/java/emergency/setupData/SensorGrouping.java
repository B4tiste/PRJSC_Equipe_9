package emergency.setupData;

import emergency.modelView.CapteurDataLocateViewModel;
import org.apache.commons.math3.ml.clustering.CentroidCluster;
import org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SensorGrouping {

    public static List<Coordinate> clusterSensors(List<CapteurDataLocateViewModel> sensors, int numClusters, double weight) {
        List<SensorPoint> sensorPoints = new ArrayList<>();
        for (CapteurDataLocateViewModel sensor : sensors) {
            double[] point = new double[] {sensor.lat, sensor.lng, sensor.intCapteur};
            sensorPoints.add(new SensorPoint(point, weight));
        }

        KMeansPlusPlusClusterer<SensorPoint> clusterer = new KMeansPlusPlusClusterer<>(numClusters);
        List<CentroidCluster<SensorPoint>> clusters = clusterer.cluster(sensorPoints);

        List<Coordinate> clusterCentroids = new ArrayList<>();
        for (CentroidCluster<SensorPoint> cluster : clusters) {
            double[] center = cluster.getCenter().getPoint();
            double[] latlng = new double[] {center[0], center[1]};
            Coordinate coo = new Coordinate(center[0], center[1]);
            clusterCentroids.add(coo);
        }

        return clusterCentroids;
    }

    /*public static List<double[]> clusterSensors(List<CapteurDataLocateViewModel> sensors, int numClusters, double weight) {
        List<double[]> sensorPositions = new ArrayList<>();
        for (CapteurDataLocateViewModel sensor : sensors) {
            double[] position = new double[] {sensor.lat, sensor.lng, sensor.intCapteur};
            sensorPositions.add(position);
        }

        KMeansPlusPlusClusterer<double[]> clusterer = new KMeansPlusPlusClusterer<double[]>(numClusters, 100, new IntensityWeightedEuclideanDistance(weight));
        List<CentroidCluster<double[]>> clusters = clusterer.cluster(sensorPositions);

        List<double[]> clusterCentroids = new ArrayList<>();
        for (CentroidCluster<double[]> cluster : clusters) {
            double[] center = cluster.getCenter();
            double[] latlng = new double[] {center[0], center[1]};
            clusterCentroids.add(latlng);
        }

        return clusterCentroids;
    }*/
    public static List<Coordinate> groupSensors(List<CapteurDataLocateViewModel> sensors) {
        Map<Integer, List<Coordinate>> intensityGroups = new HashMap<>();
        for (CapteurDataLocateViewModel sensor : sensors) {
            if (!intensityGroups.containsKey(sensor.idCapteur)) {
                intensityGroups.put(sensor.idCapteur.intValue(), new ArrayList<>());
            }
            intensityGroups.get(sensor.idCapteur.intValue()).add(new Coordinate(sensor.lat, sensor.lng));
        }
        List<Coordinate> groupCoordinates = new ArrayList<>();
        for (Map.Entry<Integer, List<Coordinate>> entry : intensityGroups.entrySet()) {
            double sumLat = 0, sumLng = 0;
            for (Coordinate coordinate : entry.getValue()) {
                sumLat += coordinate.lat;
                sumLng += coordinate.lng;
            }
            groupCoordinates.add(new Coordinate(sumLat / entry.getValue().size(), sumLng / entry.getValue().size()));
        }
        return groupCoordinates;
    }
}