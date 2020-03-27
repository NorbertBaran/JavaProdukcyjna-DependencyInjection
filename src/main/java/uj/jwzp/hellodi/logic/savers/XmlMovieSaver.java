package uj.jwzp.hellodi.logic.savers;

import org.springframework.stereotype.Component;
import uj.jwzp.hellodi.model.Movie;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

@Component
public class XmlMovieSaver implements MovieSaver {

    @Override
    public void save(List<Movie> movies, String fileName) throws IOException {
        Writer writer = new FileWriter(fileName);
        writer.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n").append("<movies>\n");
        for (Movie movie: movies) {
            writer.append(movieEntry(movie));
        }
        writer.append("</movies>\n");
        writer.close();
    }

    private String movieEntry(Movie movie) {
        StringBuilder result = new StringBuilder("  <movie>\n");
        result
            .append("    <title>" + movie.getTitle() + "</title>\n")
            .append("    <director>" + movie.getDirector() + "</director>\n")
            .append("    <year>" + movie.getYear() + "</year>\n")
            .append("  </movie>\n");
        return result.toString();
    }
}
