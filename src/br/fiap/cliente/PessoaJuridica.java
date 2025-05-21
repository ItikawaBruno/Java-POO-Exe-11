package br.fiap.cliente;

import br.fiap.desconto.IDesconto;

import java.util.Random;

public class PessoaJuridica extends Cliente implements IDesconto {

    private String cnpj;

    public PessoaJuridica(String nome, String contato, String cnpj) {
        super(nome, contato);
        this.cnpj = cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }


    @Override
    public String gerIdentificador() {
        return cnpj;
    }

    @Override
    public double aplicarDesconto(double valorOriginal) {
        Random random = new Random();
        double desconto = 0;
        desconto = valorOriginal - (random.nextDouble(0.01,0.99) * valorOriginal);
        return desconto;
    }

    @Override
    public String toString(){
        return super.toString() + ", cnpj = "+this.cnpj;
    }

}
