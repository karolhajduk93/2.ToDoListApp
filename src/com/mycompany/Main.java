package com.mycompany;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;


import static java.awt.Font.PLAIN;

public class Main extends JFrame{

    JSpinner dateSpin;
    Box eventBox, boxBox;
    JTextField eventName;
    Date date;
    JPanel panel;
    JScrollPane scrollPane;
    ArrayList<Task> allTasks = new ArrayList<>();
    ArrayList<Task> doneTasks = new ArrayList<>();
    Task myTask;


    public static void main(String[] args) {

        new Main();
    }

    private Main(){
        this.setSize(400, 600);
        this.setTitle("To-Do List");
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
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
                    /* to do: saving every task in some file, adding to "done" b4 removing
                       also "done" to do*/


                    myTask = new Task(eventName.getText(), date);
                    allTasks.add(myTask);

                    for(Task task: allTasks) {
                        task.getTaskCheckbox().addItemListener(event -> {
                            if (task.getTaskCheckbox().getState()) {
                                boxBox.remove(task.getTask());
                                doneTasks.add(task); // adding to done list
                                allTasks.remove(task);
                                boxBox.revalidate();
                                boxBox.repaint();
                            }
                        });
                    }

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

        ///////////////////////////////////////////////////////////
        Path path = Paths.get("C:\\Users\\Karol_Hajduk\\IdeaProjects\\ToDoList\\Task_to_do.ser");

        if(Files.exists(path)) {

            //read ArrayList from file
            ArrayList<Task> fileTasks = null;
            try {
                FileInputStream fileInputStream = new FileInputStream("Task_to_do.ser");
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                fileTasks = (ArrayList<Task>) objectInputStream.readObject();
                fileInputStream.close();
                objectInputStream.close();
            } catch (IOException | ClassNotFoundException e2) {
                e2.printStackTrace();
            }

            for (Task task : fileTasks) {
                allTasks.add(task);

                for(Task task2: allTasks) {
                    task.getTaskCheckbox().addItemListener(event -> {
                        if (task2.getTaskCheckbox().getState()) {
                            boxBox.remove(task2.getTask());
                            doneTasks.add(task2); // adding to done list
                            allTasks.remove(task2);
                            boxBox.revalidate();
                            boxBox.repaint();
                        }
                    });
                }
                boxBox.add(task.getTask());
                boxBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, boxBox.getMinimumSize().height + 11));
                panel.add(boxBox);
            }
            boxBox.revalidate();
            boxBox.repaint();
        }
        /////////////////////////////////////////////

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentHidden(ComponentEvent e) {
                super.componentHidden(e);
                //////////////////////////////////////////////////
                //write  ArrayList to file
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream("Task_to_do.ser");
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                    objectOutputStream.writeObject(allTasks);
                    objectOutputStream.close();
                    fileOutputStream.close();
                }catch (IOException e1){
                    e1.printStackTrace();
                }
                ////////////////////////////////////
                ((JFrame)(e.getComponent())).dispose();
            }
        });

        this.add(scrollPane);
        this.setVisible(true);
    }
}
