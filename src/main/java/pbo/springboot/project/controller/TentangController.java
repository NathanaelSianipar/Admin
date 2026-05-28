package pbo.springboot.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import pbo.springboot.project.model.Tentang;
import pbo.springboot.project.repository.TentangRepository;

@Controller
@RequestMapping("/tentang")
public class TentangController {

    @Autowired
    private TentangRepository tentangRepository;

    // ================= ADMIN =================

    @GetMapping
    public String index(Model model) {

        model.addAttribute(
                "tentangList",
                tentangRepository.findAll()
        );

        return "tentang/index";
    }

    @GetMapping("/create")
    public String create(Model model) {

        model.addAttribute(
                "tentang",
                new Tentang()
        );

        return "tentang/create";
    }

    @PostMapping("/save")
    public String save(
            @ModelAttribute Tentang tentang
    ) {

        tentangRepository.save(tentang);

        return "redirect:/tentang";
    }

    @GetMapping("/edit/{id}")
    public String edit(
            @PathVariable Long id,
            Model model
    ) {

        Tentang tentang =
                tentangRepository.findById(id)
                        .orElse(null);

        model.addAttribute(
                "tentang",
                tentang
        );

        return "tentang/edit";
    }

    @GetMapping("/delete/{id}")
    public String delete(
            @PathVariable Long id
    ) {

        tentangRepository.deleteById(id);

        return "redirect:/tentang";
    }

    // ================= USER =================

    @GetMapping("/user")
    public String user(Model model) {

        model.addAttribute(
                "tentangList",
                tentangRepository.findAll()
        );

        return "tentang/user";
    }
}