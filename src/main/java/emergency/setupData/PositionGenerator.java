package emergency.setupData;

public class PositionGenerator {
    public static double[] generatePosition(double[] pos1, double[] pos2, double seed) {
        double[] newPos = new double[2];
        double latitudeDiff = pos2[0] - pos1[0];
        double longitudeDiff = pos2[1] - pos1[1];
        double angle = seed;
        double distance = (Math.sin(seed) + 1) / 2.0;

        newPos[0] = pos1[0] + latitudeDiff * Math.cos(angle*1.34) * distance;
        newPos[1] = pos1[1] + longitudeDiff * Math.sin(angle) * distance;

        return newPos;
    }
}