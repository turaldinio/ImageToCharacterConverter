package ru.netology.graphics.image;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
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
    private boolean setTextColor = false;
    private TextColorSchema schema;


    @Override
    public String convert(String url) throws IOException, BadImageSizeException {


        BufferedImage img = ImageIO.read(new URL(url));

        if (ratioChanged) {
            int currentRatio = img.getWidth() / img.getHeight();

            if (currentRatio > maxRatio) {
                throw new BadImageSizeException(maxRatio, currentRatio);
            }

        }

        int newWidth = img.getWidth();
        int newHeight = img.getHeight();


        if (widthChanged && heightChanged) {
            double widthDifference = (double) img.getWidth() / width;

            newWidth = (int) ((double) img.getWidth() / widthDifference);
            newHeight = (int) ((double) img.getHeight() / widthDifference);
        } else {
            if (widthChanged) {
                double widthDifference = (double) img.getWidth() / width;

                newWidth = (int) (img.getWidth() / widthDifference);
            } else {
                if (heightChanged) {
                    double widthDifference = (double) img.getHeight() / height;

                    newHeight = (int) (img.getHeight() / widthDifference);
                }
            }
        }

        Image scaledImage = img.getScaledInstance(newWidth, newHeight, BufferedImage.SCALE_SMOOTH); //Новая суженная картинка


        BufferedImage bwImg = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_BYTE_GRAY);//чернобелая копия

        //       ImageIO.write(bwImg, "png", new File("C:\\Users\\Admin\\Desktop\\myFile.png"));


        Graphics2D graphics = bwImg.createGraphics();

        graphics.drawImage(scaledImage, 0, 0, null);// рисует черно белую картинку в новый размер

        WritableRaster bwRaster = bwImg.getRaster();

        if (!setTextColor) {
            this.schema = new Schema();
            setTextColorSchema(schema);
        }

        int rpgArray[] = new int[3];



        StringBuilder result = new StringBuilder();
        for (int w = 0; w < bwRaster.getHeight(); w++) {
            for (int h = 0; h < bwRaster.getWidth(); h++) {
                int color = bwRaster.getPixel(h, w, rpgArray)[0];
                char c = schema.convert(color);
                result.append(c).append(c);
            }
            result.append("\n");

        }

        return result.toString();

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
        setTextColor = true;
        this.schema = schema;
    }
}
