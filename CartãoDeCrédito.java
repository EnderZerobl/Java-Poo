import java.util.ArrayList;

public class CartãoDeCrédito {
    private String emissorDoCartao;
    private int numeroDeIdentificacao;
    private float saldo;
    private float limite;
    private ArrayList<Transação> historicoTransacoes;

    public CartãoDeCrédito(String emissorDoCartao, float limite) {
        this.emissorDoCartao = emissorDoCartao;
        this.limite = limite;
        this.saldo = 0;
        this.historicoTransacoes = new ArrayList<>();
    }

    public int getNumeroDeIdentificacao() {
        return numeroDeIdentificacao;
    }

    public void setNumeroDeIdentificacao(int numeroDeIdentificacao) {
        this.numeroDeIdentificacao = numeroDeIdentificacao;
    }

    public String getEmissorDoCartao() {
        return emissorDoCartao;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public float getLimite() {
        return limite;
    }

    public void setLimite(float limite) {
        this.limite = limite;
    }

    public ArrayList<Transação> getHistoricoTransacoes() {
        return historicoTransacoes;
    }

    public void registrarTransacao(Transação transacao) {
        this.historicoTransacoes.add(transacao);
    }

    public boolean realizarCompra(int numeroDeIdentificacao, float valor) {
        if (saldo + valor <= limite) {
            saldo += valor;
            registrarTransacao(new Transação("Compra Básica", valor));
            return true;
        } else {
            System.out.println("Limite insuficiente!");
            return false;
        }
    }

    public void verificarCartao() {
        System.out.println("Emissor: " + this.emissorDoCartao);
        System.out.println("Número: " + this.numeroDeIdentificacao);
        System.out.println("Saldo: " + this.saldo);
        System.out.println("Limite: " + this.limite);
        System.out.println("Transações: " + this.historicoTransacoes.size());
    }
}

