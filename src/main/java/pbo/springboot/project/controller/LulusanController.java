package pbo.springboot.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import pbo.springboot.project.model.Lulusan;
import pbo.springboot.project.repository.LulusanRepository;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

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
    public String save(
            @ModelAttribute Lulusan lulusan,
            @RequestParam("fotoFile") MultipartFile file) throws IOException {

        if (!file.isEmpty()) {

            String fileName = System.currentTimeMillis()
                    + "_"
                    + file.getOriginalFilename();

            String uploadDir =
        System.getProperty("user.dir")
        + "/src/main/resources/static/uploads/";

            File dir = new File(uploadDir);

            if (!dir.exists()) {
                dir.mkdirs();
            }

            File destination = new File(dir, fileName);

            file.transferTo(destination);

            lulusan.setFoto("/uploads/" + fileName);
        }

        repository.save(lulusan);

        return "redirect:/lulusan";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("lulusan", repository.findById(id).get());
        return "lulusan/edit";
    }

    @PostMapping("/update")
    public String update(
            @ModelAttribute Lulusan lulusan,
            @RequestParam("fotoFile") MultipartFile file) throws IOException {

        if (!file.isEmpty()) {

            String fileName = System.currentTimeMillis()
                    + "_"
                    + file.getOriginalFilename();

            String uploadDir =
        System.getProperty("user.dir")
        + "/src/main/resources/static/uploads/";

            File dir = new File(uploadDir);

            if (!dir.exists()) {
                dir.mkdirs();
            }

            File destination = new File(dir, fileName);

            file.transferTo(destination);

            lulusan.setFoto("/uploads/" + fileName);

        } else {

            Lulusan dataLama = repository.findById(lulusan.getId())
                    .orElse(null);

            if (dataLama != null) {
                lulusan.setFoto(dataLama.getFoto());
            }
        }

        repository.save(lulusan);

        return "redirect:/lulusan";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        repository.deleteById(id);
        return "redirect:/lulusan";
    }
}