package br.fiap.cliente;

import br.fiap.desconto.IDesconto;

import java.util.Random;

public class PessoaFisica extends Cliente {

    private String cpf;

    public PessoaFisica(String nome, String contato,String cpf) {
        super(nome, contato);
        this.cpf = cpf;
    }


    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String gerIdentificador() {
        return cpf;
    }

    @Override
    public String toString() {
        return super.toString() +", cpf = "+this.cpf;
    }


}
