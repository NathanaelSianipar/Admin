package pbo.springboot.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pbo.springboot.project.model.Galeri;
import pbo.springboot.project.repository.GaleriRepository;

import java.io.IOException;
import java.nio.file.Paths;
import java.io.File;

@Controller
@RequestMapping("/galeri")
public class GaleriController {

    @Autowired
    private GaleriRepository galeriRepository;

    //

    @GetMapping
    public String index(Model model) {

        model.addAttribute("galeri",
                galeriRepository.findAll());

        return "galeri/index";
    }

    @GetMapping("/create")
    public String create(Model model) {

        model.addAttribute("galeri",
                new Galeri());

        return "galeri/create";
    }

@PostMapping("/save")
public String save(
        @ModelAttribute Galeri galeri,
        @RequestParam("file") MultipartFile file
) throws IOException {

    if (!file.isEmpty()) {

        String fileName =
                System.currentTimeMillis()
                + "_"
                + file.getOriginalFilename();

        String uploadDir =
                System.getProperty("user.dir")
                + "/src/main/resources/static/uploads/";

        File dir = new File(uploadDir);

        if (!dir.exists()) {
            dir.mkdirs();
        }

        File destination =
                new File(dir, fileName);

        file.transferTo(destination);

        galeri.setGambar(fileName);

    } else {

        if (galeri.getId() != null) {

            Galeri dataLama =
                    galeriRepository.findById(galeri.getId())
                            .orElse(null);

            if (dataLama != null) {
                galeri.setGambar(
                        dataLama.getGambar()
                );
            }
        }
    }

    galeriRepository.save(galeri);

    return "redirect:/galeri";
}

    @GetMapping("/edit/{id}")
    public String edit(
            @PathVariable Long id,
            Model model) {

        Galeri galeri = galeriRepository.findById(id).orElse(null);

        model.addAttribute("galeri", galeri);

        return "galeri/edit";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {

        galeriRepository.deleteById(id);

        return "redirect:/galeri";
    }

    // ================= USER =================

    @GetMapping("/user")
    public String userGaleri(Model model) {

        model.addAttribute("galeri",
                galeriRepository.findAll());

        return "galeri/user";
    }
}