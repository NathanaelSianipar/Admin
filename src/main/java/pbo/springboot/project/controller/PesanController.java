package pbo.springboot.project.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pbo.springboot.project.model.Pesan;
import pbo.springboot.project.repository.PesanRepository;

@Controller
public class PesanController {

    @Autowired
    private PesanRepository pesanRepository;

    @GetMapping("/admin/pesan")
    public String pesanList(Model model) {
        var semuaPesan = pesanRepository.findAll();
        long pendingCount = semuaPesan.stream()
                .filter(p -> p.getStatus() == null || !p.getStatus().equals("Dikonfirmasi"))
                .count();

        model.addAttribute("pesanList", semuaPesan);
        model.addAttribute("pendingCount", pendingCount);
        return "pesan/index";
    }

    @GetMapping("/admin/pesan/confirm/{id}")
    public String confirmPesan(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Optional<Pesan> pesan = pesanRepository.findById(id);
        if (pesan.isPresent()) {
            Pesan pesanEntity = pesan.get();
            pesanEntity.setStatus("Dikonfirmasi");
            pesanRepository.save(pesanEntity);
            redirectAttributes.addFlashAttribute("success", "Pesan berhasil dikonfirmasi.");
        }
        return "redirect:/admin/pesan";
    }

    @PostMapping("/pesan/send")
    public String sendPesan(@ModelAttribute Pesan pesan, RedirectAttributes redirectAttributes) {
        pesan.setStatus("Baru");
        pesanRepository.save(pesan);
        redirectAttributes.addFlashAttribute("success", "Pesan berhasil dikirim, admin akan segera menindaklanjuti.");
        return "redirect:/#contact";
    }
}
