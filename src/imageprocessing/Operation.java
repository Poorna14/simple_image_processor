/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imageprocessing;

import java.lang.Math.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.image.BufferedImage;
import static java.awt.image.BufferedImage.TYPE_INT_ARGB;
import java.awt.image.Raster;
import static java.lang.Math.ceil;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.lang.Math.*;
import static java.lang.Math.abs;
import java.util.Arrays;

/**
 *
 * @author Poorna
 */
public class Operation {

    //rotating image counter clockwise
    public static void counter_rotate(JLabel label) {
        Icon icon = label.getIcon();
        BufferedImage image = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics g = image.createGraphics();
        icon.paintIcon(null, g, 0, 0);
        g.dispose();
        int length = image.getWidth();
        int height = image.getHeight();
        BufferedImage nImage = new BufferedImage(height, length, image.getType());

        for (int x = 0; x < length; x++) {
            for (int y = 0; y < height; y++) {
                nImage.setRGB(y, length - x - 1, image.getRGB(x, y));
            }
        }
        ImageIcon nIcon = new ImageIcon(nImage);
        label.setIcon(nIcon);

    }

    //rotating image clockwise
    public static void clockwise_rotate(JLabel label) {
        Icon icon = label.getIcon();
        BufferedImage image = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics g = image.createGraphics();
        icon.paintIcon(null, g, 0, 0);
        g.dispose();
        int length = image.getWidth();
        int height = image.getHeight();
        BufferedImage nImage = new BufferedImage(height, length, image.getType());

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < length; x++) {
                nImage.setRGB(height - y - 1, x, image.getRGB(x, y));
            }
        }

        ImageIcon nIcon = new ImageIcon(nImage);
        label.setIcon(nIcon);
    }

    //grayscaling image
    public static void grayscale(JLabel label) {
        Icon icon = label.getIcon();
        BufferedImage image = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics g = image.createGraphics();
        icon.paintIcon(null, g, 0, 0);
        g.dispose();
        int length = image.getWidth();
        int height = image.getHeight();

        for (int x = 0; x < length; x++) {
            for (int y = 0; y < height; y++) {
                int rgb = image.getRGB(x, y);
                int A = (rgb >> 24) & 0xFF;
                int R = (rgb >> 16) & 0xFF;
                int G = (rgb >> 8) & 0xFF;
                int B = (rgb & 0xFF);
                int gray = (R + G + B) / 3;
                rgb = (A << 24) | (gray << 16) | (gray << 8) | gray;
                image.setRGB(x, y, rgb);
            }
        }
        ImageIcon nIcon = new ImageIcon(image);
        label.setIcon(nIcon);
    }

    //maximize using nearest neighbour method
    public static void resize_max(JLabel label) {
        Icon icon = label.getIcon();
        BufferedImage image = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics g = image.createGraphics();
        icon.paintIcon(null, g, 0, 0);
        g.dispose();
        int length = image.getWidth();
        int height = image.getHeight();
        BufferedImage nImage = new BufferedImage(length * 2, height * 2, image.getType());
        int counter1 = 0;
        int counter2 = 0;
        for (int x = 0; x < length; x++) {
            for (int y = 0; y < height; y++) {
                nImage.setRGB(x + counter1, y + counter2, image.getRGB(x, y));
                nImage.setRGB(x + counter1, y + 1 + counter2, image.getRGB(x, y));
                nImage.setRGB(x + 1 + counter1, y + counter2, image.getRGB(x, y));
                nImage.setRGB(x + 1 + counter1, y + 1 + counter2, image.getRGB(x, y));
                counter2++;
            }
            counter2 = 0;
            counter1++;
        }
        ImageIcon nIcon = new ImageIcon(nImage);
        label.setIcon(nIcon);
    }

    //minimize using nearest neighbour method
    public static void resize_min(JLabel label) {
        Icon icon = label.getIcon();
        BufferedImage image = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics g = image.createGraphics();
        icon.paintIcon(null, g, 0, 0);
        g.dispose();
        int length = image.getWidth();
        int height = image.getHeight();
        BufferedImage nImage = new BufferedImage((int) ceil(length / 2), (int) ceil(height / 2), image.getType());
        int counter1 = 0;
        int counter2 = 0;
        for (int x = 0; x < (int) ceil(length / 2); x++) {
            for (int y = 0; y < (int) ceil(height / 2); y++) {
                nImage.setRGB(x, y, image.getRGB(counter1, counter2));
                counter2 = counter2 + 2;
            }
            counter2 = 0;
            counter1 = counter1 + 2;
        }
        ImageIcon nIcon = new ImageIcon(nImage);
        label.setIcon(nIcon);
    }

    public static void increase_brightness(JLabel label) {
        Icon icon = label.getIcon();
        BufferedImage image = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics g = image.createGraphics();
        icon.paintIcon(null, g, 0, 0);
        g.dispose();
        int length = image.getWidth();
        int height = image.getHeight();
        for (int x = 0; x < length; x++) {
            for (int y = 0; y < height; y++) {
                Color color = new Color(image.getRGB(x, y));

                int R, G, B;

                R = checkColorRange(color.getRed() + 10);
                G = checkColorRange(color.getGreen() + 10);
                B = checkColorRange(color.getBlue() + 10);

                color = new Color(R, G, B);
                image.setRGB(x, y, color.getRGB());

            }
        }
        ImageIcon nIcon = new ImageIcon(image);
        label.setIcon(nIcon);
    }

    public static void decrease_brightness(JLabel label) {
        Icon icon = label.getIcon();
        BufferedImage image = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics g = image.createGraphics();
        icon.paintIcon(null, g, 0, 0);
        g.dispose();
        int length = image.getWidth();
        int height = image.getHeight();
        for (int x = 0; x < length; x++) {
            for (int y = 0; y < height; y++) {
                Color color = new Color(image.getRGB(x, y));

                int R, G, B;

                R = checkColorRange(color.getRed() - 10);
                G = checkColorRange(color.getGreen() - 10);
                B = checkColorRange(color.getBlue() - 10);

                color = new Color(R, G, B);
                image.setRGB(x, y, color.getRGB());

            }
        }
        ImageIcon nIcon = new ImageIcon(image);
        label.setIcon(nIcon);
    }

    public static void increase_contrast(JLabel label) {
        Icon icon = label.getIcon();
        BufferedImage image = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics g = image.createGraphics();
        icon.paintIcon(null, g, 0, 0);
        g.dispose();
        int length = image.getWidth();
        int height = image.getHeight();
        int min = image.getRGB(0, 0);
        int max = image.getRGB(0, 0);
        for (int x = 0; x < length; x++) {
            for (int y = 0; y < height; y++) {
                int rgb = image.getRGB(x, y);
                if (min > rgb) {
                    min = rgb;
                }
                if (max < rgb) {
                    max = rgb;
                }
            }
        }
        int avg = (int) (min + max) / 2;
        for (int x = 0; x < length; x++) {
            for (int y = 0; y < height; y++) {
                if (image.getRGB(x, y) < avg) {
                    Color color = new Color(image.getRGB(x, y));
                    int R, G, B;
                    R = checkColorRange(color.getRed() - 10);
                    G = checkColorRange(color.getGreen() - 10);
                    B = checkColorRange(color.getBlue() - 10);
                    color = new Color(R, G, B);
                    image.setRGB(x, y, color.getRGB());
                }
                if (image.getRGB(x, y) > avg) {
                    Color color = new Color(image.getRGB(x, y));
                    int R, G, B;
                    R = checkColorRange(color.getRed() + 10);
                    G = checkColorRange(color.getGreen() + 10);
                    B = checkColorRange(color.getBlue() + 10);
                    color = new Color(R, G, B);
                    image.setRGB(x, y, color.getRGB());
                }
            }
        }
        ImageIcon nIcon = new ImageIcon(image);
        label.setIcon(nIcon);
    }

    public static void decrease_contrast(JLabel label) {
        Icon icon = label.getIcon();
        BufferedImage image = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics g = image.createGraphics();
        icon.paintIcon(null, g, 0, 0);
        g.dispose();
        int length = image.getWidth();
        int height = image.getHeight();
        int min = image.getRGB(0, 0);
        int max = image.getRGB(0, 0);
        for (int x = 0; x < length; x++) {
            for (int y = 0; y < height; y++) {
                int rgb = image.getRGB(x, y);
                if (min > rgb) {
                    min = rgb;
                }
                if (max < rgb) {
                    max = rgb;
                }
            }
        }
        int avg = (int) (min + max) / 2;
        for (int x = 0; x < length; x++) {
            for (int y = 0; y < height; y++) {
                if (image.getRGB(x, y) < avg) {
                    Color color = new Color(image.getRGB(x, y));
                    int R, G, B;
                    R = checkColorRange(color.getRed() + 10);
                    G = checkColorRange(color.getGreen() + 10);
                    B = checkColorRange(color.getBlue() + 10);
                    color = new Color(R, G, B);
                    image.setRGB(x, y, color.getRGB());
                }
                if (image.getRGB(x, y) > avg) {
                    Color color = new Color(image.getRGB(x, y));
                    int R, G, B;
                    R = checkColorRange(color.getRed() - 10);
                    G = checkColorRange(color.getGreen() - 10);
                    B = checkColorRange(color.getBlue() - 10);
                    color = new Color(R, G, B);
                    image.setRGB(x, y, color.getRGB());
                }
            }
        }
        ImageIcon nIcon = new ImageIcon(image);
        label.setIcon(nIcon);
    }

    public static void negative(JLabel label) {
        Icon icon = label.getIcon();
        BufferedImage image = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics g = image.createGraphics();
        icon.paintIcon(null, g, 0, 0);
        g.dispose();
        int length = image.getWidth();
        int height = image.getHeight();
        for (int x = 0; x < length; x++) {
            for (int y = 0; y < height; y++) {
                Color color = new Color(image.getRGB(x, y));
                int R, G, B;
                R = checkColorRange(255 - color.getRed());
                G = checkColorRange(255 - color.getGreen());
                B = checkColorRange(255 - color.getBlue());
                color = new Color(R, G, B);
                image.setRGB(x, y, color.getRGB());
            }
        }
        ImageIcon nIcon = new ImageIcon(image);
        label.setIcon(nIcon);
    }

    private static int checkColorRange(int newColor) {
        if (newColor > 255) {
            newColor = 255;
        } else if (newColor < 0) {
            newColor = 0;
        }
        return newColor;
    }

    public static void mean_filter(int mask_type, JLabel label) {
        double[][] mask_normal = new double[][]{{1, 1, 1}, {1, 2, 1}, {1, 1, 1}};
        double[][] mask_gaussian = new double[][]{{1, 2, 1}, {2, 4, 2}, {1, 2, 1}};
        int factor;
        double[][] mask;
        if (mask_type == 0) {
            mask = mask_normal;
            factor = 10;
        } else {
            mask = mask_gaussian;
            factor = 16;
        }

        Icon icon = label.getIcon();
        BufferedImage image = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics g = image.createGraphics();
        icon.paintIcon(null, g, 0, 0);
        g.dispose();
        int length = image.getWidth();
        int height = image.getHeight();
        for (int x = 0; x < height - 2; x++) {
            for (int y = 0; y < length - 2; y++) {

                int R = 0;
                int G = 0;
                int B = 0;
                for (int n = 0; n < 3; n++) {
                    for (int m = 0; m < 3; m++) {
                        Color color = new Color(image.getRGB(y + n, x + m));
                        R += mask[m][n] * color.getRed();
                        G += mask[m][n] * color.getGreen();
                        B += mask[m][n] * color.getBlue();
                    }
                }
                R = checkColorRange((int) (R / factor));
                G = checkColorRange((int) (G / factor));
                B = checkColorRange((int) (B / factor));
                Color color = new Color((int) R, (int) G, (int) B);
                image.setRGB(y + 1, x + 1, color.getRGB());
            }
        }
        ImageIcon nIcon = new ImageIcon(image);
        label.setIcon(nIcon);
    }

    public static void median_filter(JLabel label) {
        Icon icon = label.getIcon();
        BufferedImage image = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics g = image.createGraphics();
        icon.paintIcon(null, g, 0, 0);
        g.dispose();
        int length = image.getWidth();
        int height = image.getHeight();

        for (int x = 0; x < length - 2; x++) {
            for (int y = 0; y < height - 2; y++) {
                int[] R = new int[9];
                int[] G = new int[9];
                int[] B = new int[9];
                int counter = 0;
                for (int m = 0; m < 3; m++) {
                    for (int n = 0; n < 3; n++) {
                        Color color = new Color(image.getRGB(x + m, y + n));
                        R[counter] = color.getRed();
                        G[counter] = color.getGreen();
                        B[counter] = color.getBlue();
                        counter++;
                    }
                }

                Arrays.sort(R);
                Arrays.sort(G);
                Arrays.sort(B);
                int nR = checkColorRange(R[4]);
                int nG = checkColorRange(G[4]);
                int nB = checkColorRange(B[4]);
                Color nColor = new Color(nR, nG, nB);
                image.setRGB(x + 1, y + 1, nColor.getRGB());

            }
        }
        ImageIcon nIcon = new ImageIcon(image);
        label.setIcon(nIcon);
    }
    
    public static void mid_point_filter(JLabel label) {
        Icon icon = label.getIcon();
        BufferedImage image = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics g = image.createGraphics();
        icon.paintIcon(null, g, 0, 0);
        g.dispose();
        int length = image.getWidth();
        int height = image.getHeight();

        for (int x = 0; x < length - 2; x++) {
            for (int y = 0; y < height - 2; y++) {
                int[] R = new int[9];
                int[] G = new int[9];
                int[] B = new int[9];
                int counter = 0;
                for (int m = 0; m < 3; m++) {
                    for (int n = 0; n < 3; n++) {
                        Color color = new Color(image.getRGB(x + m, y + n));
                        R[counter] = color.getRed();
                        G[counter] = color.getGreen();
                        B[counter] = color.getBlue();
                        counter++;
                    }
                }

                Arrays.sort(R);
                Arrays.sort(G);
                Arrays.sort(B);
                int nR = checkColorRange((R[0]+R[8])/2);
                int nG = checkColorRange((G[0]+G[8])/2);
                int nB = checkColorRange((B[0]+B[8])/2);
                Color nColor = new Color(nR, nG, nB);
                image.setRGB(x + 1, y + 1, nColor.getRGB());

            }
        }
        ImageIcon nIcon = new ImageIcon(image);
        label.setIcon(nIcon);
    }
    
    public static void sobel( JLabel label) {
        double[][] horizontal = new double[][]{{1, 0, -1}, {2, 0, -2}, {1, 0, -1}};
        double[][] vertical = new double[][]{{1, 2, 1}, {0, 0, 0}, {-1, -2, -1}};
        
        Icon icon = label.getIcon();
        BufferedImage image = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
        
        Graphics g = image.createGraphics();
        icon.paintIcon(null, g, 0, 0);
        g.dispose();
        int length = image.getWidth();
        int height = image.getHeight();
        BufferedImage nImage = new BufferedImage(length , height , image.getType());
            for (int x = 0; x < length - 2; x++) {
                for (int y = 0; y < height - 2; y++) {

                    int R1=0 ,R2 = 0;
                    int G1=0 ,G2 = 0;
                    int B1=0 ,B2 = 0;
                    for (int n = 0; n < 3; n++) {
                        for (int m = 0; m < 3; m++) {
                            Color color = new Color(image.getRGB(x + n, y + m));
                            R1 += horizontal[m][n] * color.getRed();
                            G1 += horizontal[m][n] * color.getGreen();
                            B1 += horizontal[m][n] * color.getBlue();
                        }
                    }
                    R1 = checkColorRange(R1);
                    G1 = checkColorRange(G1);
                    B1 = checkColorRange(B1);
                    
                    for (int n = 0; n < 3; n++) {
                        for (int m = 0; m < 3; m++) {
                            Color color = new Color(image.getRGB(x + n, y + m));
                            R2 += vertical[m][n] * color.getRed();
                            G2 += vertical[m][n] * color.getGreen();
                            B2 += vertical[m][n] * color.getBlue();
                        }
                    }
                    R2 = checkColorRange(R2);
                    G2 = checkColorRange(G2);
                    B2 = checkColorRange(B2);
                    
                    int nR = (int) Math.pow(Math.pow(R1, 2.0)+ Math.pow(R2, 2.0),0.5);
                    int nG = (int) Math.pow(Math.pow(G1, 2.0)+ Math.pow(G2, 2.0),0.5);
                    int nB = (int) Math.pow(Math.pow(B1, 2.0)+ Math.pow(B2, 2.0),0.5);
                    
                    nR = checkColorRange(nR);
                    nG = checkColorRange(nG);
                    nB = checkColorRange(nB);
                            
                    Color color = new Color(nR, nG, nB);
                    nImage.setRGB(x + 1, y + 1, color.getRGB());
                }
            }
            ImageIcon nIcon = new ImageIcon(nImage);
            label.setIcon(nIcon);
        }
    
    public static void robert( JLabel label) {
        double[][] horizontal = new double[][]{{0,0,0}, {0,1,0}, {0,0,-1}};
        double[][] vertical = new double[][]{{0,0,0}, {0,0,1}, {0,-1,0}};
        
        Icon icon = label.getIcon();
        BufferedImage image = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
        
        Graphics g = image.createGraphics();
        icon.paintIcon(null, g, 0, 0);
        g.dispose();
        int length = image.getWidth();
        int height = image.getHeight();
        BufferedImage nImage = new BufferedImage(length , height , image.getType());
            for (int x = 0; x < length - 2; x++) {
                for (int y = 0; y < height - 2; y++) {

                    int R1=0 ,R2 = 0;
                    int G1=0 ,G2 = 0;
                    int B1=0 ,B2 = 0;
                    for (int n = 0; n < 3; n++) {
                        for (int m = 0; m < 3; m++) {
                            Color color = new Color(image.getRGB(x + n, y + m));
                            R1 += horizontal[m][n] * color.getRed();
                            G1 += horizontal[m][n] * color.getGreen();
                            B1 += horizontal[m][n] * color.getBlue();
                        }
                    }
                    R1 = checkColorRange(R1);
                    G1 = checkColorRange(G1);
                    B1 = checkColorRange(B1);
                    
                    for (int n = 0; n < 3; n++) {
                        for (int m = 0; m < 3; m++) {
                            Color color = new Color(image.getRGB(x + n, y + m));
                            R2 += vertical[m][n] * color.getRed();
                            G2 += vertical[m][n] * color.getGreen();
                            B2 += vertical[m][n] * color.getBlue();
                        }
                    }
                    R2 = checkColorRange(R2);
                    G2 = checkColorRange(G2);
                    B2 = checkColorRange(B2);
                    
                    int nR = (int) Math.pow(Math.pow(R1, 2.0)+ Math.pow(R2, 2.0),0.5);
                    int nG = (int) Math.pow(Math.pow(G1, 2.0)+ Math.pow(G2, 2.0),0.5);
                    int nB = (int) Math.pow(Math.pow(B1, 2.0)+ Math.pow(B2, 2.0),0.5);
                    
                    nR = checkColorRange(nR);
                    nG = checkColorRange(nG);
                    nB = checkColorRange(nB);
                            
                    Color color = new Color(nR, nG, nB);
                    nImage.setRGB(x + 1, y + 1, color.getRGB());
                }
            }
            ImageIcon nIcon = new ImageIcon(nImage);
            label.setIcon(nIcon);
        }
    
    public static void flipHorizontal(JLabel label){
        Icon icon = label.getIcon();
        BufferedImage image = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics g = image.createGraphics();
        icon.paintIcon(null, g, 0, 0);
        g.dispose();
        int length = image.getWidth();
        int height = image.getHeight();
        BufferedImage nImage = new BufferedImage(length, height, image.getType());

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < length; x++) {
                nImage.setRGB(length - x -1, y, image.getRGB(x, y));
            }
        }

        ImageIcon nIcon = new ImageIcon(nImage);
        label.setIcon(nIcon);
    }
        
    public static void flipVertical(JLabel label){
        Icon icon = label.getIcon();
        BufferedImage image = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics g = image.createGraphics();
        icon.paintIcon(null, g, 0, 0);
        g.dispose();
        int length = image.getWidth();
        int height = image.getHeight();
        BufferedImage nImage = new BufferedImage(length, height, image.getType());

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < length; x++) {
                nImage.setRGB(x, height -y -1, image.getRGB(x, y));
            }
        }

        ImageIcon nIcon = new ImageIcon(nImage);
        label.setIcon(nIcon);
    }
    
    
}
