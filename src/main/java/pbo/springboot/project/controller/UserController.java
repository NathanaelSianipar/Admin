package pbo.springboot.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import pbo.springboot.project.repository.ProgramRepository;
import pbo.springboot.project.repository.GaleriRepository;
import pbo.springboot.project.repository.LulusanRepository;

@Controller
public class UserController {

    @Autowired
    private ProgramRepository programRepository;

    @Autowired
    private GaleriRepository galeriRepository;

    @Autowired
    private LulusanRepository lulusanRepository;


    @GetMapping("/")
    public String home(Model model){

        // mengambil data program
        model.addAttribute(
                "program",
                programRepository.findAll()
        );

        // mengambil data galeri
        model.addAttribute(
                "galeri",
                galeriRepository.findAll()
        );

        // mengambil data lulusan
        model.addAttribute(
                "lulusan",
                lulusanRepository.findAll()
        );

        // mengambil data tentang kami


        return "user/index";
    }
}