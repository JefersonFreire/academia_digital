package me.dio.academia.digital.controller;

import me.dio.academia.digital.entity.AvaliacaoFisica;
import me.dio.academia.digital.entity.form.AvaliacaoFisicaForm;
import me.dio.academia.digital.entity.form.AvaliacaoFisicaUpdateForm;
import me.dio.academia.digital.service.impl.AvaliacaoFisicaServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoFisicaController {

    private final AvaliacaoFisicaServiceImpl avaliacao;

    public AvaliacaoFisicaController (AvaliacaoFisicaServiceImpl avaliacao){
        this.avaliacao = avaliacao;
    }

    @PostMapping
    public AvaliacaoFisica create(@RequestBody AvaliacaoFisicaForm form){
        return avaliacao.create(form);
    }

    @GetMapping("/{id}")
    public AvaliacaoFisica getId(@PathVariable(value = "id") long id){
        return avaliacao.getId(id);
    }

    @GetMapping
    public List<AvaliacaoFisica> getAll(){
        return avaliacao.getAll();
    }

    @PutMapping("/{id}")
    public AvaliacaoFisica update(@PathVariable(value = "id") long id, @RequestBody AvaliacaoFisicaUpdateForm form){
        return avaliacao.update(id, form);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(value = "id") long id){
        avaliacao.delete(id);
    }
}
