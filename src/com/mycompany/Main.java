package com.mycompany;

import javax.swing.*;

public class Main extends JFrame{

    public static void main(String[] args) {

        new Main();
    }

    private Main(){
        this.setSize(400, 400);
        this.setTitle("To-Do Application");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        //miejsce na wpisanie nazwy i podanie daty
        //klikamy date - data zapisana.
        // po dodaniu (kliknieciu enter) size != null data do dzisiejszej i dodajemy zadanie
        //dodajemy gwiazdke czy oznaczac jako wazne


        this.setVisible(true);
    }
}
