package cts.phase3.controller.utils;

import java.util.ArrayList;
import java.util.List;

public class ClusteringUtil {

    private k_means kmeans;
    private k_means kmeans2;

    public ClusteringUtil() {
        this.kmeans = new k_means(2);
//        this.kmeans2 = new k_means(4);
    }

    public void setDataSet(ArrayList<double[]> list) {
        kmeans.setDataSet(list);
    }

    public ArrayList<double[]> center() {
        kmeans.kmeans();
        return kmeans.getCenter();
//        ArrayList<double[]> list = kmeans.getDataSet();
//        double[] d1 = list.get(1);
//        double[] d2 = list.get(2);
//        double[] d3 = list.get(3);
//        double[] d4 = list.get(4);
//        ArrayList<double[]> center = kmeans.getCenter();
//        center.add(d1);
//        center.add(d2);
//        center.add(d3);
//        center.add(d4);
//        kmeans2.setDataSet(center);
//        kmeans2.kmeans();
//        ArrayList<ArrayList<double[]>> cluster = kmeans2.getCluster();
//        ArrayList<double[]> ret = new ArrayList<>();
//
//        for (int i = 0; i < cluster.size(); i++) {
//            ArrayList<double[]> li = cluster.get(i);
//            if (li.contains(d1)) {
//                if (!li.get(0).equals(d1)) ret.add(li.get(0));
//                else ret.add(li.get(1));
//                break;
//            }
//        }
//
//        for (int i = 0; i < cluster.size(); i++) {
//            ArrayList<double[]> li = cluster.get(i);
//            if (li.contains(d2)) {
//                if (!li.get(0).equals(d1)) ret.add(li.get(0));
//                else ret.add(li.get(1));
//                break;
//            }
//        }
//
//        for (int i = 0; i < cluster.size(); i++) {
//            ArrayList<double[]> li = cluster.get(i);
//            if (li.contains(d3)) {
//                if (!li.get(0).equals(d1)) ret.add(li.get(0));
//                else ret.add(li.get(1));
//                break;
//            }
//        }
//
//        for (int i = 0; i < cluster.size(); i++) {
//            ArrayList<double[]> li = cluster.get(i);
//            if (li.contains(d4)) {
//                if (!li.get(0).equals(d1)) ret.add(li.get(0));
//                else ret.add(li.get(1));
//                break;
//            }
//        }
//        return ret;
    }





}
