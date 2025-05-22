package br.fiap.reserva;

import br.fiap.assento.Assento;
import br.fiap.cliente.Cliente;
import br.fiap.desconto.IDesconto;

public class Reserva {

    private IDesconto desconto;
    private Cliente cliente;
    private Assento assento;
    private double valorOriginal;
    private double valorFinal;

    public Reserva(Cliente cliente, Assento assento, double valorOriginal) {
        this.cliente = cliente;
        this.assento = assento;
        this.valorOriginal = valorOriginal;
        calcularDesconto();
    }

    private void calcularDesconto() {
        if(cliente instanceof IDesconto){
            valorFinal = ((IDesconto) cliente).aplicarDesconto(valorOriginal);
        }else{
            valorFinal = valorOriginal;
        }
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Assento getAssento() {
        return assento;
    }

    public void setAssento(Assento assento) {
        this.assento = assento;
    }

    public double getValorOriginal() {
        return valorOriginal;
    }

    public void setValorOriginal(double valorOriginal) {
        this.valorOriginal = valorOriginal;
    }

    public double getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(double valorFinal) {
        this.valorFinal = valorFinal;
    }

    public IDesconto getDesconto() {
        return desconto;
    }

    public void setDesconto(IDesconto desconto) {
        this.desconto = desconto;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "cliente=" + cliente.getNome() +
                ", número do assento=" + assento.getNumero() +
                ", valorOriginal=" + valorOriginal +
                ", valorFinal=" + valorFinal +
                '}';
    }
}
