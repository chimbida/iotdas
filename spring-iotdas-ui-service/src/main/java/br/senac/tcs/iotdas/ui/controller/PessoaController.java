package br.senac.tcs.iotdas.ui.controller;

import br.senac.tcs.iotdas.ui.domain.Campo;
import br.senac.tcs.iotdas.ui.domain.Canal;
import br.senac.tcs.iotdas.ui.domain.Pessoa;
import br.senac.tcs.iotdas.ui.service.UsuarioServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by chimbida on 05/05/2017.
 */


@Controller()
@EnableAutoConfiguration
public class PessoaController {

    protected Logger logger = Logger.getLogger(PessoaController.class.getName());

    @Autowired
    protected UsuarioServiceClient usuarioServiceClient;

    public PessoaController(UsuarioServiceClient usuarioServiceClient) {
        this.usuarioServiceClient = usuarioServiceClient;
    }

    @GetMapping("/pessoa")
    public ModelAndView pessoa(Model model) {
        logger.info("pessoa() Chamada!");

        ModelAndView mv = new ModelAndView("pessoa/pessoa");

        mv.addObject("pessoa", usuarioServiceClient.pessoaList());
        mv.addObject("tipo", usuarioServiceClient.tipoList());

        return mv;
    }

    @GetMapping("/pessoa/nova")
    public ModelAndView pessoaNova(Model model) {
        logger.info("pessoaNova() Chamada!");

        ModelAndView mv = new ModelAndView("pessoa/form");

        mv.addObject("pessoa", new Pessoa());
        mv.addObject("tipo", usuarioServiceClient.tipoList());

        return mv;
    }

    @GetMapping("/pessoa/{id}")
    public ModelAndView editarPessoa(@PathVariable("id") String id) {
        ModelAndView mv = new ModelAndView("pessoa/form");
        mv.addObject("pessoa", usuarioServiceClient.pessoaById(id));
        mv.addObject("tipo", usuarioServiceClient.tipoList());
        return mv;
    }

    @GetMapping("/pessoa/perfil/{id}")
    public ModelAndView perfilPessoa(@PathVariable("id") String id) {
        ModelAndView mv = new ModelAndView("pessoa/perfil");
        mv.addObject("pessoa", usuarioServiceClient.pessoaById(id));
        mv.addObject("tipo", usuarioServiceClient.tipoList());
        return mv;
    }

    @GetMapping("/pessoa/info/{id}")
    public ModelAndView infoPessoa(@PathVariable("id") String id) {
        ModelAndView mv = new ModelAndView("pessoa/info");
        mv.addObject("pessoa", usuarioServiceClient.pessoaById(id));
        mv.addObject("canal", usuarioServiceClient.canalListByPessoa(id));
        mv.addObject("tipo", usuarioServiceClient.tipoList());
        return mv;
    }

    @PostMapping("/pessoa")
    public String pessoaEnvia(@Valid Pessoa pessoa, BindingResult result, Model model) {

        logger.info("pessoaNova() chamado -> Nome: " + pessoa.getNome() + " \n"
            + " PESSOA: " + pessoa.toString() + " \n"
            + "\n RESULT-PESSOA: " + result.toString() + " \n"
            + "\n MODEL: " + model.toString() + " \n");


        if (result.hasErrors()) {
            logger.warning(result.toString());
            return "pessoa/form";
        }


        if (pessoa.getId() != null) {
            Pessoa getPessoa = usuarioServiceClient.pessoaById(pessoa.getId().toString());
            logger.info("data not null - pessoa " + pessoa.getCriado() + " \n");
            logger.info("data not null - getPessoa" + getPessoa.getCriado() + " \n");
            pessoa.setCriado(getPessoa.getCriado());
        }
        if ( pessoa.getAtivo() == null ) {
            pessoa.setAtivo(false);
        }
        usuarioServiceClient.pessoaNova(pessoa);

        return "redirect:pessoa";
    }

    @GetMapping("/pessoa/delete/{pessoaId}")
    public String deletaPessoa(@PathVariable("pessoaId") String pessoaId) {
        List<Canal> canais = usuarioServiceClient.canalListByPessoa(pessoaId);
        if (canais != null) {
            //me gusta too
            for (Canal c: canais) {
                List<Campo> campos = usuarioServiceClient.campoListByCanal(c.getId().toString());
                if (campos != null) {
                    //me gusta
                    for (Campo n: campos) {
                        usuarioServiceClient.campoDelete(n.getId().toString());
                    }
                }
                usuarioServiceClient.canalDelete(c.getId().toString());
            }
        }

        usuarioServiceClient.pessoaDelete(pessoaId);
        return "redirect:/pessoa";
    }

    @GetMapping("/login")
    public ModelAndView login() {
        logger.info("login() Chamada!");

        ModelAndView mv = new ModelAndView("pessoa/login");
        mv.addObject("pessoa", new Pessoa());

        return mv;
    }

    @GetMapping("/pessoa/guid/{pessoaId}")
    public String novoGuidPessoa(@PathVariable("pessoaId") String pessoaId) {
        Pessoa pessoa = usuarioServiceClient.pessoaById(pessoaId);
        usuarioServiceClient.pessoaNovoGuid(pessoa);

        return "redirect:/pessoa/info/{pessoaId}";
    }

}
