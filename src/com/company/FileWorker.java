package com.company;

import java.io.*;
import java.net.URL;
import java.util.HashMap;

public class FileWorker {

    public static void write(String fileName, String text) {
        //Определяем файл
        File file = new File(fileName);

        try {
            //проверяем, что если файл не существует то создаем его
            if (!file.exists()) {
                file.createNewFile();
            }

            //PrintWriter обеспечит возможности записи в файл
            PrintWriter out = new PrintWriter(file.getAbsoluteFile());

            try {
                //Записываем текст у файл
                out.print(text);
                System.out.println("Успешно");
            } finally {
                //После чего мы должны закрыть файл
                //Иначе файл не запишется
                out.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String read(String urlStr, String fileName) throws FileNotFoundException {
        //Этот спец. объект для построения строки
        StringBuilder sb = new StringBuilder();
        InputStream inputStream = null;
        BufferedReader bufferedReader;

        try {
            URL url = new URL(urlStr);
            inputStream = url.openStream(); //openStream позволяет создать входной поток для чтения файла ресурса, связанного с созданным объектом класса URL
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            try {
                //В цикле построчно считываем файл
                String s;
                while ((s = bufferedReader.readLine()) != null) {
                    sb.append(s);
                    sb.append("\n");
                }
            } finally {
                try {
                    if (inputStream != null) inputStream.close();
                } catch (IOException e) {
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //Возвращаем полученный текст с файла
        return sb.toString();
    }

    public static void statistic(String fileName) throws IOException{
        FileReader in = new FileReader(fileName); // поток который подключается к текстовому файлу
        BufferedReader br = new BufferedReader(in); // соединяем FileReader с BufferedReader
        String string;
        StringBuilder resultString = new StringBuilder("");
        while ((string = br.readLine()) != null) {
            resultString.append(string);
            resultString.append("\n");//append() - Метод соединяет представление любого другого типа данных.
        }

        String[] lines = resultString.toString().split(" |\"|\\t|\\n|\\r|' | '|'|, |,|;|\\.|\\?|!|-|:|\\[|\\]|\\(|\\)|<|>|=|»|«|</|/>|&");

        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for (String t : lines) {
            if (map.containsKey(t)) {
                map.put(t, map.get(t) + 1);
            } else {
                map.put(t, 1);
            }
        }
        in.close();
        map.forEach((k, v) -> System.out.println(k +"  :  "+ v));
    }

    private static void exists(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        if (!file.exists()) {
            throw new FileNotFoundException(file.getName());
        }
    }

    public static void update(String urlStr, String nameFile, String newText) throws FileNotFoundException {
        StringBuilder sb = new StringBuilder();
        String oldFile = read(urlStr, nameFile);
        sb.append(oldFile);
        sb.append(newText);
        write(nameFile, sb.toString());
    }
}
