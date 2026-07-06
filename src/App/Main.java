package App;

import Util.Musica;
import View.TelaMenu;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Musica.tocarEmLoop("C:\\Users\\yaghi\\IdeaProjects\\DebugDoMilhao\\Musicas\\049_jnSujfSu.wav");
        Musica.ajustarVolume(0.65f);

        SwingUtilities.invokeLater(() -> new TelaMenu().setVisible(true));
    }
}
