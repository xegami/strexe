package com.xegami;

import com.xegami.utils.Constants;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HomeForm extends JFrame {

    private JPanel rootPanel;
    private JButton changeResButton;
    private JLabel yRes;
    private JLabel xRes;

    public HomeForm() {
        add(rootPanel);
        setTitle("Strexe");
        setSize(500, 400);
        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                try {
                    getMyResolution();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });

        firstSteps();
    }

    private void firstSteps() {
        try {
            getMyResolution();

            changeResButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ResolutionDialog resolutionDialog = new ResolutionDialog();
                    resolutionDialog.setVisible(true);
                    resolutionDialog.setLocationRelativeTo(null);
                    resolutionDialog.pack();
                }
            });
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    private void getMyResolution() throws Exception {
        Scanner sc = new Scanner(new File(Constants.GAMEUSERSETTINGS_PATH));

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.contains("ResolutionSizeX")) {
                xRes.setText(line.split("=")[1]);
            } else if (line.contains("ResolutionSizeY")) {
                yRes.setText(line.split("=")[1]);
            }
        }

        sc.close();
    }
}
