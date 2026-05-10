# 🎥 Panorama - Gerenciador de Mídias

## 🚀 Para que serve?
O **Panorama** é um sistema web local desenvolvido para gerenciar o consumo de diferentes tipos de mídia, como quadrinhos, animações, filmes e álbuns de música.

O objetivo do projeto é oferecer um ambiente simples, intuitivo e amigável para que o usuário possa organizar suas coleções.
Com ele, você pode cadastrar o que já consumiu e o que ainda deseja consumir, permitindo um controle total sobre sua "pilha de leitura" ou lista de espera, evitando que você se perca em meio a tantos títulos.

---

## 🛠️ Como rodar o sistema?

### Pré-requisitos
Antes de começar, você precisará ter instalado em sua máquina:
* **Java 17** ou superior.
* **MySQL 8**.

### Passo a Passo

1.  **Configuração do Banco de Dados:**
    * Crie um banco de dados chamado `panorama`.
    * Dentro dele, crie uma tabela chamada `midia` seguindo os atributos da classe `Midia` presente na aplicação.

2.  **Configuração de Acesso:**
    * Localize a classe `MySQLDataSourceConfig`.
    * No método de configuração do `DataSource`, altere os campos `username` e `password` para as credenciais do seu banco de dados local.

3.  **Configuração de Arquivos (Imagens):**
    * No arquivo `src/main/resources/application.properties`, encontre a propriedade:
      `spring.web.resources.static-locations=`.
    * Altere o caminho para o diretório onde você deseja que as imagens das mídias sejam salvas e lidas localmente.

4.  **Execução:**
    * Compile e execute o projeto através da sua IDE de preferência ou via terminal.
