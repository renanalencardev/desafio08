# Desafio 8: Sistema com API REST Simples

Exponha o sistema criado no exercício 6 através de uma API REST, utilizando qualquer framework de rotas à sua escolha, como Express, Hapi, Spring, NestJs, Mux, Flask, etc.

Inicialmente, defina duas rotas:

1. **POST /api/pessoas**: Para cadastrar uma pessoa no repositório.
   - A API deve aceitar objetos JSON.
   - Em caso de dados inválidos para uma pessoa (conforme tratado no exercício anterior), deve retornar um código de status 400 (Bad Request) junto com a mensagem de erro correspondente.
   - Em caso de sucesso ao salvar a pessoa na base, deve retornar um código de status 201 (Created).

2. **GET /api/pessoas**: Para listar todas as pessoas cadastradas.
   - A API deve retornar JSON.
   - Se não houver nenhuma pessoa cadastrada, deve retornar um código de status 200 (OK) acompanhado de uma lista vazia.

Observação: Certifique-se de seguir as práticas de design de API RESTful e garantir a consistência nas respostas de acordo com as especificações mencionadas.
