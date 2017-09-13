/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knn_kmeans;

import java.io.FileNotFoundException;

/**
 *
 * @author Bayu
 */
public class knn_main_class {
    
    public static void main(String[] args) throws FileNotFoundException {
        baca_data data = new baca_data();
        K_NN knn       = new K_NN(); 
        
        data.Baca_DataSet();
        double data_set [][] = data.getData_set();
        
        for (int i=0; i < data_set.length; i++){
            for (int j=0; j < data_set[0].length; j++){
                System.out.print(data_set[i][j]+"   ");
            }
            System.out.println();
        }
        
        System.out.println("=Data=");
        data.set_fitur_data(4);
        double dt[][] = data.getData();
        
        for (int i=0; i < dt.length; i++){
            for (int j=0; j < dt[0].length; j++){
                System.out.print(dt[i][j]+" ");
            }
            System.out.println();
        }
        
        System.out.println("== K-Fold Cross Validation ==");
        data.K_Fold_Cross_Validation(dt, 10, 9);
        double data_latih[][]   = data.getData_latih();
        double data_uji[][]     = data.getData_uji();
        double kelas_dt_latih[] = data.getClass_dt_latih();
        
        System.out.println("== Data Latih ==");
         for (int i=0; i < data_latih.length; i++){
            for (int j=0; j < data_latih[0].length; j++){
                System.out.print(data_latih[i][j] + "  ");
            }
            System.out.println();
        }
        
        System.out.println();
        System.out.println("== Data Uji ==");
        for (int i=0; i < data_uji.length; i++){
            for (int j=0; j < data_uji[0].length; j++){
                System.out.print(data_uji[i][j] + "  ");
            }
            System.out.println();
        }
        
        System.out.println();
        knn.Hitung_Euclidean_Distance(data_latih, data_uji);
        double distance[][] = knn.getEuclidean_Distance();
        System.out.println();
        knn.Set_Class_Dt_Uji(distance, kelas_dt_latih);
        knn.Urut_Jarak();
        System.out.println();
        distance = knn.getEuclidean_Distance();
        double idx[][] = knn.getClass_dt();
        
         for (int i=0; i <  distance .length; i++){
            for (int j=0; j <  distance [0].length; j++){
                System.out.print(distance [i][j] + "  " + idx[i][j]+ "  ");
            }
            System.out.println();
        }
        
        System.out.println();
        knn.Ambil_Sebanyak_K(2, distance, idx);
        double k_nn[][]     = knn.getK_NN();
        double id_knn[][]   = knn.getClass_knn();
        
        System.out.println();
        for (int i=0; i <  k_nn.length; i++){
            for (int j=0; j <  k_nn[0].length; j++){
                System.out.print(k_nn[i][j] + "  " + id_knn[i][j]+ "  ");
            }
            System.out.println();
        }
        
        knn.Klasifikasi(id_knn);
        int klasifikasi[][] = knn.getKlasifikasi();
        
        System.out.println();
        for (int i=0; i <  klasifikasi.length; i++){
            for (int j=0; j <klasifikasi[0].length; j++){
                System.out.print(klasifikasi[i][j] + "  ");
            }
            System.out.println();
        }
        
        System.out.println();
        knn.Indeks_Klasifikasi(klasifikasi);
        int id_max [] = knn.getId_max();
        
        for (int i=0; i <  id_max.length; i++){
            System.out.println(id_max[i]);
            
        }
        
        System.out.println();
        knn.Hasil_Klasifikasi(id_max);
        String Kelas[] = knn.getLabel();
        
        for (int i=0; i < Kelas.length; i++){
            System.out.println(Kelas[i]);
        }
        
        System.out.println();
        double id_mx[] = knn.getId_max_dobel();
        double id_kelas_dt_uji[] = data.getClass_dt_uji();
        knn.Akurasi(id_mx, id_kelas_dt_uji);
        System.out.println("Data Benar : "+knn.getJumlah_data_benar()+"  "+"Data Salah : "+knn.getJumlah_data_salah());
    }
    
}
