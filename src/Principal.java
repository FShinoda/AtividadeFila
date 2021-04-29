import static javax.swing.JOptionPane.*;
import static java.lang.Integer.*;

public class Principal {
    public static void main(String[] args) throws Exception {
        

        Fila<Reserva> listaReserva = new Fila<Reserva>();
        Fila<Reserva> listaEspera = new Fila<Reserva>();

        int opcao;
        int posRes = 0;

        do{
            try{
                opcao = parseInt(showInputDialog(mostrarMenu()));

                switch(opcao){
                    case 1: // Reservar mesa
                        if(posRes <= 5){
                            reservarMesa(listaReserva);
                            posRes++;
                        } else {
                            reservarMesa(listaEspera);
                            showMessageDialog(null, "As reservas estão cheias, mas seu cadastro foi armazenado na lista de espera!");

                        }


                    break;
                    case 2: //Pesquisar reserva

                    break;
                    case 3: //Imprimir Reservas   

                    break;
                    case 4: //Imprimir Espera

                    break;
                    case 5: //Cancelar reserva  
                        
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
}
