public class PessoaJuridica extends Cliente{
    String cnpj;

    public PessoaJuridica(String nome, String cnpj){
        super(nome);
        this.cnpj = cnpj;
    }

    public String toString(){
        return "PessoaJuridica={"
                + "nome:'" + nome + "', "
                + "cnpj:'" + cnpj + "'}";
    }

    @Override
    public String getCodigo(){
        return cnpj;
    }
    
}
