package Util;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Musica {

    private static Clip clip;

    public static void tocarEmLoop(String caminhoArquivo) {
        try {
            File arquivo = new File(caminhoArquivo);

            if (!arquivo.exists()) {
                System.err.println("Arquivo de música não encontrado: " + caminhoArquivo);
                return;
            }

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(arquivo);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();

        } catch (UnsupportedAudioFileException e) {
            System.err.println("Formato de áudio não suportado (use .wav): " + e.getMessage());
        } catch (LineUnavailableException e) {
            System.err.println("Linha de áudio indisponível: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo de música: " + e.getMessage());
        }
    }

    public static void ajustarVolume(float volume) {
        if (clip == null || !clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
            return;
        }
        FloatControl controle = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        float minimo = controle.getMinimum();
        float maximo = controle.getMaximum();
        float valor = minimo + (maximo - minimo) * Math.max(0f, Math.min(1f, volume));
        controle.setValue(valor);
    }

    public static void pausar() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

    public static void retomar() {
        if (clip != null && !clip.isRunning()) {
            clip.start();
        }
    }

    public static void parar() {
        if (clip != null) {
            clip.stop();
            clip.close();
        }
    }
}