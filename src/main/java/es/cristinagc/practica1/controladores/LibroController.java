package es.cristinagc.practica1.controladores;

/**
 * @author Cristina Gómez Campos
 */

import es.cristinagc.practica1.entidades.Libro;
import es.cristinagc.practica1.servicios.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Comparator;

@Controller
public class LibroController {

    @Autowired
    private LibroService servicio;

    @GetMapping("/")
    public String indice(){
        return "index";
    }

    @GetMapping("libro/list")
    public String listado(Model model){
        model.addAttribute("listaLibros", servicio.findAll());
        return "list";
    }

    @GetMapping("/libro/new")
    public String nuevoLibroForm(Model model){
        model.addAttribute("libroForm", new Libro());
        return "form";
    }

    @PostMapping("/libro/new/submit")
    public String nuevoLibroSubmit(@Valid @ModelAttribute("libroForm") Libro nuevoLibro, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "form";

        } else {
            nuevoLibro.setId(servicio.findAll().stream().count() + 1);
           servicio.add(nuevoLibro);
           return "redirect:/libro/list";

       }
    }

    @GetMapping("/libro/edit/{id}")
    public String editarLibroForm(@PathVariable long id, Model model){
        Libro libro = servicio.findById(id);
        if (libro != null){
            model.addAttribute("libroForm", libro);
            return "form";
        }else {
            return "redirect:/libro/new";
        }
    }

    @PostMapping("/libro/edit/submit")
    public String editarLibroSubmit(@Valid @ModelAttribute("libroForm") Libro libro, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "form";
        }else {
            servicio.edit(libro);
            return "redirect:/libro/list";
        }
    }
    @GetMapping("/libro/delete/{id}")
    public String deleteLibro(@PathVariable long id, Model model) {

        Libro libro = servicio.findById(id);
        if (libro != null) {
            servicio.delete(libro);
            return "redirect:/libro/list";
        } else {
            return "form";
        }
    }

    @GetMapping({"/privacidad","/libro/list/privacidad" , "/libro/new/privacidad"})
    public String privacidad(){
        return "privacidad";
    }

    @GetMapping({"/aviso","/libro/list/aviso" , "/libro/new/aviso"})
    public String aviso(){
        return "aviso";
    }
}
