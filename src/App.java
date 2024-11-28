
import java.util.ArrayList;
import java.util.List;

import dominio.Album;
import dominio.Artista;
import dominio.Musica;

public class App {
    public static void main(String[] args) throws Exception {

        Musica musica1 = new Musica("Californication", "Rock", "./assets/Red-Hot-Chili-Peppers-Californication.wav",
                120);
        
        Musica musica2 = new Musica("Otherside", "Rock", "./assets/Red-Hot-Chili-Peppers-Otherside.wav", 120);

        Musica musica3 = new Musica("In The End", "Rock", "./assets/In-The-End-Linkin-Park.wav", 120);

        List<Musica> listaDeMusicas = new ArrayList<>();
        
        Album album1 = new Album("Primeiro album", 2000, listaDeMusicas);
        album1.addMusica(musica1);
        album1.addMusica(musica2);
        album1.addMusica(musica3);

        List<Album> listaDeAlbums = new ArrayList<>();
        Artista redHot = new Artista("Red Hot Chili Peppers", listaDeAlbums);
        redHot.addAlbum(album1);

        System.out.println("Abrindo o PlayMusic");
        redHot.getAlbuns().get(0).getMusicas().get(0).getArquivoAudio();

        // Incia o Player e o Ui
        AudioPlayer player = new AudioPlayer(listaDeMusicas);
        MusicPlayerUI ui = new MusicPlayerUI(player);
        
        String musicName = player.getPlayList().get(player.getIdMusicaAtual()).getNome();
        String musicFilePath = player.getPlayList().get(player.getIdMusicaAtual()).getArquivoAudio();

        ui.showUI(musicName, musicFilePath);
    }
}
