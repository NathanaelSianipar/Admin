package pbo.springboot.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import pbo.springboot.project.model.Lulusan;
import pbo.springboot.project.repository.LulusanRepository;

@Controller
@RequestMapping("/lulusan")
public class LulusanController {

    @Autowired
    private LulusanRepository repository;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("data", repository.findAll());
        return "lulusan/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("lulusan", new Lulusan());
        return "lulusan/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Lulusan lulusan) {
        repository.save(lulusan);
        return "redirect:/lulusan";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("lulusan", repository.findById(id).get());
        return "lulusan/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Lulusan lulusan) {
        repository.save(lulusan);
        return "redirect:/lulusan";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        repository.deleteById(id);
        return "redirect:/lulusan";
    }
}