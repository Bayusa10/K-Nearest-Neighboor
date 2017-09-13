/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knn_kmeans;

/**
 *
 * @author Bayu
 */
public class K_NN {
    double Euclidean_Distance[][];
    double K_NN[][];
    double class_knn[][];
    double class_dt[][];
    int klasifikasi[][];
    String label[];
    int id_max[];
    int jumlah_data_benar;
    int jumlah_data_salah;
    double id_max_dobel[];
    public K_NN() {
    }
    
    public void Hitung_Euclidean_Distance(double data_latih[][], double data_uji[][]){
        double data_latih_transpose[][] = transpose_data_latih(data_latih);
        this.Euclidean_Distance       = hitung_jarak(data_latih_transpose,data_uji);
        
        for (int i=0;i < Euclidean_Distance .length; i++){
            for (int j=0; j < Euclidean_Distance [0].length; j++){
                System.out.print(Euclidean_Distance [i][j]+"  ");
            }
            System.out.println();
        }
    }
    
    
    public double[][] getEuclidean_Distance() {
        return Euclidean_Distance;
    }

    public void Set_Class_Dt_Uji(double euclidean_distance[][], double kelas_dt_latih[]){
        this.class_dt = new double[euclidean_distance.length][euclidean_distance[0].length];
        
        for (int i=0; i < class_dt.length; i++){
            for (int j=0; j < class_dt[0].length; j++){
                class_dt[i][j] = kelas_dt_latih[j];
                System.out.print(class_dt[i][j] + " ");
            }
            System.out.println();
        }
        
    }

    public double[][] getClass_dt() {
        return class_dt;
    }
 
    public void Urut_Jarak (){
         for (int i=0; i < this.Euclidean_Distance.length; i++){
            for (int j=0; j < this.Euclidean_Distance[0].length; j++){
                for (int k=j+1; k < this.Euclidean_Distance[0].length; k++){
                    if (this.Euclidean_Distance[i][j] > this.Euclidean_Distance[i][k]){
                        double temp = this.Euclidean_Distance[i][j];
                        double temp_idx = this.class_dt[i][j];
                       
                        this.Euclidean_Distance[i][j] = this.Euclidean_Distance[i][k];
                        this.class_dt[i][j] = this.class_dt[i][k];                        
                        
                        this.Euclidean_Distance[i][k] = temp;
                        this.class_dt[i][k] = temp_idx;
                    }
                }
            }
        }
    }
    
    public void Ambil_Sebanyak_K(int K, double dt_jarak_terurut[][], double indeks_terurut[][]){
       this.K_NN        = new double [dt_jarak_terurut.length][K]; 
       this.class_knn   = new double [dt_jarak_terurut.length][K];
       
       for (int i=0; i < this.K_NN.length; i++){
           for (int j=0; j < this.K_NN[0].length; j++){
               this.K_NN [i][j]     = dt_jarak_terurut[i][j];
               this.class_knn[i][j] = indeks_terurut[i][j];  
           }
       }
    }

    public double[][] getK_NN() {
        return K_NN;
    }

    public double[][] getClass_knn() {
        return class_knn;
    }
    
    public void Klasifikasi(double Kelas_Terurut[][]){
        this.klasifikasi = new int[Kelas_Terurut.length][3];
        
        for (int i=0; i < Kelas_Terurut.length; i++){
            for (int j=0; j < Kelas_Terurut[0].length; j++){
                if (Kelas_Terurut[i][j] == 1.0){
                    klasifikasi[i][0] +=1;
                } else {
                    klasifikasi[i][0] += 0;
                }
                
                if (Kelas_Terurut[i][j] == 2.0){
                    klasifikasi[i][1] +=1;
                }else {
                    klasifikasi[i][1] += 0;
                }
                
                if (Kelas_Terurut[i][j] == 3.0){
                    klasifikasi[i][2] +=1;
                }else {
                    klasifikasi[i][2] += 0;
                }                
            }
        }
        
    }

    public void Indeks_Klasifikasi(int klasifikasi[][]){
        id_max    = new int[klasifikasi.length];
        
        for (int i=0; i < klasifikasi.length; i++){
            int max = klasifikasi[i][0];
            for (int j=1; j < klasifikasi[0].length; j++){
                if (klasifikasi[i][j] > max){
                    max = klasifikasi[i][j];
                    id_max[i] = j+1;
                } 
            }
            if (id_max[i] == 0){
                id_max[i] = 1;
            }
        }
               
    }

    public double[] getId_max_dobel() {
       this.id_max_dobel = new double[this.id_max.length];   
       for (int i=0; i < id_max_dobel.length; i++){
           id_max_dobel[i] = (double)this.id_max[i];
       }
       return id_max_dobel;
    }
    
    

    public int[] getId_max() {
        return id_max;
    }
     
    public int[][] getKlasifikasi() {
        return klasifikasi;
    }
    
    public void Hasil_Klasifikasi(int id_klasifikasi[]){
        this.label              = new String[id_klasifikasi.length];
        String Label_Kelas []   = {"Iris-Setosa","Iris-Versicolor","Iris-Virginica"};
        
        for (int i=0; i < label.length; i++){
              this.label [i] = Label_Kelas[id_klasifikasi[i]-1]; 
        }      
    }

    public String[] getLabel() {
        return label;
    }
    
    public void Akurasi(double id_klasifikasi[], double label_kelas[]){
        this.jumlah_data_benar = 0;
        this.jumlah_data_salah = 0;
        
        for (int i=0; i < id_klasifikasi.length; i++){
            if (id_klasifikasi[i] == label_kelas[i]){
                this.jumlah_data_benar += 1;
            } else {
                this.jumlah_data_salah +=1;
            }
        }
    }

    public int getJumlah_data_benar() {
        return jumlah_data_benar;
    }

    public int getJumlah_data_salah() {
        return jumlah_data_salah;
    }
    
    
    
    private double[][] transpose_data_latih(double[][] data_latih) {
        double dt[][] = new double[data_latih[0].length-1][data_latih.length];
        
        for (int i=0;i < dt.length; i++){
            for (int j=0; j < dt[0].length; j++){
                dt[i][j] = data_latih[j][i];
            }
        }
        
        return dt;
    }

    private double[][] hitung_jarak(double[][] data_latih_transpose, double[][] data_uji) {
        double euclidean [][] = new double[data_uji.length][data_latih_transpose[0].length];
        
        for (int i=0; i < data_uji.length; i++){
            for (int j=0; j < data_latih_transpose[0].length; j++){
                double tot = 0.0;
                for (int k=0; k < data_latih_transpose.length; k++){
                    tot += (Math.pow(data_uji[i][k]-data_latih_transpose[k][j],2));
                }
                euclidean[i][j] = Math.sqrt(tot);
            }
        }
        return euclidean;
    }
 
}
