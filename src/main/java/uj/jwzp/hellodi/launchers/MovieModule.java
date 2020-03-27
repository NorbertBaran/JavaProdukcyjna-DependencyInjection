package uj.jwzp.hellodi.launchers;

import dagger.Module;
import dagger.Provides;
import uj.jwzp.hellodi.logic.savers.MovieSaver;
import uj.jwzp.hellodi.logic.savers.XmlMovieSaver;
import uj.jwzp.hellodi.logic.CSVMovieFinder;
import uj.jwzp.hellodi.logic.MovieFinder;
import uj.jwzp.hellodi.logic.MovieLister;

import javax.inject.Singleton;

@Module
public class MovieModule {

    @Provides
    @Singleton
    public MovieFinder provideMovieFinder() {
        return new CSVMovieFinder();
    }

    @Provides
    @Singleton
    public MovieLister provideMovieLister(MovieFinder movieFinder) {
        return new MovieLister(movieFinder);
    }

    @Provides
    @Singleton
    public MovieSaver provideMovieSaver() {
        return new XmlMovieSaver();
    }

}
