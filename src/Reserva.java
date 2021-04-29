public class Reserva implements Pagamento{
    Cliente cliente;
    boolean pagamentoAVista;

    public Reserva(Cliente cliente, boolean pagamentoAVista){
        this.cliente = cliente;
        this.pagamentoAVista = pagamentoAVista;
    }

    @Override
    public String toString(){
        return "Reserva={"
                + "TipoCliente:'" + cliente.getClass().getName() + "', "
                + "Nome:'" + cliente.nome + "', "
                + "FormaPagamento:'" + (pagamentoAVista ? "À vista" : "Parcelado") + "'}";
    }

    
    @Override
    public double calcularPagamento() {
        
        return pagamentoAVista ? 3200.00 * 0.9 : 3200.00;
    }
}
