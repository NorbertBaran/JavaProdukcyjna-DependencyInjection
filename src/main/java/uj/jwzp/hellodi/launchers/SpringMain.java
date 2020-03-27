package uj.jwzp.hellodi.launchers;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import uj.jwzp.hellodi.model.Movie;
import uj.jwzp.hellodi.logic.MovieLister;
import uj.jwzp.hellodi.logic.savers.MovieSaver;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class SpringMain {

    public static void main(String[] args) throws IOException {
        final ApplicationContext ctx = new AnnotationConfigApplicationContext(
            "uj.jwzp2019.hellodi.logic", "uj.jwzp2019.hellodi.logic.savers");
        MovieSaver saver = ctx.getBean(MovieSaver.class);
        MovieLister lister = (MovieLister) ctx.getBean("movieLister");

        List<Movie> movies = lister.moviesDirectedBy("Lucas").stream()
            .peek(m -> System.out.println(m.toString()))
            .collect(Collectors.toList());
        saver.save(movies, "saved.xml");
    }
}
