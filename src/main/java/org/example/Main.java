package org.example;

import CustomArrayList.CustomArrayList;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList a = new ArrayList<>();
        CustomArrayList arList = new CustomArrayList();
        arList.add(1);
        arList.add(2.5);
        arList.remove(0);
        arList.add(10);
        arList.add(-5);
        arList.remove(arList.size() - 1);
        for (int i = 0; i < arList.size(); ++i) {
            System.out.println(arList.get(i));
        }
    }
}
