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
    private boolean sizeChanged = false;

    private TextColorSchema textColorSchema;


    @Override
    public String convert(String url) throws IOException, BadImageSizeException {

        BufferedImage img = ImageIO.read(new URL(url));
        if (ratioChanged) {
            System.out.println(img.getWidth()+" "+img.getHeight());
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


        Image scaledImage = img.getScaledInstance(newWidth, newHeight, BufferedImage.SCALE_SMOOTH); //Новая суженная картинка

        BufferedImage bwImg = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_BYTE_GRAY);//чернобелая копия
        Graphics2D graphics = bwImg.createGraphics();

        graphics.drawImage(scaledImage, 0, 0, null);
        ImageIO.write(bwImg, "png", new File("C:\\Users\\Admin\\Desktop\\myFile.png"));

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
