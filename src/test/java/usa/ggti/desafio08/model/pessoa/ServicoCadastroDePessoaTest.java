package usa.ggti.desafio08.model.pessoa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import usa.ggti.desafio08.infra.exception.ValidacaoListagemPessoaException;
import usa.ggti.desafio08.model.pessoa.dtos.DadosAtualizacaoPessoaDto;
import usa.ggti.desafio08.model.pessoa.dtos.DadosCadastroPessoaDto;
import usa.ggti.desafio08.model.pessoa.dtos.DadosDetalhamentoPessoaDto;
import usa.ggti.desafio08.model.pessoa.validacoes.ValidadorCadastroDePessoa;
import usa.ggti.desafio08.util.PadronizarNome;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ServicoCadastroDePessoaTest {

    @Mock
    private PessoaRepository pessoaRepository;

    @Mock
    private PadronizarNome padronizarNome;

    @Mock
    private ValidadorCadastroDePessoa validadorCadastroDePessoa;

    @InjectMocks
    private ServicoCadastroDePessoa servicoCadastroDePessoa;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void cadastrar_deveCadastrarPessoa() {
        // Arrange
        DadosCadastroPessoaDto dados = new DadosCadastroPessoaDto("john Doe", "johndoe@example.com", "25");
        String nomePadronizado = "John Doe";
        Pessoa pessoaCadastrada = new Pessoa(null, nomePadronizado, "johndoe@example.com", "25", true);
        DadosDetalhamentoPessoaDto expected = new DadosDetalhamentoPessoaDto(pessoaCadastrada);

        when(padronizarNome.primeiraLetraMaiuscula(dados.nome())).thenReturn(nomePadronizado);
        when(pessoaRepository.save(any(Pessoa.class))).thenReturn(pessoaCadastrada);

        // Act
        DadosDetalhamentoPessoaDto result = servicoCadastroDePessoa.cadastrar(dados);

        // Assert
        assertEquals(expected, result);
        verify(padronizarNome).primeiraLetraMaiuscula(dados.nome());
        verify(pessoaRepository).save(any(Pessoa.class));
    }

    @Test
    void listar_deveRetornarListaDePessoasAtivas() {
        // Arrange
        List<Pessoa> pessoasAtivas = new ArrayList<>();
        pessoasAtivas.add(new Pessoa(1L, "John Doe", "johndoe@example.com", "25", true));
        pessoasAtivas.add(new Pessoa(2L, "Jane Smith", "janesmith@example.com", "30", true));
        List<DadosDetalhamentoPessoaDto> expected = new ArrayList<>();
        expected.add(new DadosDetalhamentoPessoaDto(pessoasAtivas.get(0)));
        expected.add(new DadosDetalhamentoPessoaDto(pessoasAtivas.get(1)));

        when(pessoaRepository.findAllByAtivoTrue()).thenReturn(pessoasAtivas);

        // Act
        List<DadosDetalhamentoPessoaDto> result = servicoCadastroDePessoa.listar();

        // Assert
        assertEquals(expected, result);
        verify(pessoaRepository).findAllByAtivoTrue();
    }

    @Test
    void buscarPorId_deveRetornarPessoaExistente() {
        // Arrange
        Long id = 1L;
        Pessoa pessoaExistente = new Pessoa(id, "John Doe", "johndoe@example.com", "25", true);
        DadosDetalhamentoPessoaDto expected = new DadosDetalhamentoPessoaDto(pessoaExistente);

        when(pessoaRepository.existsById(id)).thenReturn(true);
        when(pessoaRepository.getReferenceById(id)).thenReturn(pessoaExistente);

        // Act
        DadosDetalhamentoPessoaDto result = servicoCadastroDePessoa.buscarPorId(id);

        // Assert
        assertEquals(expected, result);
        verify(pessoaRepository).existsById(id);
        verify(pessoaRepository).getReferenceById(id);
    }

    @Test
    void buscarPorId_deveLancarExcecaoSePessoaNaoExistir() {
        // Arrange
        Long id = 1L;

        when(pessoaRepository.existsById(id)).thenReturn(false);

        // Act & Assert
        assertThrows(ValidacaoListagemPessoaException.class, () -> {
            servicoCadastroDePessoa.buscarPorId(id);
        });
        verify(pessoaRepository).existsById(id);
        verify(pessoaRepository, never()).getReferenceById(id);
    }

    @Test
    void excluirPorId_deveInativarPessoaExistente() {
        // Arrange
        Long id = 1L;
        Pessoa pessoaExistente = new Pessoa(id, "John Doe", "johndoe@example.com", "25", true);

        when(pessoaRepository.existsById(id)).thenReturn(true);
        when(pessoaRepository.getReferenceById(id)).thenReturn(pessoaExistente);

        // Act
        servicoCadastroDePessoa.excluirPorId(id);

        // Assert
        assertFalse(pessoaExistente.isAtivo());
        verify(pessoaRepository).existsById(id);
        verify(pessoaRepository).getReferenceById(id);
    }

    @Test
    void excluirPorId_deveLancarExcecaoSePessoaNaoExistir() {
        // Arrange
        Long id = 1L;

        when(pessoaRepository.existsById(id)).thenReturn(false);

        // Act & Assert
        assertThrows(ValidacaoListagemPessoaException.class, () -> {
            servicoCadastroDePessoa.excluirPorId(id);
        });
        verify(pessoaRepository).existsById(id);
        verify(pessoaRepository, never()).getReferenceById(id);
    }

    @Test
    void atualizar_deveAtualizarDadosDaPessoaExistente() {
        // Arrange
        Long id = 1L;
        DadosAtualizacaoPessoaDto dados = new DadosAtualizacaoPessoaDto("john Doe", "johndoe@example.com", "30");
        String nomePadronizado = "John Doe";
        Pessoa pessoaExistente = new Pessoa(id, nomePadronizado, "johndoe@example.com", "25", true);
        Pessoa pessoaEsperada = new Pessoa(id, nomePadronizado, "johndoe@example.com", "30", true);
        DadosDetalhamentoPessoaDto expected = new DadosDetalhamentoPessoaDto(pessoaEsperada);

        when(pessoaRepository.existsById(id)).thenReturn(true);
        when(padronizarNome.primeiraLetraMaiuscula(dados.nome())).thenReturn(nomePadronizado);
        when(pessoaRepository.getReferenceById(id)).thenReturn(pessoaExistente);
        when(pessoaRepository.save(any(Pessoa.class))).thenReturn(pessoaEsperada);
        // Act
        DadosDetalhamentoPessoaDto result = servicoCadastroDePessoa.atualizar(dados, id);

        // Assert
        assertEquals(expected, result);
        verify(pessoaRepository).existsById(id);
        verify(padronizarNome).primeiraLetraMaiuscula(dados.nome());
        verify(pessoaRepository).getReferenceById(id);
        verify(pessoaRepository).save(pessoaExistente);
    }

    @Test
    void atualizar_deveLancarExcecaoSePessoaNaoExistir() {
        // Arrange
        Long id = 1L;
        DadosAtualizacaoPessoaDto dados = new DadosAtualizacaoPessoaDto("John Doe", "johndoe@example.com", "30");

        when(pessoaRepository.existsById(id)).thenReturn(false);

        // Act & Assert
        assertThrows(ValidacaoListagemPessoaException.class, () -> {
            servicoCadastroDePessoa.atualizar(dados, id);
        });
        verify(pessoaRepository).existsById(id);
        verify(pessoaRepository, never()).getReferenceById(id);
        verify(pessoaRepository, never()).save(any(Pessoa.class));
    }
}