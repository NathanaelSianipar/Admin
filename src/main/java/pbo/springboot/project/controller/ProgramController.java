package pbo.springboot.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import pbo.springboot.project.model.Program;
import pbo.springboot.project.repository.ProgramRepository;

@Controller
public class ProgramController {

    @Autowired
    private ProgramRepository programRepository;

    @GetMapping("/program")
    public String index(Model model) {
        // Mengirim data list program ke html
        model.addAttribute("listProgram", programRepository.findAll());
        // Membuka file templates/program/index.html
        return "program/index";
    }
    // 2. Menampilkan Form Tambah Program
    @GetMapping("/program/add")
    public String showAddForm(Model model) {
        // Mengirim objek kosong sebagai wadah input form
        model.addAttribute("program", new Program());
        return "program/create";
    }

    // 3. Memproses Penyimpanan Data ke Database
    @PostMapping("/program/save")
    public String saveProgram(@ModelAttribute("program") Program program) {
        // Otomatis insert data ke tabel MySQL
        programRepository.save(program);
        // Setelah sukses, kembali ke halaman daftar program
        return "redirect:/program";
    }
    // 4. Menampilkan Form Edit berdasarkan ID data yang dipilih
    @GetMapping("/program/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Program program = programRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid program Id:" + id));
        model.addAttribute("program", program);
        return "program/edit";
    }

    // 5. Memproses Perubahan Data (Update)
    @PostMapping("/program/update/{id}")
    public String updateProgram(@PathVariable("id") Long id, @ModelAttribute("program") Program program) {
        program.setId(id);
        programRepository.save(program); // Mengupdate data yang id-nya sama
        return "redirect:/program";
    }

    // 6. Menghapus Data Program
    @GetMapping("/program/delete/{id}")
    public String deleteProgram(@PathVariable("id") Long id) {
        programRepository.deleteById(id);
        return "redirect:/program";
    }
}