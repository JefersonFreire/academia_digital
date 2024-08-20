package me.dio.academia.digital.controller;

import jakarta.validation.Valid;
import me.dio.academia.digital.entity.Aluno;
import me.dio.academia.digital.entity.AvaliacaoFisica;
import me.dio.academia.digital.entity.form.AlunoForm;
import me.dio.academia.digital.entity.form.AlunoUpdateForm;
import me.dio.academia.digital.service.impl.AlunoServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

   private final  AlunoServiceImpl alunoService;

   public AlunoController(AlunoServiceImpl alunoService) {
       this.alunoService = alunoService;
   }

   @GetMapping
    public List<Aluno> getAll(@RequestParam(value = "dataNascimento", required = false)
                                 String dataNascimento) {
       return alunoService.getAll(dataNascimento);
   }

   @GetMapping("/{id}")
   public Aluno getId(@PathVariable(value = "id") Long id) {
       return alunoService.getId(id);
   }


   @GetMapping("/avaliacoes/{id}")
    public List<AvaliacaoFisica> getAllAvaliacaoFisicaId(@PathVariable Long id) {
       return alunoService.getAllAvaliacaoFisicaId(id);
   }

   @PostMapping
    public Aluno create(@Valid @RequestBody AlunoForm form) {
       return  alunoService.create(form);
   }

   @PutMapping("/{id}")
    public Aluno atualizarAluno(@PathVariable Long id, @RequestBody AlunoUpdateForm formUpdate) {
       return alunoService.atualizarAluno(id, formUpdate);
   }

   @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
       alunoService.delete(id);
       return ResponseEntity.noContent().build();
   }

}
