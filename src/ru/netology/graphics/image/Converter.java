package ru.netology.graphics.image;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class Converter implements TextGraphicsConverter {

    private int width;
    private int height;
    private double ratio;
    private boolean ratioChanged;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public double getRatio() {
        return ratio;
    }

    public TextColorSchema getTextColorSchema() {
        return textColorSchema;
    }

    private TextColorSchema textColorSchema;


    @Override
    public String convert(String url) throws IOException, BadImageSizeException {

        BufferedImage img = ImageIO.read(new URL(url));
        if (ratioChanged) {
            double maxRatio = img.getWidth() / img.getHeight();
            if (maxRatio > ratio) {
                throw new BadImageSizeException(ratio, maxRatio);
            }

        }


    }

    @Override
    public void setMaxWidth(int width) {

    }

    @Override
    public void setMaxHeight(int height) {

    }

    @Override
    public void setMaxRatio(double maxRatio) {
        ratioChanged = true;

    }

    @Override
    public void setTextColorSchema(TextColorSchema schema) {

    }
}
