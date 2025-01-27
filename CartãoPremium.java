public class CartãoPremium extends CartãoDeCrédito {
    private float cashback;
    private int pontosFidelidade;

    public CartãoPremium(String emissorDoCartao, float limite, float cashback) {
        super(emissorDoCartao, limite);
        this.cashback = cashback;
        this.pontosFidelidade = 0;
    }

    public float getCashback() {
        return this.cashback;
    }

    public void setCashback(float cashback) {
        this.cashback = cashback;
    }

    public int getPontosFidelidade() {
        return this.pontosFidelidade;
    }

    private void acumularPontos(float valor) {
        this.pontosFidelidade += Math.round(valor);
    }

    @Override
    public boolean realizarCompra(int numeroDeIdentificacao, float valor) {
        if (getSaldo() + valor <= getLimite()) {
            float valorComDesconto = valor - (valor * (this.cashback / 100));
            setSaldo(getSaldo() + valorComDesconto);
            registrarTransacao(new Transação("Compra com Cashback", valorComDesconto));
            acumularPontos(valor);
            return true;
        } else {
            System.out.println("Limite insuficiente!");
            return false;
        }
    }

    public boolean aplicarCashback() {
        if (this.pontosFidelidade >= 1000) {
            float desconto = 50.0f; 
            setSaldo(getSaldo() - desconto);
            registrarTransacao(new Transação("Cashback Aplicado", -desconto));
            this.pontosFidelidade -= 1000;
            return true;
        } else {
            System.out.println("Pontos insuficientes para aplicar cashback!");
            return false;
        }
    }
}

