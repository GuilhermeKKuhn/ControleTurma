package org.example;

import dao.PessoaDao;
import dao.ProfissaoDao;
import util.Factory;
import java.util.Scanner;


import javax.persistence.EntityManager;


public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.MenuPrincipal();

    }
}