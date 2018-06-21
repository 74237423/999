package cts.phase3.controller.utils;

import java.util.ArrayList;
import java.util.List;

public class ClusteringUtil {

    private k_means kmeans;

    public ClusteringUtil() {
        this.kmeans = new k_means(4);

    }

    public void setDataSet(ArrayList<double[]> list) {
        kmeans.setDataSet(list);
    }

    public ArrayList<double[]> center() {
        return kmeans.getCenter();
    }

    public void run() {
        this.kmeans.kmeans();
    }
}
