package org.example;

import dao.PessoaDao;
import dao.ProfissaoDao;
import util.Factory;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Scanner;
import java.util.Iterator;

public class Menu {
    Scanner scanner = new Scanner(System.in);
    EntityManager em = Factory.getEntityManager();
    EntityManager pess = Factory.getEntityManager();
    ProfissaoDao profDAO = new ProfissaoDao(em);
    PessoaDao pessDAO = new PessoaDao(pess);

    public Menu(){
    }

    //Menu principal do sistema
    public void MenuPrincipal(){
        System.out.println("========== MENU PRINCIPAL ==========");
        System.out.println("1 -- Profissão");
        System.out.println("2 -- Pessoa");
        System.out.println("3 -- Sair");
        System.out.print("Selecione uma opção: ");
        int opção = scanner.nextInt();
        switch (opção){
            case 1:
                MenuProfissão();
                break;
            case 2:
                MenuPessoa();
                break;
            case 3:
                System.exit(0);
                break;
        }
    }

    //Menu para processos com pessoas
    public void MenuPessoa(){
        System.out.println("========== ESCOLHA OQUE DESEJA FAZER ==========");
        System.out.println("1 -- Cadastrar Pessoa");
        System.out.println("2 -- Excluir Pessoa");
        System.out.println("3 -- Editar Cadastro");
        System.out.println("4 -- Visualizar Cadastros");
        System.out.println("5 -- Voltar");
        System.out.print("Selecione uma opção: ");

        int opcao = scanner.nextInt();

        switch (opcao){

            //cadastro de Pessoas
            case 1:
                System.out.println("========== Cadastro de Pessoa ==========");
                System.out.print("Nome: ");
                String nome = scanner.next();
                System.out.print("Documento: ");
                String documento = scanner.next();
                System.out.print("Contato: ");
                String contato = scanner.next();
                System.out.print("Idade: ");
                int idade = scanner.nextInt();
                System.out.print("Cidade: ");
                String cidade = scanner.next();
                System.out.print("Rua: ");
                String rua = scanner.next();
                System.out.print("Numero: ");
                int numero = scanner.nextInt();
                System.out.println("");

                //mostra todas as profissoes disponiveis
                System.out.println("Profissões disponiveis:");
                profDAO.conectar();
                List<Profissao> profissoes = profDAO.mostrarProfissao();
                profDAO.encerrar();
                for (Profissao prof: profissoes){
                    System.out.println(prof.getId() + " -- " + prof.getProfissao());
                }
                System.out.print("escolha uma profissão: ");
                int profissaoId = scanner.nextInt();

                //verifica a profissão escolhida se existe no banco
                Profissao profissao = null;
                for (Profissao prof : profissoes) {
                    if (prof.getId() == profissaoId) {
                        profissao = prof;
                        break;
                    }
                }
                //adiciona a pessoa e fecha a conexão
                Pessoa pessoa = new Pessoa(nome, documento, contato, idade, cidade, rua, numero, profissao);
                pessDAO.Conectar();
                pessDAO.Salvar(pessoa);
                pessDAO.Encerrar();
                System.out.println("Pessoa Cadastrada com sucesso");
                MenuPessoa();
                break;

            //Excluir pessoa
            case 2:
                System.out.println("========== EXCLUIR PESSOA ==========");
                //realiza a conexão
                pessDAO.Conectar();
                List<Pessoa> pessoas = pessDAO.consultar();

                // Lista todas as pessoas com seus IDs
                for (Pessoa p : pessoas) {
                    System.out.println(p.getId() + "." + p.getNome() + ", Doc: " + p.getCpf());
                }

                System.out.print("Escolha uma pessoa para excluir (informe o ID): ");
                int pessoaId = scanner.nextInt();

                // Verificar se a pessoa com o ID especificado existe
                boolean pessoaEncontrada = false;
                Iterator<Pessoa> iterator = pessoas.iterator();
                while (iterator.hasNext()) {
                    Pessoa VerPessoa = iterator.next();
                    if (VerPessoa.getId() == pessoaId) {
                        iterator.remove(); // Remove a pessoa da lista
                        pessDAO.Deletar(VerPessoa); // Exclui a pessoa do banco de dados
                        pessoaEncontrada = true;
                        break; // Sai do loop depois de encontrar a pessoa
                    }
                }
                //se a pessoa for encontrada printa a mesg caso não for printa a outra
                if (pessoaEncontrada) {
                    System.out.println("Pessoa excluída com sucesso!");
                } else {
                    System.out.println("Pessoa não encontrada com o ID especificado.");
                }
                pessDAO.Encerrar(); //encerra a conexão
                MenuPessoa();//retorna para o menu de pessoas
                break;

            //edita cadastro
            case 3:
                System.out.println("========= Editar Cadastro ==========");
                pessDAO.Conectar(); //realiza a conexão e salva as pessoas em uma lista
                List<Pessoa> EditPessoas = pessDAO.consultar();

                // Lista todas as pessoas com seus IDs
                for (Pessoa p : EditPessoas) {
                    p.listarPessoa();
                }
                System.out.println("selecione um ID para Editar: ");
                int IDeditar = scanner.nextInt();

                // Verificar se a pessoa com o ID especificado existe
                boolean pessoaEdit = false;
                Iterator<Pessoa> PercorrePessoas = EditPessoas.iterator();
                while (PercorrePessoas.hasNext()) {
                    Pessoa p = PercorrePessoas.next();
                    if (p.getId() == IDeditar) {   //caso exista pede para digitar os novos dados e salva nas variaveis
                        System.out.print("Nome: ");
                        String nomeEdit = scanner.next();
                        p.setNome(nomeEdit);
                        System.out.print("Documento: ");
                        String documentoEdit = scanner.next();
                        p.setCpf(documentoEdit);
                        System.out.print("Contato: ");
                        String contatoEdit = scanner.next();
                        p.setContato(contatoEdit);
                        System.out.print("Idade: ");
                        int idadeEdit = scanner.nextInt();
                        p.setIdade(idadeEdit);
                        System.out.print("Cidade: ");
                        String cidadeEdit = scanner.next();
                        p.setCidade(cidadeEdit);
                        System.out.print("Rua: ");
                        String ruaEdit = scanner.next();
                        p.setRua(ruaEdit);
                        System.out.print("Numero: ");
                        int numeroEdit = scanner.nextInt();
                        p.setNumero(numeroEdit);
                        System.out.println("");

                        System.out.println("Profissões disponiveis:"); //lista todas as profissoes disponiveis
                        profDAO.conectar();
                        List<Profissao> profissoesEdit = profDAO.mostrarProfissao();
                        profDAO.encerrar();
                        for (Profissao prof: profissoesEdit){
                            System.out.println(prof.getId() + " -- " + prof.getProfissao());
                        }
                        System.out.print("escolha uma profissão: ");
                        int profissaoEdit = scanner.nextInt();

                        for (Profissao prof : profissoesEdit) {
                            if (prof.getId() == profissaoEdit) { //altera a profissão para a selecionada
                                p.setProfissao(prof);
                                break;
                            }
                        }
                        pessDAO.Atualizar(p); //atualiza a pessoa
                        pessDAO.Encerrar(); //encerra a conexao
                        pessoaEdit = true;
                        break; // Sai do loop depois de encontrar a pessoa
                    }
                }
                //se a pessoa existe printa uma msg caso nao printa a outra
                if (pessoaEdit) {
                    System.out.println("Pessoa Editada com sucesso!");
                } else {
                    System.out.println("Pessoa não encontrada com o ID especificado.");
                }
                MenuPessoa();
                break;

            //listar pessoas disponiveis
            case 4:
                System.out.println("========== LISTAR PESSOAS ==========");
                pessDAO.Conectar(); //realiza a conexão
                List<Pessoa> ListarPessoas = pessDAO.consultar();
                pessDAO.Encerrar();

                // Lista todas as pessoas com seus IDs
                for (Pessoa p : ListarPessoas) {
                    p.listarPessoa();
                }
                MenuPessoa();
                break;
            case 5:
                MenuPrincipal(); //volta para o menu principal
                break;
        }
    }

    //mostra as opções do cadastro de profissões
    public void MenuProfissão(){
        System.out.println("========== ESCOLHA OQUE DESEJA FAZER ==========");
        System.out.println("1 -- Cadastrar Profissão");
        System.out.println("2 -- Excluir Profissão");
        System.out.println("3 -- Editar Profissão");
        System.out.println("4 -- Visualizar Profissões");
        System.out.println("5 -- Voltar");
        System.out.print("Selecione uma opção: ");

        int opcao = scanner.nextInt();

        switch (opcao) {

            //cadastra uma profissão
            case 1:
                System.out.println("========== Cadastro de Profissão ==========");
                System.out.print("Nome da profissão: ");
                String profissao = scanner.next(); //solicita o nome da profissao e salva na variavel
                Profissao prof = new Profissao(profissao);
                profDAO.conectar(); //realiza a conxão, salva no banco e encerra logo em seguida
                profDAO.salvar(prof);
                profDAO.encerrar();
                System.out.println("profissão cadastrada com sucesso");
                MenuProfissão();
                break;

            //excluir profissao
            case 2:
                System.out.println("========== Excluir Profissão ==========");
                System.out.println("Profissôes cadastradas:");
                profDAO.conectar(); // faz a conexao e mostra todas as profissões disponiveis
                List<Profissao> listaProfissão = profDAO.mostrarProfissao();
                for (Profissao listProf : listaProfissão) {
                    System.out.println(listProf.getId() + "." + listProf.getProfissao());
                }
                System.out.print("escolha a Profissão que deseja excluir: ");
                int ProfId = scanner.nextInt();

                //verifica se a profissao existe
                boolean ProfissaoEncontrada = false;
                Iterator<Profissao> iterator = listaProfissão.iterator();
                while (iterator.hasNext()) {
                    Profissao VerProf = iterator.next();
                    if (VerProf.getId() == ProfId) {
                        iterator.remove(); // Remove a pessoa da lista
                        profDAO.deletar(VerProf); // Exclui a pessoa do banco de dados
                        ProfissaoEncontrada = true;
                        break; // Sai do loop depois de encontrar a pessoa
                    }
                }
                if (ProfissaoEncontrada) {
                    System.out.println("Profissão excluída com sucesso!");
                } else {
                    System.out.println("Profissão não encontrada com o ID especificado.");
                }
                pessDAO.Encerrar(); //encerra a conexao
                MenuProfissão();
                break;

            //editar profissões
            case 3:
                System.out.println("========== Editar Profissoes ==========");
                profDAO.conectar(); // realiza a conexao e lista as profissões disponiveis
                List<Profissao> ListaProfissaoEditar = profDAO.mostrarProfissao();
                for (Profissao p : ListaProfissaoEditar) {
                    p.listarProfissoes();
                }

                System.out.print("escolha a Profissão que deseja Editar: ");
                int ProfID_Edit = scanner.nextInt();

                // Verificar se a profissao com o ID especificado existe
                boolean ProfEdit = false;
                Iterator<Profissao> PercorreProfissao = ListaProfissaoEditar.iterator();
                while (PercorreProfissao.hasNext()) {
                    Profissao p = PercorreProfissao.next();
                    if (p.getId() == ProfID_Edit) {
                        System.out.print("Informe o nome da Profissao: ");
                        String profNome = scanner.next(); //solicita os novos dados e salva em uma variavel
                        p.setProfissao(profNome);
                        ProfEdit = true;

                        profDAO.atualizar(p); //atualiza a profissão e encerra a conexao
                        profDAO.encerrar();
                    }
                }

                if(ProfEdit){
                    System.out.println("alterado com sucesso!");
                    MenuProfissão();
                }else{
                    System.out.println("Não foi encontrado o ID especificado");
                    MenuProfissão();
                }
                break;

            //lista as profissões disponiveis
            case 4:
                profDAO.conectar(); //realiza a conexão e lista todas atravez do metodo listarProfissões
                List<Profissao> ListaProfissao = profDAO.mostrarProfissao();

                for (Profissao p : ListaProfissao) {
                    p.listarProfissoes();
                }
                profDAO.encerrar(); // encerra a conexão
                MenuProfissão();
                break;
            case 5:
                MenuPrincipal();
        }
    }
}
