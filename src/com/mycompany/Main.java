package com.mycompany;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.*;


import static java.awt.Font.PLAIN;

public class Main extends JFrame{

    JSpinner dateSpin;
    Box eventBox, boxBox;
    JTextField eventName;
    Date date;
    JPanel panel;
    JScrollPane scrollPane;
    Task myTask;
    SimpleDateFormat simpleDate;


    public static void main(String[] args) {

        new Main();
    }

    private Main(){
        this.setSize(400, 600);
        this.setTitle("To-Do List");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);


        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        boxBox = Box.createVerticalBox();
        scrollPane = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        eventBox = Box.createHorizontalBox();
        eventBox.setBorder(BorderFactory.createEtchedBorder());

        JLabel plusSign = new JLabel("+");
        plusSign.setFont(new Font("Serafi", PLAIN, 20));
        plusSign.setMaximumSize(new Dimension(Integer.MAX_VALUE, plusSign.getMinimumSize().height));
        eventBox.add(plusSign);

        eventName = new JTextField(20);
        eventName.setFont(new Font("Times", Font.ITALIC, 15));
        eventName.setMaximumSize(new Dimension(Integer.MAX_VALUE, eventName.getMinimumSize().height));
        eventName.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (eventName.getText().length() >= 30 )
                    e.consume();
            }
        });
        eventName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == eventName){
                    /* to do: saving every task in some file, figure out how to remove
                       those tasks (checkbox + jlabel) -> whole box from screen or how to send them to "done"
                       also "done" to do*/


                    myTask = new Task(eventName.getText(), date);

                    boxBox.add(myTask.getTask());
                    boxBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, boxBox.getMinimumSize().height + 11));
                    panel.add(boxBox);
                    panel.revalidate();
                    panel.repaint();

                }
            }
        });
        eventBox.add(eventName);

        date = new Date();
        dateSpin = new JSpinner(new SpinnerDateModel(date, null, null, Calendar.DAY_OF_MONTH));
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpin, "dd/MM/yy");
        dateSpin.setEditor(dateEditor);
        dateSpin.setMaximumSize(new Dimension(Integer.MAX_VALUE, dateSpin.getMinimumSize().height));
        dateSpin.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if(e.getSource() == dateSpin){
                    date = (Date) dateSpin.getValue();

                }
            }
        });
        eventBox.add(dateSpin);

        panel.add(eventBox, new FlowLayout());

        this.add(scrollPane);
        this.setVisible(true);
    }
}
