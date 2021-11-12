package com.juliano.oselame;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class Main {

    static ArrayList<Duelist> duelists = new ArrayList<Duelist>();

    public static void main(String[] args) {

        int op = 0;

        do {
            op = menu();
            switch (op) {

                case 1:
                    cadastrarDuelistas(duelists);
                    break;

                case 2:
                    JOptionPane.showMessageDialog(null, listarDuelistas());
                    break;

                case 3:
                    duelar();
                    break;

            }

        } while (op != 4);
    }//main
    private static int menu() {
        StringBuilder menu = new StringBuilder();
        menu.append("1 - Cadastrar Duelistas" + "\n");
        menu.append("2 - Listar Duelistas" + "\n");
        menu.append("3 - Duelar" + "\n");
        menu.append("4 - SAIR");

        return Integer.parseInt(JOptionPane.showInputDialog(menu));
    }

    private static void cadastrarDuelistas(ArrayList<Duelist> duelists) {
        Duelist duelist = new Duelist();
        duelist.setName(JOptionPane.showInputDialog("Digite um nome para o Duelista"));
        duelists.add(duelist);
        JOptionPane.showMessageDialog(null, "Duelista " + duelist.getName() + " Cadastrado!!");
    }

    private static String listarDuelistas() {
        StringBuilder sb = new StringBuilder();
        sb.append("====== LISTA DE DUELISTAS CADASTRADOS ======\n");

        for (int i = 0; i < duelists.size(); i++) {
            if (duelists.get(i).alive) {
                sb.append((i + 1) + " - Duelista: " + duelists.get(i).getName() + " - Status: VIVO\n");
            } else {
                sb.append((i + 1) + " - Duelista: " + duelists.get(i).getName() + " - Status: MORTO\n");
            }
        }
        return sb.toString();
    }

    private static ArrayList<Duelist> escolherDuelistas() {
        Duelist duelist1;
        Duelist duelist2;

        int option1;
        int option2;

        boolean vivo1 = true;
        boolean vivo2 = true;

        ArrayList<Duelist> players = new ArrayList<Duelist>();

        while (vivo1) {
            JOptionPane.showMessageDialog(null, "Escolha o primeiro Duelista!");
            option1 = Integer.parseInt(JOptionPane.showInputDialog(listarDuelistas())) - 1;

            if (duelists.get(option1).alive) {
                duelist1 = duelists.get(option1);
                players.add(duelist1);
                JOptionPane.showMessageDialog(null, "Duelista #1 = " + duelist1.getName());
                vivo1 = false;
            } if (!duelists.get(option1).alive) {
                JOptionPane.showMessageDialog(null, duelists.get(option1).getName() + " já morreu!\n" + "Escolha outro!!");
            }
        }

        while (vivo2) {
            JOptionPane.showMessageDialog(null, "Escolha o segundo Duelista!");
            option2 = Integer.parseInt(JOptionPane.showInputDialog(listarDuelistas())) - 1;

            if (duelists.get(option2).alive) {
                duelist2 = duelists.get(option2);
                players.add(duelist2);
                JOptionPane.showMessageDialog(null, "Duelista #2 = " + duelist2.getName());
                vivo2 = false;
            }
            if (!duelists.get(option2).alive) {
                JOptionPane.showMessageDialog(null, duelists.get(option2).getName() + " já morreu!\n" + "Escolha outro!!");
            }

        }//while1

        return players;
    }

    private static void duelar() {
        ArrayList<Duelist> players =  escolherDuelistas();

        String shoot = "Empty";

        int count1 = 0;
        int count2 = 0;

        while (shoot.equals("Empty")) {

            JOptionPane.showMessageDialog(null, players.get(0).getName() + " Vai atirar!!");

            if (shot().equals("Empty")) {
                JOptionPane.showMessageDialog(null, "CLICK!\n" + players.get(0).getName() + " Sobreviveu");
                JOptionPane.showMessageDialog(null, players.get(1).getName() + " Vai atirar!!");
                count1++;
                if (shot().equals("Empty")) {
                    JOptionPane.showMessageDialog(null, "CLICK!\n" + players.get(1).getName() + " Sobreviveu");
                    count2++;
                } else {
                    JOptionPane.showMessageDialog(null, "BOOM!\n" + players.get(1).getName() + " Morreu");
                    shoot = "Bullet";
                    players.get(1).setAlive(false);
                    JOptionPane.showMessageDialog(null, "GAME OVER\n" + players.get(1).getName() + " não deveria ter jogado Roleta Russa!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "BOOM!\n" + players.get(0).getName() + " Morreu");
                shoot = "Bullet";
                players.get(0).setAlive(false);
                JOptionPane.showMessageDialog(null, "GAME OVER\n" + players.get(0).getName() + " não deveria ter jogado Roleta Russa!");
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(players.get(0).getName() + " Sobreviveu a " + count1 + " tiros.\n");
        sb.append(players.get(1).getName() + " Sobreviveu a " + count2 + " tiros.\n");

        JOptionPane.showMessageDialog(null, sb.toString());

    }

    public static String shot() {
        ArrayList<String> gun = new ArrayList<>();
        gun.add("Empty");
        gun.add("Empty");
        gun.add("Empty");
        gun.add("Empty");
        gun.add("Empty");
        gun.add("Bullet");

        Random r = new Random();
        int tiro = r.nextInt(gun.size());
        System.out.println(gun.get(tiro));

        return gun.get(tiro);
    }
}

