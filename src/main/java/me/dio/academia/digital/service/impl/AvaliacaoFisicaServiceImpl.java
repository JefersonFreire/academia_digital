package me.dio.academia.digital.service.impl;

import jakarta.transaction.Transactional;
import me.dio.academia.digital.entity.Aluno;
import me.dio.academia.digital.entity.AvaliacaoFisica;
import me.dio.academia.digital.entity.form.AvaliacaoFisicaForm;
import me.dio.academia.digital.entity.form.AvaliacaoFisicaUpdateForm;
import me.dio.academia.digital.repository.AlunoRepository;
import me.dio.academia.digital.repository.AvaliacaoFisicaRepository;
import me.dio.academia.digital.service.IAvaliacaoFisicaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvaliacaoFisicaServiceImpl implements IAvaliacaoFisicaService {

    private final AvaliacaoFisicaRepository avaliacaoFisicaRepository;
    private final AlunoRepository alunoRepository;

    public AvaliacaoFisicaServiceImpl(AvaliacaoFisicaRepository avaliacaoFisicaRepository, AlunoRepository alunoRepository) {
        this.avaliacaoFisicaRepository = avaliacaoFisicaRepository;
        this.alunoRepository = alunoRepository;
    }

    @Override
    public AvaliacaoFisica create(AvaliacaoFisicaForm form) {
        AvaliacaoFisica avaliacao = new AvaliacaoFisica();
        Aluno aluno = alunoRepository.findById(form.getAlunoId()).get();

        avaliacao.setAluno(aluno);
        avaliacao.setPeso(form.getPeso());
        avaliacao.setAltura(form.getAltura());

        return avaliacaoFisicaRepository.save(avaliacao);
    }

    @Override
    public AvaliacaoFisica getId(Long id) {
        return avaliacaoFisicaRepository.findById(id).get();
    }

    @Override
    public List<AvaliacaoFisica> getAll() {
        return avaliacaoFisicaRepository.findAll();
    }

    @Override
    public AvaliacaoFisica update(Long id, AvaliacaoFisicaUpdateForm formUpdate) {
        AvaliacaoFisica avaliacaoFisica = avaliacaoFisicaRepository.findById(id).get();
        avaliacaoFisica.setPeso(formUpdate.getPeso());
        avaliacaoFisica.setAltura(formUpdate.getAltura());
        return avaliacaoFisicaRepository.save(avaliacaoFisica);
    }


    @Override
    public void delete(Long id) {
        avaliacaoFisicaRepository.deleteById(id);
    }
}
