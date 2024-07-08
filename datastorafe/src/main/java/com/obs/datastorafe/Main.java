/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.obs.datastorafe;

/**
 *
 * @author D-16
 */
public class Main {
    public static void main(String[] args) {
     
        DataStorage memoryStorage = new MemoryStorage1();
        memoryStorage.writeData("Data stored in memory");
        System.out.println("Memory storage: " + memoryStorage.readData());

        DataStorage fileStorage = new FileStorage("data.txt");
        fileStorage.writeData("Data stored in file");
        System.out.println("File storage: " + fileStorage.readData());

        DataStorage databaseStorage = new DatabaseStorage("database.db");
        databaseStorage.writeData("Ini data uang kusimpan");
        System.out.println("Database storage: " + databaseStorage.readData());
    }
}
