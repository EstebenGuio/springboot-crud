package com.esteben.datajpa.controllers;


import com.esteben.datajpa.models.entity.Client;
import com.esteben.datajpa.models.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.*;
import java.util.Map;

@Controller
@SessionAttributes("client")
public class ClientController {

    @Autowired
    private IClienteService clientService;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String getClients(Model m) {
        m.addAttribute("title", "Listado de clientes");
        m.addAttribute("clients", clientService.findAll());
        return "clients/list";
    }

    @GetMapping(value = "/form")
    public String create(Map<String, Object> m) {
        Client c = new Client();
        m.put("title", "client's form");
        m.put("client", c);
        return "clients/create";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@Valid Client c, BindingResult br, Model m, SessionStatus st) {
        //If cliet object has errors
        if (br.hasErrors()) {
            m.addAttribute("title", "client's form");
            return "clients/create";
        }

        clientService.save(c);
        st.setComplete();

        return "redirect:home";
    }

    @RequestMapping(value = "/form/{cid}")
    public String edit(@PathVariable(value = "cid") Long cid, Model m) {
        Client c = null;
        if (cid < 1) {
            return "redirect:/home";
        }
        c = clientService.findById(cid);
        m.addAttribute("title", "Edit client");
        m.addAttribute("client", c);
        return "clients/create";
    }

    @RequestMapping(value = "/remove/{cid}")
    public String remove(@PathVariable Long cid) {
        if (cid > 0) {
            clientService.delete(cid);
        }
        return "redirect:/home";
    }
}