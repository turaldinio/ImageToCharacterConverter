package ru.netology.graphics.image;

public class Schema implements TextColorSchema {

    char[] symbol = new char[]{
            '#', '$', '@', '%', '*', '+', '-', '\''
    };

    @Override
    public char convert(int color) {
        double colorBorder = 255.0 / symbol.length;
        return symbol[(int) (color / colorBorder)];


    }
}
