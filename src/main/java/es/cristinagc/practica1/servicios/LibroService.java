package es.cristinagc.practica1.servicios;

/**
 * @author Cristina Gómez Campos
 */

import es.cristinagc.practica1.entidades.Genero;
import es.cristinagc.practica1.entidades.Idioma;
import es.cristinagc.practica1.entidades.Libro;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class LibroService {

    private List<Libro> repositorio = new ArrayList<>();

    public Libro add(Libro l) {
        repositorio.add(l);
        return l;
    }

    public List<Libro> findAll() {

        return repositorio;
    }

    public Libro findById(long id) {
        Libro result = null;
        boolean encontrado = false;
        int i = 0;
        while (!encontrado && i < repositorio.size()) {
            if (repositorio.get(i).getId() == id) {
                encontrado = true;
                result = repositorio.get(i);
            } else {
                i++;
            }
        }
        return result;
    }

    public Libro edit(Libro l) {
        boolean encontrado = false;
        int i = 0;
        while (!encontrado && i < repositorio.size()) {
            if (repositorio.get(i).getId() == l.getId()) {
                encontrado = true;
                repositorio.remove(i);
                repositorio.add(i, l);
            } else {
                i++;
            }
        }
        if (!encontrado)
            repositorio.add(l);

        return l;
    }


    public void delete(Libro l) {
        repositorio.remove(l);
    }



    @PostConstruct
    public void init2(){
        repositorio.addAll(
                Arrays.asList(
                        Libro.builder()
                                .id(1)
                                .titulo("Drácula")
                                .autor("Bram Stoker")
                                .anioPublicacion("1897")
                                .anioEdicion("2019")
                                .editorial("Debolsillo")
                                .idioma(Idioma.ESPAÑOL)
                                .genero(Genero.GÓTICO)
                                .disponible(true)
                                .cantidad("2")
                                .observaciones("").build(),
                        Libro.builder()
                                .id(2)
                                .titulo("El resplandor")
                                .autor("Stephen King")
                                .anioPublicacion("1977")
                                .anioEdicion("2006")
                                .editorial("Burlington")
                                .idioma(Idioma.ESPAÑOL)
                                .genero(Genero.TERROR)
                                .disponible(false)
                                .cantidad("1")
                                .observaciones("El ejemplar está dañado").build(),
                        Libro.builder()
                                .id(3)
                                .titulo("La sombra del viento")
                                .autor("Carlos Ruiz Zafón")
                                .anioPublicacion("2001")
                                .anioEdicion("2002")
                                .editorial("Planeta")
                                .idioma(Idioma.ESPAÑOL)
                                .genero(Genero.MISTERIO)
                                .disponible(true)
                                .cantidad("4")
                                .observaciones("").build()
                )
        );
    }



}
