package ru.netology.graphics.image;

public class Schema implements TextColorSchema {

    char[] symbol = new char[]{
            '#', '$', '@', '%', '*', '+', '-', '\''
    };

    private int colorBorder = 36;//255/7

    @Override
    public char convert(int color) {

        return symbol[ (color / colorBorder)];
    }
}
