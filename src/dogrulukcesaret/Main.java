package dogrulukcesaret;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Doğruluk Cesaretlik Oyunu");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);
            frame.setLocationRelativeTo(null);

            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());

            JLabel title = new JLabel("Doğruluk Cesaretlik", JLabel.CENTER);
            title.setFont(new Font("Arial", Font.BOLD, 24));
            panel.add(title, BorderLayout.CENTER);

            JButton startButton = new JButton("Başla");
            startButton.setFont(new Font("Arial", Font.PLAIN, 18));
            panel.add(startButton, BorderLayout.SOUTH);

            frame.add(panel);
            frame.setVisible(true);

            startButton.addActionListener(e -> {
                frame.dispose();
                OyuncuEklemeEkrani oyuncuEklemeEkrani = new OyuncuEklemeEkrani();
                oyuncuEklemeEkrani.setVisible(true);
            });
        });
    }
}

//MUHAMMEDA ASAF BUDAK HAMZA ZÜBEYİR GÜNEŞ
