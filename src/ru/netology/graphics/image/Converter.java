package ru.netology.graphics.image;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Converter implements TextGraphicsConverter {

    private int width;
    private int height;
    private double maxRatio;
    private boolean ratioChanged = false;
    private boolean heightChanged = false;
    private boolean widthChanged = false;


    private TextColorSchema textColorSchema;


    @Override
    public String convert(String url) throws IOException, BadImageSizeException {

        BufferedImage img = ImageIO.read(new URL(url));

        if (ratioChanged) {
            int currentRatio = img.getWidth() / img.getHeight();
            if (currentRatio > maxRatio) {
                throw new BadImageSizeException(maxRatio, maxRatio);
            }
        }

        int newWidth = img.getWidth();
        int newHeight = img.getHeight();


        double widthDifference = (double) img.getWidth() / width;

        if (widthChanged && heightChanged) {
            newWidth = (int) (img.getWidth() / widthDifference);
            newHeight = (int) (img.getHeight() / widthDifference);
        } else {
            if (widthChanged) {
                newWidth = (int) (img.getWidth() / widthDifference);
            } else {
                newHeight = (int) (img.getHeight() / widthDifference);

            }
        }


        Image scaledImage = img.getScaledInstance(newWidth, newHeight, BufferedImage.SCALE_SMOOTH); //Новая суженная картинка

        BufferedImage bwImg = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_BYTE_GRAY);//чернобелая копия
        Graphics2D graphics = bwImg.createGraphics();

        graphics.drawImage(scaledImage, 0, 0, null);
        ImageIO.write(bwImg, "png", new File("C:\\Users\\Admin\\Desktop\\myFile.png"));

        return "turaldinio";

    }

    @Override
    public void setMaxWidth(int width) {
        widthChanged = true;
        this.width = width;
    }

    @Override
    public void setMaxHeight(int height) {
        heightChanged = true;
        this.height = height;
    }

    @Override
    public void setMaxRatio(double maxRatio) {
        ratioChanged = true;
        this.maxRatio = maxRatio;

    }

    @Override
    public void setTextColorSchema(TextColorSchema schema) {

    }
}
