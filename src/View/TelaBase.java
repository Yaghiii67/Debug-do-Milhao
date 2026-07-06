//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package View;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public abstract class TelaBase extends JFrame {
    protected static final Font FONTE_TITULO = new Font("SansSerif", 1, 26);
    protected static final Font FONTE_TEXTO = new Font("SansSerif", 0, 16);
    protected static final Font FONTE_BOTAO = new Font("SansSerif", 0, 16);

    public TelaBase(String titulo) {
        super(titulo);
        this.setSize(600, 450);
        this.setDefaultCloseOperation(2);
        this.setLocationRelativeTo((Component)null);
        this.setResizable(false);
        ImageIcon imagem = new ImageIcon(this.getClass().getResource("/imagens/FUNDO.png"));
        JLabel fundo = new JLabel(imagem);
        fundo.setLayout(new BorderLayout());
        this.setContentPane(fundo);
        this.montarConteudo();
    }

    protected abstract void montarConteudo();
}
