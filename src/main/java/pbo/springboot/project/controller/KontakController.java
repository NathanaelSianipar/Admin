package pbo.springboot.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import pbo.springboot.project.model.Kontak;
import pbo.springboot.project.repository.KontakRepository;

import java.util.Optional;

@Controller
@RequestMapping("/kontak")
public class KontakController {

    @Autowired
    private KontakRepository kontakRepository;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("kontakList", kontakRepository.findAll());
        return "kontak/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("kontak", new Kontak());
        return "kontak/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Kontak kontak) {
        kontakRepository.save(kontak);
        return "redirect:/kontak";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Optional<Kontak> kontak = kontakRepository.findById(id);
        if (kontak.isEmpty()) {
            return "redirect:/kontak";
        }
        model.addAttribute("kontak", kontak.get());
        return "kontak/edit";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        kontakRepository.deleteById(id);
        return "redirect:/kontak";
    }
}

