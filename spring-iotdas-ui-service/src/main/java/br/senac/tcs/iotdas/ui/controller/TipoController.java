package br.senac.tcs.iotdas.ui.controller;

import br.senac.tcs.iotdas.ui.domain.Tipo;
import br.senac.tcs.iotdas.ui.service.UsuarioServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.logging.Logger;

/**
 * Created by chimbida on 08/05/2017.
 */

@Controller
@EnableAutoConfiguration
public class TipoController {

    protected Logger logger = Logger.getLogger(HomeController.class.getName());

    @Autowired
    protected UsuarioServiceClient usuarioServiceClient;

    public TipoController(UsuarioServiceClient usuarioServiceClient) {
        this.usuarioServiceClient = usuarioServiceClient;
    }

    @GetMapping("/tipo")
    public ModelAndView tipo(Model model) {
        logger.info("tipoNova() Chamada!");

        ModelAndView mv = new ModelAndView("tipo/tipo");

        mv.addObject("tipo", usuarioServiceClient.tipoList());

        return mv;
    }

    @GetMapping("/tipo/novo")
    public ModelAndView tipoNovo(Model model) {
        logger.info("tipoNovo() Chamada!");

        ModelAndView mv = new ModelAndView("tipo/form");

        mv.addObject("tipo", new Tipo());

        return mv;
    }

    @PostMapping("/tipo")
    public String tipoNova(@Valid Tipo tipo, BindingResult result, Model model) {

        logger.info("tipoNovo() chamado -> Nome: " + tipo.getNome() + " Descrição: " + tipo.getDescricao()
            + "\n RESULT: " + result.toString() + " \n"
            + "\n MODEL: " + model.toString() + " \n");

        if (result.hasErrors()) {
            logger.warning(result.toString());
            return "novatipo";
        }

        usuarioServiceClient.tipoNovo(tipo);

        return "redirect:tipo";
    }

    @GetMapping("/tipo/delete/{tipoId}")
    public String deletaTipo(@PathVariable("tipoId") int tipoId) {

        usuarioServiceClient.tipoDelete(tipoId);
        return "redirect:/tipo";
    }

    @GetMapping("/tipo/{id}")
    public ModelAndView editarTipo(@PathVariable("id") Integer id) {
        ModelAndView mv = new ModelAndView("tipo/form");
        mv.addObject("tipo", usuarioServiceClient.tipoById(id));
        return mv;
    }

}
