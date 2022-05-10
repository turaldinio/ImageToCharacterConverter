package ru.netology.graphics;

import ru.netology.graphics.image.Converter;
import ru.netology.graphics.image.TextGraphicsConverter;
import ru.netology.graphics.server.GServer;

import java.io.File;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) throws Exception {
        TextGraphicsConverter converter = new Converter(); // Создайте тут объект вашего класса конвертера
        //  System.out.println(converter.convert("https://hobbiphoto.ru/wp-content/uploads/2018/02/2018-02-05_16-50-53.png"));
        GServer server = new GServer(converter); // Создаём объект сервера
        server.start(); // Запускаем

    }
}
