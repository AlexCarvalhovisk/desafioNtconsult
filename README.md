# Desafio Ntconsult(Sicredi)

## Projeto de votação de associados em assembléias.

Tecnologias utilizadas:

- Spring Boot
- Banco de dados H2
- Insomnia

![PostPautas](https://github.com/user-attachments/assets/4c658add-bfa2-4ae5-9960-4404d148d0f2)

Cria uma Pauta (POST)
http://localhost:8080/api/pautas

Exemplo de request:

<p align-"center">
{
	<br>"descricao": "Teste de versão de criação de pauta.",
	<br>"inicio": "2025-03-03T06:38:03",
	<br>"fim": "2025-03-03T06:38:03"<br>
}
</p><br>



![PostSessoes](https://github.com/user-attachments/assets/6e4b33e6-cc8b-4dc6-988a-f7ac1a068420)

Abre uma sessão de votação em uma pauta (POST)
http://localhost:8080/api/sessoes

Exemplo de request:

<p align-"center">
{
	<br>"pautaId": 1,
	<br>"tempoAberto": 1<br>
}
</p><br>



![PostVotos](https://github.com/user-attachments/assets/2c847d89-4d46-4ddb-9e6b-512335c7cd4c)

Recebe votos dos associados em pautas (POST)
http://localhost:8080/api/votos

Exemplo de request:

<p align-"center">
{
	<br>"associadoId": 100,
	<br>"sessaoId": 1,
  <br>"votoOpcao": "SIM"<br>
}
</p><br>


![GetRetornoAPI](https://github.com/user-attachments/assets/b463a84d-2871-4f6b-9afb-a6423ecaa2f7)

Retorna os votos e aprezenta os resultados da votação (GET)
http://localhost:8080/api/votos/resultados/1

