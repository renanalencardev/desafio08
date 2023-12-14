package usa.ggti.desafio08.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import usa.ggti.desafio08.domain.pessoa.DadosCadastroPessoaDto;
import usa.ggti.desafio08.domain.pessoa.DadosDetalhamentoPessoaDto;
import usa.ggti.desafio08.domain.pessoa.ServicoCadastroDePessoa;

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
}
