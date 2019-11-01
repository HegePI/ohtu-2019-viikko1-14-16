package ohtuesimerkki;

import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

public class StatisticsTest {
 
    Reader readerStub = new Reader() {
 
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();
 
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
 
            return players;
        }
    };
 
    Statistics stats;

    @Before
    public void setUp(){
        // luodaan Statistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }
    
    @Test
    public void haeNimenPerusteella() {
        String haettava = "Yzerman";
        Player tulos = stats.search(haettava);
        assertEquals(haettava, tulos.getName());
    }
    
    @Test
    public void eiTulostaNimenPerusteella() {
        String haettava = "Yz3rman";
        Player tulos = stats.search(haettava);
        assertEquals(null, tulos);
    }
    
    @Test
    public void haeJoukkueenPelaajat() {
        String joukkue = "EDM";
        List<Player> tulos = stats.team(joukkue);
        assertEquals(3, tulos.size());
    }
    
    @Test
    public void haePisteidenTekijat() {
        int maara = 4;
        List<Player> tulos = stats.topScorers(maara);
        assertEquals(4, tulos.size());
    }
    
    @Test
    public void negatiivinenMaarapalauttaaNolla() {
        int maara = -1;
        List<Player> tulos = stats.topScorers(maara);
        assertEquals(0, tulos.size());
    }
}

