
# 🎮 PlayDex

> **Sua coleção. Seus jogos. Sua história.**

O **PlayDex** é uma plataforma desenvolvida para centralizar a biblioteca de jogos de usuários multiplataforma (Steam, PlayStation, Xbox, Nintendo). Inspirado em redes de catalogação cultural como o *Letterboxd*, o aplicativo tem como proposta permitir que jogadores registrem suas experiências, atribuam notas, escrevam resenhas e descubram novos títulos.

---

## 📌 Sobre o Projeto

Este projeto foi desenvolvido como parte da disciplina de **Desenvolvimento de Aplicativos Móveis (Android)** da Universidade Positivo.

O objetivo principal é criar uma solução intuitiva que ajude a organizar o histórico de jogos e incentive a comunidade gamer a compartilhar experiências.

### 🎯 Público-Alvo

- **Gamers Multiplataforma:** jogadores que utilizam mais de um console ou PC.
- **Entusiastas de organização:** pessoas que gostam de manter um acervo pessoal atualizado.
- **Jogadores ativos:** usuários interessados em avaliações, recomendações e descobertas de novos jogos.

---

## ✨ Principais Funcionalidades

- 📚 **Gerenciamento de Favoritos:** adicione, edite e remova jogos da sua lista, registrando status, data, nota e review de forma persistente.
- ⭐ **Avaliações e Reviews:** atribua notas em estrelas, selecione datas e escreva resenhas sobre os jogos salvas no banco de dados local.
- 🗄️ **Persistência Local (Room / SQLite):** Seus dados salvos (favoritos, avaliações e histórico) ficam seguros na memória interna do dispositivo, garantindo que nada seja perdido ao fechar o app.
- 🧠 **Gerenciamento de Estado (`AndroidViewModel`):** Arquitetura MVVM implementada para desacoplar a lógica e reter os dados perante eventos de ciclo de vida (como a rotação da tela).
- ⚡ **Processamento Assíncrono (Coroutines):** Operações de I/O executadas em segundo plano (`Dispatchers.IO`) integradas com segurança à thread principal (`Dispatchers.Main`).
- 🔎 **Pesquisa de Jogos e Histórico Reativo:** pesquise títulos, consulte o histórico ordenado cronologicamente (`ORDER BY id DESC`) e remova pesquisas anteriores.
- 🎮 **Detalhes dos Jogos:** visualize informações como nome, desenvolvedor, gênero e resumo.
- ❤️ **Detalhes dos Favoritos:** consulte individualmente os jogos adicionados, com suas informações e avaliações.
- 👤 **Perfil do Usuário:** acesse o perfil, as configurações e a lista de favoritos.
- 🧭 **Navegação Funcional e Arquitetura MVVM:** navegue entre as telas utilizando a barra inferior e componentes reativos gerenciados por `AndroidViewModel` e Coroutines.
- 🎯 **Descoberta de Jogos:** explore jogos populares e sugestões exibidas na página inicial.

---

## 💻 Como Executar o Projeto

### Pré-requisitos

- Android Studio instalado.
- Git configurado no computador.
- Emulador Android ou dispositivo físico disponível.
- Conexão com a internet para baixar o projeto e suas dependências.

### Passo a Passo

**1. Abra o Android Studio**

Com o Android Studio aberto, acesse:

**File → New → Project from Version Control...**

**2. Clone o repositório**

Na janela **Clone Repository**, selecione **Git** em *Version control*.

No campo **URL**, cole o endereço do repositório:

```text
https://github.com/anajulianicolack-hub/playdex3.git
```

No campo **Directory**, selecione a pasta onde o projeto será salvo.

Em seguida, clique em **Clone**.

**3. Abra e sincronize o projeto**

Aguarde o Android Studio baixar os arquivos e abrir o projeto.

Espere a sincronização do **Gradle** terminar. Caso seja necessário, utilize **File → Sync Project with Gradle Files**.

**4. Execute o aplicativo**

Selecione um emulador Android ou conecte um dispositivo físico com a depuração USB ativada.

Clique no botão **Run ▶** para compilar e iniciar o PlayDex.

---

## 📄 Documentação do Projeto

A documentação completa do projeto PlayDex encontra-se no arquivo PDF localizado nesta pasta: 📎 **[PLAYDEX — Documentação Completa (PDF)](https://github.com/yaslopes07/playdex2/blob/master/docs/PLAYDEX%20-%20Documenta%C3%A7%C3%A3o.docx.pdf)**

## ⚠️ Aviso Importante sobre os Vídeos

Como a documentação utiliza vídeos demonstrativos integrados (com links do Google Drive), recomendamos que você abra o PDF diretamente pelo Google Drive ou faça o download para visualizá-lo em um leitor de PDF compatível com links externos.
Se aberto direto no visualizador padrão do GitHub, alguns navegadores podem bloquear a reprodução ou o redirecionamento correto dos vídeos das etapas do aplicativo.


---

## 👥 Equipe de Desenvolvimento

- **Ana Julia Nicolack**
- **Yasmin da Rocha Lopes**


---

🎮 **PlayDex — Sua coleção. Seus jogos. Sua história.**
