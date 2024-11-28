import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class MusicPlayerUI {
  private AudioPlayer player;

  public MusicPlayerUI(AudioPlayer player) {
    this.player = player;
  }

  public void showUI(String musicName, String musicFilePath) {
    player.loadAudio(musicFilePath);

    // Cria o botão Play/Stop e configura sua ação
    JButton playStopButton = new JButton("Play");
    playStopButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (!player.isPlaying) {
          player.playAudio();
          playStopButton.setText("Stop");
        } else {
          player.stopAudio();
          playStopButton.setText("Play");
        }
      }
    });

    // Cria o botão Next e configura sua ação
    JButton nextButton = new JButton("Next");
    nextButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        player.playNext();
        // atualizarInterface();
      }
    });

    // Cria o botão Previous e configura sua ação
    JButton previousButton = new JButton("Previous");
    previousButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        player.playPrevious();
        // atualizarInterface();
      }
    });

    ImageIcon icon = new ImageIcon("./assets/music.png");
    // Exibe um JOptionPane com o botão Play/Stop
    JOptionPane.showOptionDialog(
        null,
        musicName,
        "PlayMusic",
        JOptionPane.DEFAULT_OPTION,
        JOptionPane.PLAIN_MESSAGE,
        icon,
        new Object[] { previousButton, playStopButton, nextButton }, playStopButton);

    // Fecha o clip de áudio ao encerrar o programa
    if (player.audioClip != null) {
      player.audioClip.close();
    }
  }
  
  // private void atualizarInterface() {
  //   String musicName = player.getPlayList().get(player.getIdMusicaAtual()).getNome();
  //   String musicFilePath = player.getPlayList().get(player.getIdMusicaAtual()).getArquivoAudio();

  //   showUI(musicName, musicFilePath);
  // }
}
