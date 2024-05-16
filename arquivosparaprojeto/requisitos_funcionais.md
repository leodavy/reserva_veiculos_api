# Requisitos Funcionais (RF)

1. **Permitir que os usuários realizem o cadastro**
    - Os usuários podem ser cadastrados no sistema.
    - O cadastro inclui nome, login e senha.

2. **Possibilitar aos administradores o cadastro de Perfil de Usuário**
    - Os perfis de usuário podem ser definidos no sistema.
    - Cada perfil possui um nome único.

3. **Possibilitar aos administradores a listagem dos usuários do sistema**

4. **Possibilitar aos administradores a associação entre Usuários e Perfis**
    - Os usuários podem ser associados a um ou mais perfis.
    - A associação é feita em uma tabela de ligação entre usuários e perfis.

5. **Possibilitar aos usuários o cadastro de Veículo**
    - Os veículos podem ser cadastrados no sistema.
    - O cadastro inclui nome, marca e tipo do veículo.

6. **Possibilitar aos usuários o a atualização de Veículo**
    - Os veículos podem ser atualizados no sistema.
    - A atualização inclui nome, marca e tipo do veículo.

7. **Permitir que os usuários realizem reservas de veículos**
    - Os usuários podem estar associados a um ou mais veículos.
    - A associação é registrada em uma tabela que também armazena a data de reserva.

8. **Possibilitar aos usuários a exclusão de Veículos**
    - A exclusão deve também remover todas as imagens e reservas associadas.

9. **Possibilitar aos usuários o armazenamento de Imagens de Veículo**
    - Imagens dos veículos podem ser armazenadas no sistema.
    - Cada imagem é identificada por um ID único e associada a um veículo.
    - Um veículo pode ter no máximo 6 imagens.

10. **Possibilitar aos usuários a alteração de Imagens do Veículo**
    - A atualização trata-se da adição ou substituição de uma imagem.

11. **Possibilitar aos usuários a exclusão de Imagens do Veículo**
    - Um veículo pode não ter nenhuma imagem.

12. **O sistema deve possibilitar a autenticação do Usuário**
    - O sistema deve autenticar os usuários com base em seus nomes de usuário e senhas.
    - O acesso às funcionalidades do sistema deve ser restrito com base nos perfis associados a cada usuário.

13. **O sistema deve manter a restrição de Acesso a Dados**
    - Os usuários só podem visualizar e manipular dados dos veículos e imagens aos quais estão associados.
