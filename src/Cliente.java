abstract class Cliente {
    String nome;

    public Cliente(String nome){
        this.nome = nome;
    }

    @Override
    public String toString(){
        return "Cliente={"+
                "nome:'" + nome + "'}";
    }
    
    public abstract String getCodigo();
}
