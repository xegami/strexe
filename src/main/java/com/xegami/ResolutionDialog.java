package com.xegami;

import com.xegami.utils.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class ResolutionDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox resolutionsCB;

    public ResolutionDialog() {
        setContentPane(contentPane);
        getRootPane().setDefaultButton(buttonOK);
        setPreferredSize(new Dimension(380, 200));
        setTitle("Cambiar resolución");

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        setNewResolution();
        JOptionPane.showMessageDialog(this, "Resolución cambiada a " + resolutionsCB.getSelectedItem());
        dispose();
    }

    private void onCancel() {
        dispose();
    }

    private void setNewResolution() {
        try (PrintWriter output = new PrintWriter(Constants.GAMEUSERSETTINGS_PATH + ".new", "UTF-8")) {

            try (Scanner sc = new Scanner(new File(Constants.GAMEUSERSETTINGS_PATH))) {

                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    if (line.contains("ResolutionSizeX")){
                        line = line.split("=")[0] + "=" + resolutionsCB.getSelectedItem().toString().split("x")[0];
                    } else if (line.contains("ResolutionSizeY")) {
                        line = line.split("=")[0] + "=" + resolutionsCB.getSelectedItem().toString().split("x")[1];
                    }
                    output.write(line + "\n");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        renameFiles();
    }

    private void renameFiles() {
        File original = new File(Constants.GAMEUSERSETTINGS_PATH);
        File strexe = new File(Constants.GAMEUSERSETTINGS_PATH + ".new");

        original.delete();
        strexe.renameTo(new File(Constants.GAMEUSERSETTINGS_PATH));
    }

}
