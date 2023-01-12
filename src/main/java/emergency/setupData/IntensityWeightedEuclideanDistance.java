package emergency.setupData;


import org.apache.commons.math3.ml.distance.DistanceMeasure;

public class IntensityWeightedEuclideanDistance implements DistanceMeasure {
    private final double weight;

    public IntensityWeightedEuclideanDistance(double weight) {
        this.weight = weight;
    }

    @Override
    public double compute(double[] a, double[] b) {
        double latDistance = a[0] - b[0];
        double lngDistance = a[1] - b[1];
        double intDistance = weight * (a[2] - b[2]);
        return Math.sqrt(latDistance * latDistance + lngDistance * lngDistance + intDistance * intDistance);
    }
}