package com.boraozdogan.binarycodedavatar.impl;

import com.boraozdogan.binarycodedavatar.impl.conf.ConfigurationProviderSingleton;
import com.boraozdogan.binarycodedavatar.impl.icutils.ImageContainerDisplayHandler;
import com.boraozdogan.binarycodedavatar.impl.icutils.ImageContainerFileSaveUtility;

import static com.boraozdogan.binarycodedavatar.impl.Constants.INTEGER_MULTIPLIER_TWO;
import static com.boraozdogan.binarycodedavatar.impl.Constants.IDENTIFIER_STRING_FOR_PNG_PICTURE_FORMAT;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.*;

/** Binary-Coded GitHub Avatar (BinGA) Creator */
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
        // 0. initialize configuration provider
        ConfigurationProviderSingleton.getInstance().initializeSingleton(Constants.PROVIDED_DEFAULT_CONFIGURATION_PATH);

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
        int imgSize = contentSize + INTEGER_MULTIPLIER_TWO * frameThickness;

        Graphics2ContextManager ctxman = Graphics2ContextManager.getInstance();

        ImageContainer ic = new ImageContainer();
        ic.setMetadata("imgwdt", Integer.valueOf(imgSize).toString());
        ic.setMetadata("imghgt", Integer.valueOf(imgSize).toString());
        ic.setMetadata("imgtyp", Character.toString('1'));
        ic.createNew();
        ctxman.setActiveImageContainer(ic);
        
        ctxman.createGraphicsContextForActiveImage();
        Graphics2D gfx = ctxman.getActiveGraphicsContextHandle();

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
                        frameThickness + (unitOffsetH/INTEGER_MULTIPLIER_TWO + i)*unitSize,
                        frameThickness + (unitOffsetV/INTEGER_MULTIPLIER_TWO + j)*unitSize,
                        unitSize, unitSize);
            }
        }

        gfx.dispose();

        // *. (optional) display image
        ImageContainerDisplayHandler disphdl = new ImageContainerDisplayHandler(ic);
        disphdl.executeDisplayProcedure(Main::DisplayBitmap);
        
        // 6. save image
        try
        {
            File file = new File("github_avatar.png");
            ImageContainerFileSaveUtility icfsu = new ImageContainerFileSaveUtility(ic, file);
            icfsu.setPictureFormatIdentifier(IDENTIFIER_STRING_FOR_PNG_PICTURE_FORMAT);
            icfsu.executeFileSavingProcess();
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

            System.out.print(bitmap[i]!=0 ?
                    ConfigurationProviderSingleton.getInstance().fetchConfiguration().getValue("DEBUG_bit_matrix_printer_one_character", String.class) :
                    ConfigurationProviderSingleton.getInstance().fetchConfiguration().getValue("DEBUG_bit_matrix_printer_zero_character", String.class));
        }
        System.out.println();
    }
}

// 2020-07-11
// b.ozdogan