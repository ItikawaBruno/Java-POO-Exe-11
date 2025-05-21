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
        int numero = Integer.parseInt(JOptionPane.showInputDialog("Digite o numero do assento"));
        for(Reserva r : listaReservas){
            if(numero == r.getAssento().getNumero()){
                JOptionPane.showMessageDialog(null,r);
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
            int numero = Integer.parseInt(JOptionPane.showInputDialog("Informe o número do assento"));
            assento = new Assento(numero);
            double valor = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor do assento"));
            double valorFinal = pessoaFisica.aplicarDesconto(valor);
            reserva = new Reserva(pessoaFisica,assento,valor, valorFinal);
            listaReservas.add(reserva);

        }else if(opcao == 2) {
            String nome = JOptionPane.showInputDialog("Informe o nome");
            String contato = JOptionPane.showInputDialog("Informe o número de contato");
            String cnpj = JOptionPane.showInputDialog("Informe o cpf");
            pessoaJuridica = new PessoaJuridica(nome,contato,cnpj);
            int numero = Integer.parseInt(JOptionPane.showInputDialog("Informe o número do assento"));
            assento = new Assento(numero);
            double valor = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor do assento"));
            double valorFinal = pessoaJuridica.aplicarDesconto(valor);
            reserva = new Reserva(pessoaJuridica,assento,valor, valorFinal);
            listaReservas.add(reserva);
        }

    }



}
