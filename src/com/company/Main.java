package com.company;

import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.print("Input url: ");
        String urlStr = in.nextLine();

        String fileName = "C:/Users/Marin/OneDrive/Рабочий стол/mr.txt";
        String textFromFile = FileWorker.read(urlStr, fileName);
        FileWorker.write(fileName, textFromFile);
        FileWorker.statistic(fileName);
    }
}
