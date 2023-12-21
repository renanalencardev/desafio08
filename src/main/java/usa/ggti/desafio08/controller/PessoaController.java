package usa.ggti.desafio08.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import usa.ggti.desafio08.model.pessoa.dtos.DadosAtualizacaoPessoaDto;
import usa.ggti.desafio08.model.pessoa.dtos.DadosCadastroPessoaDto;
import usa.ggti.desafio08.model.pessoa.dtos.DadosDetalhamentoPessoaDto;
import usa.ggti.desafio08.model.pessoa.ServicoCadastroDePessoa;

import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {
    @Autowired
    private ServicoCadastroDePessoa pessoaService;

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroPessoaDto dados, UriComponentsBuilder uriBuilder){
        var pessoaCadastrada = pessoaService.cadastrar(dados);
        var uri = uriBuilder.path("/pessoas/{id}").buildAndExpand(pessoaCadastrada.id()).toUri();
        return ResponseEntity.created(uri).body(pessoaCadastrada);
    }

    @GetMapping
    public ResponseEntity<List<DadosDetalhamentoPessoaDto>> listar (){
        var pessoas = pessoaService.listar();
        return ResponseEntity.ok(pessoas);
    }

    @GetMapping("/{id}")
    public ResponseEntity buscarPorId(@PathVariable Long id){
        var pessoa = pessoaService.buscarPorId(id);
        return ResponseEntity.ok().body(pessoa);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluir(@PathVariable Long id){
        pessoaService.excluirPorId(id);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity atualizar(@PathVariable Long id, @RequestBody @Valid DadosAtualizacaoPessoaDto dados){
        var pessoa = pessoaService.atualizar(dados, id);
        return ResponseEntity.ok(pessoa);
    }
}
