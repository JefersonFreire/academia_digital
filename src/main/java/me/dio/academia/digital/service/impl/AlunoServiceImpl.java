package me.dio.academia.digital.service.impl;

import jakarta.transaction.Transactional;
import me.dio.academia.digital.entity.Aluno;
import me.dio.academia.digital.entity.AvaliacaoFisica;
import me.dio.academia.digital.entity.form.AlunoForm;
import me.dio.academia.digital.entity.form.AlunoUpdateForm;
import me.dio.academia.digital.infra.utils.JavaTimeUtils;
import me.dio.academia.digital.repository.AlunoRepository;
import me.dio.academia.digital.repository.AvaliacaoFisicaRepository;
import me.dio.academia.digital.repository.MatriculaRepository;
import me.dio.academia.digital.service.IAlunoService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AlunoServiceImpl implements IAlunoService {

    private final AlunoRepository alunoRepository;
    private final MatriculaRepository matriculaRepository;
    private final AvaliacaoFisicaRepository avaliacaoFisicaRepository;

    public AlunoServiceImpl(AlunoRepository alunoRepository, MatriculaRepository matriculaRepository, AvaliacaoFisicaRepository avaliacaoFisicaRepository) {
        this.alunoRepository = alunoRepository;
        this.matriculaRepository = matriculaRepository;
        this.avaliacaoFisicaRepository = avaliacaoFisicaRepository;
    }

    @Override
    public Aluno create(AlunoForm form) {
        Aluno aluno = new Aluno();
        aluno.setNome(form.getNome());
        aluno.setCpf(form.getCpf());
        aluno.setBairro(form.getBairro());
        aluno.setDataNascimento(form.getDataNascimento());

        return alunoRepository.save(aluno);

    }


    @Override
    public Aluno getId(Long id) {
        return alunoRepository.findById(id).get();
    }

    @Override
    public List<Aluno> getAll(String dataNascimento) {
        if (dataNascimento == null) {
            return alunoRepository.findAll();
        } else {
            LocalDate localDate = LocalDate.parse(dataNascimento, JavaTimeUtils.LOCAL_DATE_FORMATTER);
            return alunoRepository.findByDataNascimento(localDate);
        }
    }

    @Override
    public Aluno atualizarAluno(Long id, AlunoUpdateForm formUpdate) {
        Aluno aluno = alunoRepository.findById(id).get();
        aluno.setNome(formUpdate.getNome());
        aluno.setBairro(formUpdate.getBairro());
        aluno.setDataNascimento(formUpdate.getDataNascimento());
        return alunoRepository.save(aluno);
    }

    @Transactional
    public void delete(Long id) {
        if(matriculaRepository.existsByAlunoId(id) && avaliacaoFisicaRepository.existsByAlunoId(id)) {
            avaliacaoFisicaRepository.deleteByAlunoId(id);
            matriculaRepository.deleteByAlunoId(id);
        }
        alunoRepository.deleteById(id);
    }

    @Override
    public List<AvaliacaoFisica> getAllAvaliacaoFisicaId(Long id) {
        Aluno aluno = alunoRepository.findById(id).get();
        return aluno.getAvaliacaoFisica();
    }
}
