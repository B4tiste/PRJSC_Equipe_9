package emergency.setupData;

import org.apache.commons.math3.ml.clustering.Clusterable;

public class SensorPoint implements Clusterable {

    private final double[] point;
    private final IntensityWeightedEuclideanDistance distance;

    public SensorPoint(double[] point, double weight) {
        this.point = point.clone();
        this.distance = new IntensityWeightedEuclideanDistance(weight);
    }

    @Override
    public double[] getPoint() {
        return point;
    }

    public double distanceFrom(Clusterable p) {
        return distance.compute(getPoint(), p.getPoint());
    }
}


