package com.xegami;

import javax.swing.*;

public class Main {

    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                HomeForm homeForm = new HomeForm();
                homeForm.setVisible(true);
                homeForm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                homeForm.setLocationRelativeTo(null);
            }
        });

    }
}
