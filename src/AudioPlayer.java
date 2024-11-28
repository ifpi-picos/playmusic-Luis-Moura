import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import dominio.Musica;

public class AudioPlayer {
  public Clip audioClip;
  public boolean isPlaying = false;
  private List<Musica> playList;
  private int IdMusicaAtual;

  public AudioPlayer(List<Musica> playList) {
    this.playList = playList;
  }

  public void setPlayList(List<Musica> playList) {
    this.playList = playList;
  }

  public List<Musica> getPlayList() {
    return this.playList;
  }

  public int getIdMusicaAtual() {
    return this.IdMusicaAtual;
  }

  public void loadAudio(String filePath) {
    System.out.println("loadAudio: " + filePath);
    try {
      // Carrega o arquivo de áudio
      File audioFile = new File(filePath);
      AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

      // Configura o Clip
      AudioFormat format = audioStream.getFormat();
      DataLine.Info info = new DataLine.Info(Clip.class, format);
      audioClip = (Clip) AudioSystem.getLine(info);

      audioClip.open(audioStream);
    } catch (UnsupportedAudioFileException e) {
      System.out.println("O formato do arquivo de áudio não é suportado.");
      e.printStackTrace();
    } catch (LineUnavailableException e) {
      System.out.println("A linha de áudio não está disponível.");
      e.printStackTrace();
    } catch (IOException e) {
      System.out.println("Erro ao ler o arquivo de áudio.");
      e.printStackTrace();
    }
  }

  public void playAudio() {
    System.out.println("playAudio");
    if (audioClip != null && !isPlaying) {
      audioClip.setFramePosition(0); // Reinicia o áudio do começo
      System.out.println("playAudio start");
      audioClip.start();
      isPlaying = true;
    }
  }

  public void stopAudio() {
    System.out.println("stopAudio");
    if (audioClip != null && isPlaying) {
      System.out.println("stopAudio stop");
      audioClip.stop();
      isPlaying = false;
    }
  }

  public void playNext() {
    if (playList != null && !playList.isEmpty()) {
      stopAudio();

      if (IdMusicaAtual < playList.size() - 1) {
        IdMusicaAtual = (IdMusicaAtual + 1);
      }

      // System.out.println(IdMusicaAtual);
      // System.out.println(playList.size());
      // System.out.println(playList.get(IdMusicaAtual));

      loadAudio(playList.get(IdMusicaAtual).getArquivoAudio());
      playAudio();
    }
  }

  public void playPrevious() {
    if (playList != null && !playList.isEmpty()) {
      stopAudio();

      if (IdMusicaAtual > 0) {
        IdMusicaAtual = (IdMusicaAtual - 1);
      }

      // System.out.println(IdMusicaAtual);
      // System.out.println(playList.size());
      // System.out.println(playList.get(IdMusicaAtual));
      
      loadAudio(playList.get(IdMusicaAtual).getArquivoAudio());
      playAudio();
    }
  }
}
