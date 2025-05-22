package br.fiap.controle;

import br.fiap.assento.Assento;
import br.fiap.cliente.Cliente;
import br.fiap.cliente.PessoaFisica;
import br.fiap.cliente.PessoaJuridica;
import br.fiap.reserva.Reserva;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Controle {
    List<Reserva> listaReservas = new ArrayList<>();

    public void menu(){
        String menu = """
                1. Reservar
                2. Pesquisar reserva
                3. Cancelar reserva
                4. Finalizar
                """;
        int opcao = 0;
        while (true){
            opcao =  Integer.parseInt(JOptionPane.showInputDialog(menu));
            switch (opcao){
                case 1:
                    reservar();
                    break;
                case 2:
                    pesquisarReserva();
                    break;
                case 3:
                    cancelarReserva();
                    break;
                case 4:
                    return;
                default:
                    JOptionPane.showMessageDialog(null,"Opção invalida!");
            }
        }

    }

    private void cancelarReserva() {
        String cpf = JOptionPane.showInputDialog("Digite o nome");
        String contato = JOptionPane.showInputDialog("Digite o contato");
        for (Reserva r : listaReservas) {
            if (cpf.equalsIgnoreCase(r.getCliente().getNome()) && contato.equalsIgnoreCase(r.getCliente().getContato())) {
                listaReservas.remove(r);
                JOptionPane.showMessageDialog(null,"Reserva cancelada!");
                break;
            }else{
                JOptionPane.showMessageDialog(null,"Não foi possivel cancelar a reserva.");
            }
        }
    }

    private void pesquisarReserva() {
        String identificador = JOptionPane.showInputDialog("Digite o identificador do cliente");
        for(Reserva r : listaReservas){
            if(r.getCliente().gerIdentificador().equals(identificador)){
                JOptionPane.showMessageDialog(null,r);
                return;
            }else{
                JOptionPane.showMessageDialog(null,"Essa reserva não foi encontrada.");
            }
        }
    }

    private void reservar() {
        int opcao = 0;
        PessoaFisica pessoaFisica;
        PessoaJuridica pessoaJuridica;
        Reserva reserva;
        Assento assento;
        opcao = Integer.parseInt(JOptionPane.showInputDialog("""
                1. Pessoa Fisica.
                2. Pessoa Juridica.
                """));
        while (opcao != 1 && opcao != 2){
            JOptionPane.showMessageDialog(null,"Opção invalida!");
            opcao = Integer.parseInt((JOptionPane.showInputDialog("""
                1. Pessoa Fisica.
                2. Pessoa Juridica.
                """)));
        }

        if (opcao == 1){
            String nome = JOptionPane.showInputDialog("Informe o nome");
            String contato = JOptionPane.showInputDialog("Informe o número de contato");
            String cpf = JOptionPane.showInputDialog("Informe o cpf");
            pessoaFisica = new PessoaFisica(nome,contato,cpf);
            int numero= 0;
            boolean assentoDisponivel = false;
            while (!assentoDisponivel) {
                numero = Integer.parseInt(JOptionPane.showInputDialog("Informe o número do assento"));
                assentoDisponivel = true;
                for (Reserva r : listaReservas) {
                    if (numero == r.getAssento().getNumero()) {
                        assentoDisponivel = false;
                        JOptionPane.showMessageDialog(null, "Esse assento já está reservado!");
                        break;
                    }
                }
                if (assentoDisponivel) {
                    JOptionPane.showMessageDialog(null, "Número do assento aceito!");
                }
            }
            assento = new Assento(numero);
            double valor = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor do assento"));
            reserva = new Reserva(pessoaFisica,assento,valor);
            listaReservas.add(reserva);
            JOptionPane.showMessageDialog(null,"Reserva feita com sucesso!");

        }else if(opcao == 2) {
            String nome = JOptionPane.showInputDialog("Informe o nome");
            String contato = JOptionPane.showInputDialog("Informe o número de contato");
            String cnpj = JOptionPane.showInputDialog("Informe o cpf");
            pessoaJuridica = new PessoaJuridica(nome,contato,cnpj);
            int numero = 0;
            boolean assentoDisponivel = false;
            while (!assentoDisponivel) {
                numero = Integer.parseInt(JOptionPane.showInputDialog("Informe o número do assento"));
                assentoDisponivel = true;

                for (Reserva r : listaReservas) {
                    if (numero == r.getAssento().getNumero()) {
                        assentoDisponivel = false;
                        JOptionPane.showMessageDialog(null, "Esse assento já está reservado!");
                        break;
                    }
                }

                if (assentoDisponivel) {
                    JOptionPane.showMessageDialog(null, "Número do assento aceito!");
                }
            }
            assento = new Assento(numero);
            double valor = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor do assento"));
            double valorFinal = pessoaJuridica.aplicarDesconto(valor);
            reserva = new Reserva(pessoaJuridica,assento,valor);
            listaReservas.add(reserva);
            JOptionPane.showMessageDialog(null,"Reserva feita com sucesso!");
        }

    }



}
