package cr.ac.ucr.paraiso.dsw4.renting.data;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import cr.ac.ucr.paraiso.dsw4.renting.domain.Pelicula;


@SpringBootTest
public class PeliculaDataTest {
    @Autowired
    private PeliculaData peliculaData;

    @Test
    @DisplayName ("Given existing movies, when searching by existing title and existing genre, then returns movies")
    @Transactional // Para que no se guarden los cambios en la base de datos
    @Sql(scripts = "/insert_peliculas_con_actores.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD) 
    // Carga datos de prueba antes de ejecutar la prueba
    void givenExistingMovies_withExistingTitleAndExistingGenre_thenReturnsMovies() {
        // Arrange
            String title = "Marriage Story";
            String genre = "Drama";
        // Act
            List<Pelicula> peliculas = peliculaData.findMoviesByTitleOrGenre(title, genre);
        // Assert
            assertNotNull(peliculas);
            assertTrue(!peliculas.isEmpty() );

            String expectedTitle = "Marriage Story";
            String expectedGenre ="Drama";
           assertTrue(peliculas.stream().anyMatch(p -> p.getTitulo().contains(expectedTitle)));
           assertTrue(peliculas.stream().anyMatch(p ->p.getGenero().getNombreGenero().contains(expectedGenre)));


    }
}
