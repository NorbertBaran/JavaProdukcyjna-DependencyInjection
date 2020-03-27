package uj.jwzp.hellodi.launchers;

import uj.jwzp.hellodi.logic.savers.MovieSaver;
import uj.jwzp.hellodi.logic.savers.XmlMovieSaver;
import uj.jwzp.hellodi.model.Movie;
import uj.jwzp.hellodi.logic.CSVMovieFinder;
import uj.jwzp.hellodi.logic.MovieFinder;
import uj.jwzp.hellodi.logic.MovieLister;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class ManualMain {

    public static void main(String[] args) throws IOException {
        MovieFinder finder = new CSVMovieFinder();
        MovieLister lister = new MovieLister(finder);
        MovieSaver saver = new XmlMovieSaver();

        List<Movie> movies = lister.moviesDirectedBy("Lucas").stream()
                .peek(m -> System.out.println(m.toString()))
                .collect(Collectors.toList());
        saver.save(movies, "saved.xml");
    }
}
