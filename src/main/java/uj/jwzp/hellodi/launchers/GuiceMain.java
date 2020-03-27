package uj.jwzp.hellodi.launchers;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import uj.jwzp.hellodi.logic.savers.MovieSaver;
import uj.jwzp.hellodi.logic.savers.XmlMovieSaver;
import uj.jwzp.hellodi.model.Movie;
import uj.jwzp.hellodi.logic.CSVMovieFinder;
import uj.jwzp.hellodi.logic.MovieFinder;
import uj.jwzp.hellodi.logic.MovieLister;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class GuiceMain {

    public static void main(String[] args) throws IOException {
        Injector injector = Guice.createInjector(new GuiceMovieModule());
        MovieLister lister = injector.getInstance(MovieLister.class);
        MovieSaver saver = injector.getInstance(MovieSaver.class);

        List<Movie> movies = lister.moviesDirectedBy("Lucas").stream()
            .peek(m -> System.out.println(m.toString()))
            .collect(Collectors.toList());
        saver.save(movies, "saved.xml");
    }
}

class GuiceMovieModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(MovieFinder.class).to(CSVMovieFinder.class);
        bind(MovieSaver.class).to(XmlMovieSaver.class);
    }
}
