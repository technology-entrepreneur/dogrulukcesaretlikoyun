package dogrulukcesaret;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.ArrayList;


class OyunEkrani extends JFrame {
    private List<String> oyuncular;
    private Map<String, Integer> skorlar;
    private int turSayisi = 0;
    private int maxTur = 4;
    private JLabel oyuncuLabel, soruLabel;
    private JButton dogrulukBtn, cesaretBtn, kabulBtn, redBtn;
    private String aktifOyuncu;
    private String aktifTip;
    private Random random = new Random();
    private List<String> dogrulukSorulari, cesaretSorulari;
    private Queue<String> siraliOyuncular;

    public OyunEkrani(List<String> oyuncular) {
        this.oyuncular = new ArrayList<>(oyuncular);
        Collections.shuffle(this.oyuncular);
        skorlar = new HashMap<>();
        for (String o : oyuncular) skorlar.put(o, 0);

        dogrulukSorulari = SoruVeritabani.getDogrulukSorulari();
        cesaretSorulari = SoruVeritabani.getCesaretSorulari();

        siraliOyuncular = new LinkedList<>(this.oyuncular);

        setTitle("Oyun Ekranı");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1));

        oyuncuLabel = new JLabel("Sıra: ", JLabel.CENTER);
        soruLabel = new JLabel("Soru: ", JLabel.CENTER);
        dogrulukBtn = new JButton("Doğruluk");
        cesaretBtn = new JButton("Cesaret");
        kabulBtn = new JButton("Kabul Ediyorum");
        redBtn = new JButton("Etmiyorum");

        JPanel secimPanel = new JPanel();
        secimPanel.add(dogrulukBtn);
        secimPanel.add(cesaretBtn);

        JPanel cevapPanel = new JPanel();
        cevapPanel.add(kabulBtn);
        cevapPanel.add(redBtn);

        add(oyuncuLabel);
        add(secimPanel);
        add(soruLabel);
        add(cevapPanel);

        yeniTur();

        dogrulukBtn.addActionListener(e -> secimYap("Doğruluk"));
        cesaretBtn.addActionListener(e -> secimYap("Cesaret"));
        kabulBtn.addActionListener(e -> puanGuncelle(true));
        redBtn.addActionListener(e -> puanGuncelle(false));
    }

    private void yeniTur() {
        if (siraliOyuncular.isEmpty()) {
            turSayisi++;
            if (turSayisi >= maxTur) {
                kontrolEtVeBitir();
                return;
            }
            siraliOyuncular = new LinkedList<>(oyuncular);
        }
        aktifOyuncu = siraliOyuncular.poll();
        oyuncuLabel.setText("Sıra: " + aktifOyuncu);
        soruLabel.setText("Soru: Seçim yapınız.");
        aktifTip = null;
    }

    private void secimYap(String tip) {
        aktifTip = tip;
        String soru = tip.equals("Doğruluk") ? 
            dogrulukSorulari.get(random.nextInt(dogrulukSorulari.size())) :
            cesaretSorulari.get(random.nextInt(cesaretSorulari.size()));
        soruLabel.setText(tip + " - " + soru);
    }

    private void puanGuncelle(boolean kabul) {
        if (aktifTip == null) return;
        int puan = skorlar.get(aktifOyuncu);
        if (kabul) puan += 10;
        else puan -= 5;
        skorlar.put(aktifOyuncu, puan);
        yeniTur();
    }

    private void kontrolEtVeBitir() {
        List<Map.Entry<String, Integer>> siraliSkor = new ArrayList<>(skorlar.entrySet());
        siraliSkor.sort((a, b) -> b.getValue() - a.getValue());

        int birinci = siraliSkor.get(0).getValue();
        boolean eslik = siraliSkor.stream().filter(e -> e.getValue() == birinci).count() > 1;

        if (eslik && maxTur == 4) {
            maxTur += 2;
            JOptionPane.showMessageDialog(this, "Beraberlik var! 2 tur daha oynanacak.");
            siraliOyuncular = new LinkedList<>(oyuncular);
            yeniTur();
        } else if (eslik) {
            JOptionPane.showMessageDialog(this, "Dostluk kazandı! Kimse kaybetmedi.");
            dispose();
        } else {
            StringBuilder sb = new StringBuilder("Sıralama:\n");
            int sira = 1;
            for (Map.Entry<String, Integer> e : siraliSkor) {
                sb.append(sira++).append(") ").append(e.getKey()).append(" - ").append(e.getValue()).append(" puan\n");
            }
            JOptionPane.showMessageDialog(this, sb.toString());
            dispose();
        }
    }
}
