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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

/**
 * Created by chimbida on 15/05/2017.
 */

@Controller
@EnableAutoConfiguration
public class CanalController {

    protected Logger logger = Logger.getLogger(CanalController.class.getName());

    @Autowired
    protected UsuarioServiceClient usuarioServiceClient;

    public CanalController(UsuarioServiceClient usuarioServiceClient) {
        this.usuarioServiceClient = usuarioServiceClient;
    }

    @GetMapping("/canal/{id}")
    public ModelAndView infoCanal(@PathVariable("id") String id) {
        ModelAndView mv = new ModelAndView("canal/info");
        mv.addObject("canal", usuarioServiceClient.canalById(id));
        mv.addObject("campo", usuarioServiceClient.campoListByCanal(id));
        mv.addObject("json", gerarJsonExemplo(usuarioServiceClient.canalById(id)));
        return mv;
    }

    @GetMapping("/canal/{id}/{idPessoa}")
    public ModelAndView editarCanal(@PathVariable("id") String id, @PathVariable("idPessoa") String idPessoa) {
        ModelAndView mv = new ModelAndView("canal/form");
        mv.addObject("canal", usuarioServiceClient.canalById(id));
        mv.addObject("pessoa", usuarioServiceClient.pessoaById(idPessoa));
        return mv;
    }

    @GetMapping("/canal/novo/{id}")
    public ModelAndView canalNovo(@PathVariable("id") String id, Model model) {
        logger.info("canalNovo() Chamada!");

        ModelAndView mv = new ModelAndView("canal/form");

        mv.addObject("canal", new Canal());
        mv.addObject("pessoa", usuarioServiceClient.pessoaById(id));

        return mv;
    }

    @GetMapping("/canal/delete/{canalId}")
    public String canalPessoa(@PathVariable("canalId") String canalId) {
        Canal getIdPessoa = usuarioServiceClient.canalById(canalId);
        List<Campo> campos = usuarioServiceClient.campoListByCanal(canalId);

        if (campos != null) {
            //me gusta
            for (Campo c: campos) {
                usuarioServiceClient.campoDelete(c.getId().toString());
            }

        }

        usuarioServiceClient.canalDelete(canalId);
        return "redirect:/pessoa/info/" + getIdPessoa.getPessoa().getId().toString();
    }

    @PostMapping("/canal/{id}")
    public String canalEnvia(@PathVariable("id") String id, @Valid Canal canal, BindingResult result, Model model) {

        logger.info("canalNova() chamado -> Nome: " + canal.getNome() + " \n"
            + " CANAL: " + canal.toString() + " \n"
            + "\n RESULT-PESSOA: " + result.toString() + " \n"
            + "\n MODEL: " + model.toString() + " \n");

        if (result.hasErrors()) {
            logger.warning(result.toString());
            return "canal/form";
        }

        Pessoa getPessoa = usuarioServiceClient.pessoaById(id);
        canal.setPessoa(getPessoa);
        usuarioServiceClient.canalNovo(canal);
        return "redirect:/pessoa/info/{id}";
    }

    @GetMapping("/canal/guid/{canalId}")
    public String novoGuidCanal(@PathVariable("canalId") String canalId) {
        Canal canal = usuarioServiceClient.canalById(canalId);
        usuarioServiceClient.canalNovoGuid(canal);

        return "redirect:/canal/{canalId}";
    }

    private String gerarJsonExemplo(Canal canal) {
        String camposJson = "";

        List<Campo> campos = usuarioServiceClient.campoListByCanal(canal.getId().toString());

        if (campos != null) {

            for (Campo c : campos) {
                if (camposJson.equals("")) {
                    camposJson += "          {\n" +
                        "               \"guid\" : \"" + c.getGuid() + "\",\n" +
                        "               \"dados\" : \"" + new Random().nextLong() + "\"\n          }";
                } else {
                    camposJson += ",\n          {\n" +
                        "               \"guid\" : \"" + c.getGuid() + "\",\n" +
                        "               \"dados\" : \"" + new Random().nextLong() + "\"\n          }";
                }
            }

        }

        String json = "{\n" +
                      "     \"guid\" : \"" + canal.getGuid() + "\",\n" +
                      "     \"campos\" :\n     [\n" + camposJson + "\n     ]\n}";

        return json;
    }

}
