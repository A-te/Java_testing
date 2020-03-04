package ru.barancev.testing;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collections {

    public static void main(String[] args){
// Обычный вариант
//        String[] langs = new String[4];
//        langs[0] ="Java";
//        langs[1] = "C#";
//        langs[2] = "Python";
//        langs[3] = "PHP";

        String[] langs = {"Java", "C#", "Python", "PHP"};

// Обычный вариант
//        for (int i = 0; i < langs.length; i++){
//            System.out.println("I want to learn " + langs[i]);
//        }

       // Улучшенный вариант
        for (String x : langs){
            System.out.println("I want to learn " + x);
        }
        System.out.println();

       // Arrays
        List<String> languages = new ArrayList<String>();
        languages.add("Java");
        languages.add("C#");
        languages.add("C++");

        for (String x : languages){
            System.out.println("I want to learn " + x);
        }
        System.out.println();


        List<String> languages2 = Arrays.asList("Java", "C#", "Python", "PHP", "Ruby");

        for(String x : languages2){
            System.out.println("I want to learn " + x);
        }
        System.out.println();


        List<String> languages3 = Arrays.asList("Java", "C++", "Python", "Java Script", "HTML");

        for (int i = 0; i < languages3.size(); i++){
            System.out.println("I want to learn " + languages3.get(i));
        }
        System.out.println();

    }
}
