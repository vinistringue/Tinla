# TINLA - SISTEMA DE CONTROLE DE VENDAS 
 
**TINLA - SISTEMA DE CONTROLE DE VENDAS**
 
**APRESENTAÇÃO**

* O Tinla é um software para controle de vendas que possui integração entre vendas, compras, financeiro , paganmentos, recebimentos e estoque de produtos. Esta integração entre os módulos permite que o empreendedor otimize seu tempo. Agilizando ainda mais os processos operacionais você poderá se concentrar realmente no que importa, **o sucesso do seu negócio.**


**DESCRIÇÃO DO PROJETO**

* O Tinla é um projeto de controle de vendas  para negócios físicos onde você empreendedor automatizará as vendas, compras, financeiro, pagamentos, recebimentos e estoque de produtos.


**DESCRIÇÃO DOS USUÁRIOS**  

* O sitema possuí 3 usuários:
   * **Vendedor:** cada vendendor terá seu usuário e senha para saber o vendedor que realizou a venda, a quantidade, data e horário. O vendedor terá uma tela para cadastro de novos clientes.
   * **Financeiro:** Apenas o setor financeiro e gerente terão acesso aos dados do setor financeiro.
   * **Gerente:** Gerente terá acesso ao setor financeiro, acesso à relatórios de serviço e cadastro e remoção de usuários/funcionários.


**NECESSIDADES OBSERVADAS E REGRAS DE NEGÓCIO**

   **[RN001] < Autenticação de acesso >**

   * **RN01: < Solicitação de login pelo sistema >**
         * O sistema deverá solicitar login assim que o usuário abrir o software.

      * **RN02: < Pré-Requisito >**
         * Apenas usuários/funcionários cadastrados terão acessos ao sistema.

      * **RN03: < Segurança do sistema >**
         * Só terá acesso ao sistema/software se a pessoa posssuir um login e senha.


   **[RN002] < Controle de entrada e saída de valores de uma empresa >**

   * **RN01: < Segurança imposta pelo sistema >**
      * Apenas pessoas autorizadas terão acesso ao setor financeiro.

   * **RN02: < Geração de relatórios >**
      * Só pessoas/usuários com acesso ao setor financeiro poderão gerar relatórios.


   **[RN003] < Cadastro de novos produtos >**

   * **RN01: < Pré-Requisito >**
      * Só usuários com permissão poderão cadastrar novos produtos no sistema.


   **[RN004] < Cadastro de novos clientes >**

   * **RN01: < Pré-Requisito >**
      * Apenas usuários/funcionários com permisssão para cadastrar novos clientes.


   **[RN005] < Cadastro e remoção de funcionários/usuários >**

   * **RN01: < Pré-Requisito >**
      * Só usuário com permissão poderão cadastrar novos usuários/funcionários.


**REQUISITOS FUNCIONAIS**

   **[RF001] < Autenticação de acesso >**
   * **Ator:** < Vendedores, financeiro, gerente >
   * **Descrição:** < O sistema deverá ter de ser feito login, pois através do login teremos controle de quem tem acesso a quais dados dentro do sistema de uma empresa >


   **[RF002] < Controle de entrada e saída de valores de uma empresa >**
   * **Ator:** < Gerente e financeiro >
   * **Descrição:** < O sistema deverá permitir uma fácil manipulação das movimentações financeiras da empresa e gerar um relatório >


   **[RF003] < Cadastro de novos produtos >**
   * **Ator:** < Vendedores e gerente >
   * **Descrição:** < O sistema deverá permitir o cadastro de novos produtos, de modo que seja fácil e bem intuitivo de cadastrar >


   **[RF004] < Cadastro de novos clientes >**
   * **Ator:** < Vendedores e gerente >
   * **Descrição:** < O sistema terá uma tela para cadastro de novos clientes, de forma fácil e bem intuitiva >


   **[RF005] < Cadastro e remoção novos funcionários/usuários >**
   * **Ator:** < Gerente >
   * **Descrião:** < O sistema deverá permitir o cadastro/remoção do funcionário/usuário de forma fácil e intuitiva >


**REQUISITOS NÃO FUNCIONAIS**

   **[RNF001] < Segurança do sistema >**
   * **Descrição:** < Apenas pesssoas com login e senha poderão ter acesso ao sistema, para que pessoas não autorizadas não mexam >

   **[RNF002] < Sistema desktop >**
   * **Descrição:** < O sistema terá de ser desktop pelo fato de ter que operar desconectado da internet, conectado apenas a um banco de dados local >

   **[RNF003] < Conexão a um banco de dados local >**
   * **Descrição:** < O sistema estará conectado a um banco de dados local com todos os dados da empresa >


**CASOS DE USO**

   * **Caso de uso 1: < Autenticação de acesso >**
      1. Abrir o sistema da empresa
      2. O usuário deverá fazer login no sistema, com seu usuário e senha
      3. Após fazer login terá acesso a um ambiente, e através do seu login mesmo o sistema identificará as permissões que aquele usuário possui
      4. O sistema identificará qauais as funcionalidades que ele pode usar e o que cada funcionário pode ter acesso, pois é muito importante que apenas pessoas autorizadas tenham acesso ao sistema 


   * **Caso de uso 2: < Controle de entrada e saída de valores de uma empresa >**
      1. Apenas pessoas autorizadas
      2. Após ter feito login no sistema, ele já identificará se o funcionário terá acesso
      3. Caso funcionário/usuário tenha acesso, ele poderá gerar um relatório


   * **Caso de uso 3: < Cadastro de novos produtos >**
      1. Após ter feito login no sistema, terá uma janela pra cadastro de novos produtos
      2. A janela para cadastro de novos produtos é basicamente uma tela pra cadastrar os novos produtos
      

   * **Caso de uso 4: < Cadastro de novos clientes >**
      1. Pessoas autorizadas 
      2. Terá acesso a uma janela pra cadastro de novos clientes 


   * **Caso de uso 5: < Cadastro e remover funcionários/usuários >**
      1. Pessoas autorizada a cadastro e remoção de usuários/ funcionários
      2. Terá acesso a uma janela a cadastro e remoção de usuários/funcionários


**TECNOLOGIAS PREVISTAS**

   **[NetBeansIDE]** < O NetBeans IDE é um ambiente de desenvolvimento gratuito e de código aberto para desenvolvedores de software na linguagem nas linguagens Java, JavaScript, Ruby, entre outras >

   **[JAVA]** < A linguagem mais adequada seria a utilização do Java, pelo fato de ser interpretado pelo Linux e Windows, além de poder desenvolver um sistema desktop >

   **[MySQL]** < O banco de dados que será utilizado será o MySQL com conexão à uma rede local, onde todos os computadores conectado a rede terão acesso aos dados do banco de dados >

