package com.mycompany;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;


import static java.awt.Font.PLAIN;

public class Main extends JFrame{

    public static void main(String[] args) {

        new Main();
    }

    private Main(){
        this.setSize(400, 400);
        this.setTitle("To-Do List");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        //miejsce na wpisanie nazwy i podanie daty
        //klikamy date - data zapisana.
        // po dodaniu (kliknieciu enter) size != null data do dzisiejszej i dodajemy zadanie (instancje klasy)
        //dodajemy gwiazdke czy oznaczac jako wazne

        JPanel panel = new JPanel(new BorderLayout());

        Box eventBox = Box.createHorizontalBox();

        JLabel plusSign = new JLabel("+");
        plusSign.setFont(new Font("Serafi", PLAIN, 40));
        eventBox.add(plusSign);

        JTextArea jTextArea = new JTextArea();
        panel.add(jTextArea, BorderLayout.CENTER);

        JTextField event = new JTextField(30);
        event.setPreferredSize(new Dimension(40,30));
        event.setFont(new Font("Times", Font.ITALIC, 20));
        eventBox.add(event);

        ImageIcon calendar = new ImageIcon("seo-social-web-network-internet_104-128.png");
        Image image = calendar.getImage();
        Image newImage = image.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        calendar = new ImageIcon(newImage);

        JButton date = new JButton(calendar);
        date.setBorder(BorderFactory.createEmptyBorder());
        date.setContentAreaFilled(false);
        date.setPreferredSize(new Dimension(40, 40));
        eventBox.add(date);
        panel.add(eventBox, BorderLayout.NORTH);

        this.add(panel);
        this.setVisible(true);
    }
}
