package classExercise;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SearchMovieMDB extends MovieMDB{

    public static void main(String[] args) {


        navegarSitio("https://imdb.com");
        verificarLandingPage();
        buscaPelicula();
        listarPeliculas();


    }

}
