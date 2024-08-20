package me.dio.academia.digital.controller;

import jakarta.validation.Valid;
import me.dio.academia.digital.entity.Matricula;
import me.dio.academia.digital.entity.form.MatriculaForm;
import me.dio.academia.digital.service.impl.MatriculaServiceImpl;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matriculas")
public class MatriculaController {

    private final MatriculaServiceImpl service;

    public MatriculaController(MatriculaServiceImpl service) {
        this.service = service;
    }

    @PostMapping
    public Matricula create(@Valid @RequestBody MatriculaForm form) {
        return service.create(form);
    }

    @GetMapping
    public List<Matricula> getAll(@RequestParam(value = "bairro", required = false)String bairro) {
        return service.getAll(bairro);
    }

    @GetMapping("/{id}")
    public Matricula get(@PathVariable Long id) {
        return service.get(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
