package dogrulukcesaret;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class OyuncuEklemeEkrani extends JFrame {
    private List<JTextField> oyuncuAlanlari = new ArrayList<>();

    public OyuncuEklemeEkrani() {
        setTitle("Oyuncu Ekleme");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel oyuncuPanel = new JPanel();
        oyuncuPanel.setLayout(new GridLayout(0, 1));

        JScrollPane scrollPane = new JScrollPane(oyuncuPanel);
        panel.add(scrollPane, BorderLayout.CENTER);

        JButton ekleButton = new JButton("+ Oyuncu Ekle");
        JButton baslatButton = new JButton("Oyunu Başlat");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(ekleButton);
        buttonPanel.add(baslatButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        ekleButton.addActionListener(e -> {
            JTextField oyuncuAlani = new JTextField();
            oyuncuAlani.setPreferredSize(new Dimension(200, 30));
            oyuncuAlanlari.add(oyuncuAlani);
            oyuncuPanel.add(oyuncuAlani);
            oyuncuPanel.revalidate();
        });

        baslatButton.addActionListener(e -> {
            List<String> oyuncular = new ArrayList<>();
            for (JTextField tf : oyuncuAlanlari) {
                String isim = tf.getText().trim();
                if (!isim.isEmpty()) oyuncular.add(isim);
            }
            if (oyuncular.size() >= 2 && oyuncular.size() % 2 == 0) {
                dispose();
                OyunEkrani oyun = new OyunEkrani(oyuncular);
                oyun.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "En az 2 ve çift sayıda oyuncu girilmelidir.");
            }
        });

        add(panel);
    }
} 
