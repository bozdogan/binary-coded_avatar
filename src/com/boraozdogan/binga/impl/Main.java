package com.boraozdogan.binga.impl;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import javax.swing.*;

public class Main
{
    static Scanner stdin = new Scanner(System.in);

    static void DisplayBitmap(BufferedImage picture)
    {
        JPanel panel = new JPanel();
        panel.add(new JLabel(new ImageIcon(picture)));

        JFrame window = new JFrame();
        window.setSize(picture.getWidth() + 30, picture.getHeight() + 50);
        window.add(panel);
        window.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        window.setVisible(true);

    }

    public static void main(String[] args)
    {
        // 1. get input string
        System.out.print("Input: ");
        String inputText = stdin.nextLine();
        if(inputText.length() > 12)
        {
            inputText = inputText.substring(0, 12);
        }

        // 2. convert to binary
        byte[] binaryString = inputText.getBytes(StandardCharsets.UTF_8);

        // 3. create image matrix
        int width = 8;
        int height = binaryString.length;
        byte[] bitmap = new byte[width * height];

        for(int j = 0; j < height; ++j)
        {
            int mask = 0b10000000;
            for(int i = 0; i < width; ++i)
            {
                bitmap[j*width + i] = (binaryString[j] & mask) != 0 ?
                        (byte) 1 :
                        (byte) 0;

                mask = mask >> 1;
            }

        }

        debug_PrintBitmap(bitmap, width);

        // 4. align offsets so the image is square
        boolean portrait = height > width;
        int unitOffsetV = portrait ?
                0 :
                (width - height);
        int unitOffsetH = portrait ?
                (height - width) :
                0;

        int unitSize = 16;
        int frameThickness = 16;

        // 5. generate image
        int contentSize = (width + unitOffsetH) * unitSize;
        int imgSize = contentSize + 2 * frameThickness;

        BufferedImage picture = new BufferedImage(imgSize, imgSize, BufferedImage.TYPE_INT_RGB);
        Graphics2D gfx = picture.createGraphics();

        Color bgColor = Color.LIGHT_GRAY;
        Color zeroColor = Color.WHITE;
        Color oneColor = Color.BLACK;

        gfx.setColor(bgColor);
        gfx.fillRect(0, 0, imgSize, imgSize);

        for(int j = 0; j < height; ++j)
        {
            for(int i = 0; i < width; ++i)
            {
                if(bitmap[j*width + i] != 0)
                    gfx.setColor(oneColor);
                else
                    gfx.setColor(zeroColor);

                gfx.fillRect(
                        frameThickness + (unitOffsetH/2 + i)*unitSize,
                        frameThickness + (unitOffsetV/2 + j)*unitSize,
                        unitSize, unitSize);
            }
        }

        gfx.dispose();

        // *. (optional) display image
        DisplayBitmap(picture);
        
        // 6. save image
        try
        {
            File file = new File("github_avatar.png");
            ImageIO.write(picture, "png", file);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

    }


    static void debug_PrintBitmap(byte[] bitmap, int pitch)
    {
        for(int i = 0; i < bitmap.length; ++i)
        {
            if(i % pitch == 0)
                System.out.println();

            System.out.print(bitmap[i]!=0 ? "<>" : "´´");
        }
        System.out.println();
    }
}
