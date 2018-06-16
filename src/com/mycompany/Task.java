package com.mycompany;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

class Task extends JFrame {
    private Checkbox taskCheckbox;
    private JLabel taskName;
    private Date deadLine;
    private Box task;
    private SimpleDateFormat simpleDate = new SimpleDateFormat("E-dd-MM-yyyy");

    Task(String Name, Date date){
        this.taskCheckbox = new Checkbox();
        this.taskName = new JLabel(Name);
        this.deadLine = date;
        task = Box.createHorizontalBox();
        task.add(taskCheckbox);
        task.add(taskName);
        task.setBorder(BorderFactory.createTitledBorder(simpleDate.format(date)));
    }

    public Checkbox getTaskCheckbox() {
        return taskCheckbox;
    }

    public void setTaskCheckbox(Checkbox taskCheckbox) {
        this.taskCheckbox = taskCheckbox;
    }

    public JLabel getTaskName() {
        return taskName;
    }

    public void setTaskName(JLabel taskName) {
        this.taskName = taskName;
    }

    public Date getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(Date deadLine) {
        this.deadLine = deadLine;
    }

    public Box getTask() {
        return task;
    }

    public void setTask(Box task) {
        this.task = task;
    }
}
