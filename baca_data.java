/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knn_kmeans;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Bayu
 */

public class baca_data {
    double data_latih[][];
    double data_uji[][];
    double class_dt_latih[];
    double class_dt_uji[];
    ArrayList<Double> dataset;
    double data_set[][];
    double data[][];
    int jumlah_data_uji;
    int jumlah_data_latih;
    
    public void Baca_DataSet() throws FileNotFoundException{
        File file_data = new File("C:\\Users\\Bayu\\Documents\\NetBeansProjects\\knn_kmeans\\Dataset.txt");        
        this.dataset = new ArrayList<>();
        this.data_set = new double[150][5];
        
        Scanner baca_dataset = new Scanner(file_data);
        
        while (baca_dataset.hasNext()){
            double data_set = baca_dataset.nextDouble();
            this.dataset.add(data_set);
        }        
        baca_dataset.close();
        
        //set data
        int count = 0;
        for (int i=0; i < data_set.length; i++){
            for (int j=0; j < data_set[0].length; j++){
                this.data_set[i][j] = dataset.get(count);
                count++;
            }
        }
    }

    public double[][] getData_set() {
        return data_set;
    }

    public void set_fitur_data(int jumlah_fitur){
        this.data = new double[150][jumlah_fitur+1];        
        for (int i=0; i < data.length; i++){
            for (int j=0; j < data[0].length; j++){
                if (j < data[0].length-1){
                    data[i][j] = data_set[i][j];
                } else {
                    data[i][j] = data_set[i][data_set[0].length-1];
                } 
            }
        }
        
    }

    public double[][] getData() {
        return data;
    }
    
    public void K_Fold_Cross_Validation (double data[][], int K, int Fold){
        int titik_data_latih[]  = dapatkan_fold(Fold);
        int titik_data[]        = dapatkan_k(data.length,K);               
        jumlah_data_latih       = dapat_length_dt_latih(titik_data, titik_data_latih);
        jumlah_data_uji         = data.length-jumlah_data_latih;
        int titik_dt_uji[]      = dapatkan_titik_data(K, Fold);
        double data_label[][]   = isi_titik_data(data,titik_data);
        this.data_latih         = get_data_latih(jumlah_data_latih, titik_data_latih, data_label);
        this.class_dt_latih     = get_class_data_latih(data_latih);
        this.data_uji           = get_data_uji(jumlah_data_uji, titik_dt_uji, data_label);
        this.class_dt_uji       = get_class_data_uji(data_uji);
    }

    public double[][] getData_latih() {
        return data_latih;
    }

    public double[] getClass_dt_latih() {
        return class_dt_latih;
    }

    public double[][] getData_uji() {
        return data_uji;
    }

    public double[] getClass_dt_uji() {
        return class_dt_uji;
    }
    
    private int[] dapatkan_fold(int Fold) {
       int fold[] = new int [Fold];
        for (int i=0; i < Fold; i++){
            fold[i] = i;
        } 
        return fold;
    }

    private int[] dapatkan_k(int length, int K) {
        int titik_k [] = new int[length];
        int a = 0;
        for (int i=0; i < titik_k.length; i++){            
            if (a < K){
                titik_k[i] = a;
                a++;
            } else {
                a=0;
                titik_k[i] = a;
                a++;
            }      
        }
        return titik_k;        
    }

    private int dapat_length_dt_latih(int[] titik_k, int[] fold) {
        int panjang = 0;        
        for (int i=0; i < fold.length; i++){
            for (int j=0; j < titik_k.length; j++){
                if (fold[i] == titik_k[j]){
                    panjang ++;
                } 
            }
        } 
        return panjang;
    }

    private int[] dapatkan_titik_data(int K, int Fold) {
        int titik[] = new int[K-Fold];
        int X = Fold;
        for (int i=0; i < titik.length; i++){
            if (Fold < K){
               titik[i] = Fold;
               Fold++;
            } else {
                Fold = X;
                titik[i] = Fold;
                Fold++;
            }
        } 
        return titik;
    }

    private double[][] isi_titik_data(double[][] data, int[] titik_data) {
        double dt[][] = new double[data.length][data[0].length+1];
        
        for (int i=0; i < dt.length; i++){
            for (int j=0; j < dt[0].length; j++){
                if ( j < dt[0].length-1){
                    dt[i][j] = data[i][j];
                } else {
                    dt[i][j] = titik_data[i];
                }
            }
        }
        
        return dt;
    }

    private double[][] get_data_latih(int jumlah_data_latih, int[] titik_data_latih, double[][] data_label) {
       double data_latih[][] = new double [jumlah_data_latih][data_label[0].length-1];
       
       int count = 0;
       for (int i=0; i < titik_data_latih.length; i++){
           for (int j=0; j < data_label.length; j++){
               if (titik_data_latih[i] == data_label[j][data_label[0].length-1]){
                   for (int l=0; l < data_latih[0].length; l++){
                       data_latih[count][l] = data_label[j][l];
                   }
                   count++;
               }
           }
       }
       
       return data_latih;
    }

    private double[] get_class_data_latih(double[][] data_latih) {
       double get_class [] = new double[data_latih.length];
       
       for (int i=0; i < get_class.length; i++){
           get_class[i] = data_latih[i][data_latih[0].length-1];
       }
       return get_class;
    }

    private double[][] get_data_uji(int jumlah_data_uji, int[] titik_dt_uji, double[][] data_label) {
        double data_uji[][] = new double[jumlah_data_uji][data_label[0].length-1];
        
       int count = 0;
       for (int i=0; i < titik_dt_uji.length; i++){
           for (int j=0; j < data_label.length; j++){
               if (titik_dt_uji[i] == data_label[j][data_label[0].length-1]){
                   for (int l=0; l < data_latih[0].length; l++){
                      data_uji[count][l] = data_label[j][l];
                   }
                   count++;
               }
           }
       }
       
       return data_uji;
    }

    private double[] get_class_data_uji(double[][] data_uji) {
       double get_class [] = new double[data_uji.length];
       
       for (int i=0; i < get_class.length; i++){
           get_class[i] = data_uji[i][data_uji[0].length-1];
       }
       return get_class;  
    }
}
      

