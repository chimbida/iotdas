package br.senac.tcs.iotdas.ui.controller;

import br.senac.tcs.iotdas.ui.domain.Campo;
import br.senac.tcs.iotdas.ui.domain.Canal;
import br.senac.tcs.iotdas.ui.service.UsuarioServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.logging.Logger;

/**
 * Created by Pablo on 16/05/2017.
 */

@Controller()
@EnableAutoConfiguration
public class CampoController {

    protected Logger logger = Logger.getLogger(HomeController.class.getName());

    @Autowired
    protected UsuarioServiceClient usuarioServiceClient;

    public CampoController(UsuarioServiceClient usuarioServiceClient) {
        this.usuarioServiceClient = usuarioServiceClient;
    }

    @GetMapping("/campo")
    public ModelAndView campo(Model model) {
        logger.info("campo() Chamada!");

        ModelAndView mv = new ModelAndView("campo/campo");
        mv.addObject("campo", usuarioServiceClient.campoList());

        return mv;
    }

    @GetMapping("/campo/novo/{id}")
    public ModelAndView campoNova(@PathVariable("id") String id, Model model) {
        logger.info("campoNovo() Chamada!");

        ModelAndView mv = new ModelAndView("campo/form");

        mv.addObject("campo", new Campo());
        mv.addObject("canal", usuarioServiceClient.canalById(id));
        mv.addObject("pessoa", usuarioServiceClient.pessoaById(id));

        return mv;
    }

    @GetMapping("/campo/info/{id}")
    public ModelAndView infoCampo(@PathVariable("id") String id) {
        ModelAndView mv = new ModelAndView("campo/info");
        mv.addObject("campo", usuarioServiceClient.campoById(id));
        return mv;
    }

    @GetMapping("/campo/{id}/{idCanal}")
    public ModelAndView editarCampo(@PathVariable("id") String id, @PathVariable("idCanal") String idCanal) {
        ModelAndView mv = new ModelAndView("campo/form");
        mv.addObject("campo", usuarioServiceClient.campoById(id));
        mv.addObject("canal", usuarioServiceClient.canalById(idCanal));
        return mv;
    }

    @PostMapping("/campo/{id}")
    public String campoEnvia(@PathVariable("id") String id, @Valid Campo campo, BindingResult result, Model model) {

        logger.info("campoEnvia() chamado -> Nome: " + campo.getNome() + " \n"
            + " PESSOA: " + campo.toString() + " \n"
            + "\n RESULT-PESSOA: " + result.toString() + " \n"
            + "\n MODEL: " + model.toString() + " \n");


        if (result.hasErrors()) {
            logger.warning(result.toString());
            return "campo/form";
        }

        Canal canal = usuarioServiceClient.canalById(id);
        campo.setCanal(canal);
        usuarioServiceClient.campoNovo(campo);

        return "redirect:/canal/{id}";
    }

    @GetMapping("/campo/delete/{campoId}")
    public String deletaCampo(@PathVariable("campoId") String campoId) {
        Campo campo = usuarioServiceClient.campoById(campoId);
        Canal canal = usuarioServiceClient.canalById(campo.getCanal().getId().toString());
        usuarioServiceClient.campoDelete(campoId);
        return "redirect:/canal/" + canal.getId();
    }

    @GetMapping("/campo/guid/{campoId}")
    public String novoGuidCampo(@PathVariable("campoId") String campoId) {
        Campo campo = usuarioServiceClient.campoById(campoId);
        usuarioServiceClient.campoNovoGuid(campo);

        return "redirect:/campo/info/{campoId}";
    }

}
