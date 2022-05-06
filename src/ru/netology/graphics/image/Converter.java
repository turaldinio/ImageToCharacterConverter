package ru.netology.graphics.image;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class Converter implements TextGraphicsConverter {

    private int width;
    private int height;
    private double maxRatio;
    private boolean ratioChanged = false;
    private boolean sizeChanged = false;

    private TextColorSchema textColorSchema;


    @Override
    public String convert(String url) throws IOException, BadImageSizeException {

        BufferedImage img = ImageIO.read(new URL(url));
        if (ratioChanged) {
            double maxRatio = img.getWidth() / img.getHeight();
            if (maxRatio > maxRatio) {
                throw new BadImageSizeException(maxRatio, maxRatio);
            }

        }

        int newWidth = img.getWidth();
        int newHeight = img.getHeight();

        if (sizeChanged) {
            double sizeDifference = img.getWidth() / width;
            newWidth = (int) (img.getWidth() / sizeDifference);
            newHeight = (int) (img.getHeight() / sizeDifference);
        }


        Image scaledImage = img.getScaledInstance(newWidth, newHeight, BufferedImage.SCALE_SMOOTH);


        return "turaldinio";

    }

    @Override
    public void setMaxWidth(int width) {
        sizeChanged = true;
        this.width = width;
    }

    @Override
    public void setMaxHeight(int height) {
        sizeChanged = true;
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
