import static javax.swing.JOptionPane.*;
import static java.lang.Integer.*;

public class Principal {
    public static void main(String[] args) throws Exception {
        
        int numReservasMax = 4; // Apenas 5 mesas

        Fila<Reserva> listaReserva = new Fila<Reserva>();
        Fila<Reserva> listaEspera = new Fila<Reserva>();

        int opcao;
        int posRes = 0;

        boolean definicaoLista; // true = Reserva; false = Espera
        boolean cancelamento;

        do{
            try{
                opcao = parseInt(showInputDialog(mostrarMenu()));

                switch(opcao){
                    case 1: // Reservar mesa
                        if(posRes <= numReservasMax){
                            reservarMesa(listaReserva);
                            posRes++;
                        } else {
                            reservarMesa(listaEspera);
                            showMessageDialog(null, "As reservas estão cheias, mas seu cadastro foi armazenado na lista de espera!");

                        }


                    break;
                    case 2: //Pesquisar reserva
                        if(posRes > 0){
                            pesquisarReserva(listaReserva, listaEspera, posRes, numReservasMax);
                        } else {
                            showMessageDialog(null, "Ainda não há reservas feitas!");
                        }

                    break;
                    case 3: //Imprimir Reservas   
                        definicaoLista = true;
                        imprimir(listaReserva, listaEspera, definicaoLista);
                        
                    break;
                    case 4: //Imprimir Espera
                        definicaoLista = false;
                        imprimir(listaReserva, listaEspera, definicaoLista);

                    break;
                    case 5: //Cancelar reserva  
                        String infoCliente = showInputDialog(null, "Favor informar o CPF/CPNJ.");
                        cancelamento = cancelarReserva(listaReserva, infoCliente);  

                        if(cancelamento) {
                            Reserva aux = listaEspera.desenfileirar();
                            listaReserva.enfileirar(aux);
                        }

                    break;
                    case 6: //Finaliza o programa
                    break;

                    default:
                        showMessageDialog(null, "Opção inválida! Por favor tente novamente");
                }
                
            } catch(NumberFormatException e){
                showMessageDialog(null, "Por favor digite um número!");
                opcao = 0;
            }


        } while(opcao != 6);
    }
        

    public static String mostrarMenu(){
        String menu = "";

        menu += "1. Reservar mesa\n";
        menu += "2. Pesquisar reserva\n";
        menu += "3. Imprimir reservas\n";
        menu += "4. Imprimir lista de espera\n";
        menu += "5. Cancelar reserva\n";
        menu += "6. Finalizar\n";

        return menu;
    }

    // aux
    public static Reserva realizarPagamento(Cliente cliente){
        String formaPagamento = "";
        boolean aVista;

        do{
            formaPagamento = showInputDialog(null, "Qual a forma de pagamento?\n1.À Vista\n2.Parcelado");

        } while(formaPagamento.equals("1") != true && formaPagamento.equals("2") != true);

        if(formaPagamento.equalsIgnoreCase("1")){
            aVista = true;
        } else {
            aVista = false;
        }

        Reserva reserva = new Reserva(cliente, aVista);
        
        return reserva;
    }

    //1.
    public static void reservarMesa(Fila<Reserva> lista){
        int tipoCliente = 0;
        Reserva resAux = null;

        do{
            try{
                tipoCliente = parseInt(showInputDialog(null, "1.Pessoa Jurídica\n2.Pessoa Física"));

                if(tipoCliente != 1 && tipoCliente != 2){
                    showMessageDialog(null, "Opção inválida! Por favor tente novamente");
                }

            } catch (NumberFormatException e){
                showMessageDialog(null, "Por favor digite um número!");
            }
        } while(tipoCliente != 1 && tipoCliente != 2);

        if(tipoCliente == 1){
            String nome = showInputDialog(null, "Digite o nome da empresa:");
            String cnpj = showInputDialog(null, "Digite o cnpj");
            
            PessoaJuridica pJur = new PessoaJuridica(nome, cnpj);
            resAux = realizarPagamento(pJur);
        } else {
            String nome = showInputDialog(null, "Digite seu nome");
            String cpf = showInputDialog(null, "Digite seu CPF");

            PessoaFisica pFis = new PessoaFisica(nome, cpf);

            resAux = realizarPagamento(pFis);
        }

        // Aloca a reserva na lista
        lista.enfileirar(resAux);

    }

    //aux
    public static int procurarReserva(Fila<Reserva> lista, String codigoProcurado){

        for(int i = 0; i < lista.size(); i++){
            String codigo = lista.pegarElemento(i).cliente.getCodigo();
            if(codigo.equals(codigoProcurado)){
                return 0;
            }
        }

        return -1;
    }

    //2.
    public static void pesquisarReserva(Fila<Reserva> listaReserva, Fila<Reserva> listaEspera, int posRes, int numReservasMax){
        String codigoProcurado = showInputDialog(null, "Digite o CPF/CNPJ a ser procurado");
        
        int aux = procurarReserva(listaReserva, codigoProcurado);

        if(posRes > numReservasMax){
            aux = procurarReserva(listaEspera, codigoProcurado);
        }

        if(aux == -1){
            showMessageDialog(null, "Não foi encontrada nenhuma reserva no CPF/CNPJ " + codigoProcurado);
        } else {
            showMessageDialog(null, "Sua reserva no CPF/CNPJ " + codigoProcurado + " foi encontrada!");
        }
        
    }

    // Imprimir Listas
    public static void imprimir(Fila<Reserva> listaReserva, Fila<Reserva> listaEspera, boolean definicaoLista) {
        Fila<Reserva> _lr = listaReserva;
        Fila<Reserva> _le = listaEspera;
        Reserva dados;
        String info = "";

        if(!definicaoLista) {
            for(int idx = 0; idx < _le.size(); idx++) {
                dados = _le.pegarElemento(idx);

                if(dados != null) {
                    info += dados.toString() + "\n";
                }
            }
        } else {
            for(int idx = 0; idx < _lr.size(); idx++) {
                dados = _lr.pegarElemento(idx);
                if(dados != null) {
                    info += dados.toString() + "\n";
                }
            }
        }

        showMessageDialog(null, info);
    }

    // Cancelar Reserva
    public static boolean cancelarReserva(Fila<Reserva> lista, String infoCliente) {
        Reserva reserva;
        String dados;
        int checagem = procurarReserva(lista, infoCliente);

        if(checagem == -1) {
            showMessageDialog(null, "Não encontramos sua reserva.");
        } else {
            for(int k = 0; k < lista.size(); k++) {
                reserva = lista.pegarElemento(k);
                dados = reserva.cliente.getCodigo();
    
                if(dados.equals(infoCliente)) {
                    lista.excluirElemento(k);
                }
            }

            return true;
        }

        return false;
    }
}
