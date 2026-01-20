<h1 align="center" style="font-weight: bold;">Gestão de Documentos de Beneficiado - Plano de Saúde💻</h1>

<p align="center">
 <a href="#tech">Stacks</a> • 
  <a href="#routes">API Endpoints</a> 
</p>

<p align="center">
    <b>API para gestão de documentos de um beneficiário de plano de saúde. Resolução do desafio técnico: https://github.com/matheuslf/com-rocketseat-desafio-junior.</b>
</p>

<h2 id="technologies">💻 Stacks</h2>

- Java 21
- PostgreSQL 17.5
- Spring Boot 4.0.1


<h2 id="routes">📍 API Endpoints</h2>

Endpoints que compõe a API:
​
| route               | description                                          
|----------------------|-----------------------------------------------------
| <kbd>GET api/v1/usuarios</kbd>     | Listar todos os beneficiários cadastrados
| <kbd>GET api/v1/usuarios/{usuarioId}</kbd>     | Buscar dados de um beneficiário cadastrado
| <kbd>POST api/v1/usuarios</kbd>     | Cadastrar um beneficiário com seus documentos
| <kbd>PUT api/v1/usuarios/{usuarioId}</kbd>     | Atualizar dados um beneficiário
| <kbd>DELETE api/v1/usuarios/{usuarioId}</kbd>     | Deletar um beneficiário
| <kbd>GET api/v1/documentos/{usuarioId}</kbd>     | Listar todos os documentos cadastrados de um beneficiário

