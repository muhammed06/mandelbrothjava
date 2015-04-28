/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mandelbrothjava;

import java.awt.Color;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

/**
 *
 * @author muhammed
 */
public class Mandelbrothjava extends JFrame{

    /**
     * @param args the command line arguments
     */
    private  BufferedImage I;
    private int bolx=4,boly=2;
    private int resimx=2000,resimy=2000;
    public Mandelbrothjava() throws HeadlessException {
        super("Mandelbrot Set");
        setBounds(100, 100, resimx, resimy);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        I = new BufferedImage(getWidth(), getHeight(), 1);
        Bellek bellek = new Bellek(resimx,resimy);
        Islem islemler[];
        islemler = new Islem[bolx*boly];
        ExecutorService executor = Executors.newCachedThreadPool();
        int a=0;
        for (int i = 0; i < bolx; i++) {
            for (int j = 0; j < boly; j++) {
                islemler[a] = new Islem(resimx/bolx*i,resimy/boly*j,resimx/bolx*(i+1),resimy/boly*(j+1),250,resimx,resimy,bellek);
                executor.execute(islemler[a]);
                a++;
            }
        }
        executor.shutdown();
        
        try{
            int p=0;
            while(!executor.awaitTermination(1, TimeUnit.SECONDS)){
                   System.err.println("-|-"+ ++p);
            }
        }catch(InterruptedException ex){
            Logger.getLogger(Mandelbrothjava.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for (int i = 0; i < resimx; i++) {
            for (int j = 0; j < resimy; j++) {
                I.setRGB(i, j, bellek.sonuc[i][j]|( bellek.sonuc[i][j] << 12));  
            }
        }
        try{
            ImageIO.write(I, "bmp",new File("D:/ozan.bmp"));
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(I, 0, 0, this);
    }
    
    public static void main(String[] args) throws InterruptedException {
            new Mandelbrothjava().setVisible(true);
    }
    
}
