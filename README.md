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

## 9. Expansão da API

Em continuação ao exercício anterior, adicione mais três funcionalidades à sua API, conforme descrito abaixo:

Inicialmente, você possuía apenas essas duas rotas:

- **POST /api/pessoas** (dados no corpo da requisição): Para cadastrar uma pessoa no repositório.
- **GET /api/pessoas**: Para listar todas as pessoas cadastradas.

Agora, adicione as seguintes três rotas:

1. **GET /api/pessoas/{id}**: Para recuperar uma pessoa no repositório pelo seu ID.
2. **DELETE /api/pessoas/{id}**: Para excluir uma pessoa da lista através do seu ID.
3. **PUT /api/pessoas/{id}** (dados no corpo da requisição): Atualizar dados de uma pessoa específica.

A mini API deve receber objetos JSON e retornar JSON.

**Observações:**
- Ao receber dados inválidos de uma pessoa, conforme feito no exercício anterior, você deve retornar um código 400 (Bad Request) com a respectiva mensagem de erro.
- Ao ter sucesso ao salvar a pessoa na base, você deve retornar um código 201 (Created).
- Ao listar pessoas, caso não haja ninguém cadastrado, retorne o código 200 (OK) com a lista vazia.

Em cada rota específica:

1. Para a rota **GET /api/pessoas/{id}**:
   - No caso de sucesso, deve retornar um JSON contendo a pessoa e o código de status 200.
   - No caso de não encontrar uma pessoa com o ID específico, deve-se retornar um código de status 404.

2. Para a rota **DELETE /api/pessoas/{id}**:
   - No caso de sucesso, deve retornar apenas o código de status 200.
   - No caso de não encontrar uma pessoa com o ID específico, deve-se retornar um código de status 404.

3. Para a rota **PUT /api/pessoas/{id}**:
   - No caso de sucesso, deve retornar um JSON contendo a pessoa e o código de status 200.
   - No caso de não encontrar uma pessoa com o ID específico, deve-se retornar um código de status 404 ou 400 com os erros específicos no corpo.

