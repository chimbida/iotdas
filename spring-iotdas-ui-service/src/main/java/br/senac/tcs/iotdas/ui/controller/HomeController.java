package br.senac.tcs.iotdas.ui.controller;

import br.senac.tcs.iotdas.ui.service.UsuarioServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by chimbida on 04/05/2017.
 */


@Controller()
@EnableAutoConfiguration
public class HomeController {

    @Autowired
    protected UsuarioServiceClient usuarioServiceClient;

    @RequestMapping("/")
    public ModelAndView home(Model model) {
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }

//    @RequestMapping("/login")
//    public ModelAndView login(Model model) {
//        ModelAndView mv = new ModelAndView("login");
//        return mv;
//    }

}
