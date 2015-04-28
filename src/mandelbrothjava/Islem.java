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
public class Islem implements Runnable{
    private final int basx;
    private final int basy;
    private final int bitx,tg,ty;
    private final int bity,MAX_ITER;
    private double zx, zy, cX, cY, tmp;
    public final Bellek bellek;
    public Islem(int basxx,int basyy,int bitxx,int bityy,int iter,int topg,int topy,Bellek bel){
        basx = basxx;
        basy = basyy;
        bitx=bitxx;
        bity=bityy;MAX_ITER=iter;
        tg=topg;ty=topy;
        bellek=bel;
    }

    public void run(){
        for (int i = basx; i < bitx; i++) {
            for (int j = basy; j < bity; j++) {
                zx = zy = 0;
                cX = (i/(float)tg)*1.625 - 1 ;
                cY = (j/(float)ty)*1.25 - 0.625 ;
                int iter = MAX_ITER;
                while (zx * zx + zy * zy < 4 && iter > 0) {
                    tmp = zx * zx - zy * zy + cX;
                    zy = 2.0 * zx * zy + cY;
                    zx = tmp;
                    iter--;
                }
                bellek.ekle1(i, j, iter);
            }
        }
        //System.err.println("bitti"+basx+ " "+basy+" "+bitx+ " "+bity+" ");
        //bellek.ekle(basx, basy, tampon);
    }
}
