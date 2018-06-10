package com.mycompany;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


import static java.awt.Font.PLAIN;

public class Main extends JFrame{

    JSpinner dateSpin;
    Box eventBox, taskBox, boxBox;
    JTextField eventName;
    Date date;
    Checkbox doneCheck;
    JLabel taskLabel;
    JPanel panel;

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
        boxBox = Box.createVerticalBox();

        eventBox = Box.createHorizontalBox();
        eventBox.setBorder(BorderFactory.createEtchedBorder());

        JLabel plusSign = new JLabel("+");
        plusSign.setFont(new Font("Serafi", PLAIN, 20));
        eventBox.add(plusSign);



        eventName = new JTextField(20);
        eventName.setFont(new Font("Times", Font.ITALIC, 15));
        eventName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == eventName){
                    /* to do: saving every task in some file, figure out how to remove
                       those tasks (checkbox + jlabel) -> whole box from screen or how to send them to "done"
                       aslo "done" to do*/

                    SimpleDateFormat simpleDate = new SimpleDateFormat("E-dd-MM-yyyy");
                    taskBox = Box.createHorizontalBox();
                    taskBox.setBorder(BorderFactory.createTitledBorder(simpleDate.format(date)));

                    doneCheck = new Checkbox();
                    panel.add(doneCheck);
                    taskBox.add(doneCheck);

                    String taskName = eventName.getText();
                    taskLabel = new JLabel(taskName);
                    taskLabel.setMinimumSize(new Dimension(500,10));
                    taskLabel.setPreferredSize(new Dimension(300, 10));
                    taskBox.add(taskLabel);

                    boxBox.add(taskBox);
                    
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
        
        this.add(panel);
        this.setVisible(true);
    }
}
