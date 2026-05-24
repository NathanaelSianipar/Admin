package pbo.springboot.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    // Jalur utama yang menampilkan Dashboard Admin
    @GetMapping("/")
    public String dashboard(){
        return "dashboard";
    }

    // Menyamakan rute /index agar memanggil file template yang sama persis
    @GetMapping("/index")
    public String index(){
        return "dashboard";
    }
}