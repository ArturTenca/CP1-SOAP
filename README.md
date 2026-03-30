# Sistema de Reserva de Salas — WebService SOAP

Checkpoint 1 — Arquitetura SOA / Web Services
Turma: 3ESPW | FIAP

---

## Integrantes

| Nome | RM |
|---|---|
| Artur Tenca | RM555171 |
| Igor Brunelli | RM555035 |
| Victor Capp | RM555753 |

---

## Sobre o projeto

Sistema de reserva de salas de reunião desenvolvido com WebService SOAP em Java utilizando JAX-WS, sem frameworks como Spring Boot.

O problema resolvido é o conflito de agendamentos em ambientes corporativos ou acadêmicos, onde múltiplas pessoas tentam reservar o mesmo espaço. O serviço centraliza as reservas e valida automaticamente conflitos de horário.

---

## Operações disponíveis

| Operação | Descrição |
|---|---|
| `listarSalas` | Retorna todas as salas disponíveis |
| `reservarSala` | Cria uma nova reserva |
| `cancelarReserva` | Cancela uma reserva existente |
| `consultarReserva` | Busca os dados de uma reserva pelo ID |

---

## Como executar

**Pré-requisitos:** Java 21 e Maven 3.8+

1. Abrir o projeto no IntelliJ e aguardar o Maven carregar as dependências
2. Rodar `ServicePublisher.java` para subir o servidor
3. Acessar `http://localhost:8080/reserva?wsdl` para verificar o WSDL
4. Com o servidor rodando, rodar `ReservaClient.java` para ver o cliente consumindo o serviço

---

## Testando no Insomnia

- Método: `POST`
- URL: `http://localhost:8080/reserva`
- Header: `Content-Type: text/xml;charset=UTF-8`

Exemplo — listar salas:

```xml
<soapenv:Envelope
    xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
    xmlns:ser="http://service.soap.grupo.com.br/">
    <soapenv:Header/>
    <soapenv:Body>
        <ser:listarSalas/>
    </soapenv:Body>
</soapenv:Envelope>
```

Exemplo — reservar sala:

```xml
<soapenv:Envelope
    xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
    xmlns:ser="http://service.soap.grupo.com.br/">
    <soapenv:Header/>
    <soapenv:Body>
        <ser:reservarSala>
            <salaId>1</salaId>
            <responsavel>João Silva</responsavel>
            <data>30/03/2026</data>
            <horario>14:00</horario>
        </ser:reservarSala>
    </soapenv:Body>
</soapenv:Envelope>
```

---

## Estrutura do projeto

```
src/main/java/br/com/grupo/soap/
├── model/
│   ├── Sala.java
│   ├── Reserva.java
│   ├── ReservaResponse.java
│   └── SalaListResponse.java
├── repository/
│   ├── SalaRepository.java
│   └── ReservaRepository.java
├── service/
│   └── ReservaService.java
├── publisher/
│   └── ServicePublisher.java
└── client/
    └── ReservaClient.java
```

---

## Tecnologias

- Java 21
- JAX-WS 2.3.7
- JAXB 2.3.1
- Maven
