package View;

import javax.swing.*;
import java.awt.*;

public abstract class TelaBase extends JFrame {

    protected static final Font FONTE_TITULO = new Font("SansSerif", Font.BOLD, 26);
    protected static final Font FONTE_TEXTO = new Font("SansSerif", Font.PLAIN, 16);
    protected static final Font FONTE_BOTAO = new Font("SansSerif", Font.PLAIN, 16);

    public TelaBase(String titulo) {
        super(titulo);
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        montarConteudo();
    }

    protected abstract void montarConteudo();
}
