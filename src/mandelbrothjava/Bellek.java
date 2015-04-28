/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mandelbrothjava;

/**
 *
 * @author muhammed
 */
public class Bellek {
    public  final int[][] sonuc;
    
    public Bellek( int genislik,int yukseklik ){
        sonuc = new int[genislik][yukseklik];
    }

    public synchronized void ekle1(int i,int j,int g){
        sonuc[i][j]=g;
    }
    
    
    public synchronized void ekle(int i,int j,int[][] uretilen){
        for(int k= 0;k<uretilen.length;k++){
            for (int l = 0; l < uretilen[0].length; l++) {
                sonuc[i+k][j+l]=uretilen[i][j];
            }
        }
    }
    
}
