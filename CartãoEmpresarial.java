public class CartãoEmpresarial extends CartãoDeCrédito {
    private float limiteExtra;

    public CartãoEmpresarial(String emissorDoCartao, float limite, float limiteExtra) {
        super(emissorDoCartao, limite);
        this.limiteExtra = limiteExtra;
    }

    public float getLimiteExtra() {
        return this.limiteExtra;
    }

    public void setLimiteExtra(float limiteExtra) {
        this.limiteExtra = limiteExtra;
    }

    @Override
    public boolean realizarCompra(int numeroDeIdentificacao, float valor) {
        if (getSaldo() + valor <= (getLimite() + this.limiteExtra)) {
            setSaldo(getSaldo() + valor);
            registrarTransacao(new Transação("Compra Empresarial", valor));
            return true;
        } else {
            System.out.println("Limite insuficiente!");
            return false;
        }
    }

    public boolean realizarCompraParcelada(int numeroDeIdentificacao, float valor, int parcelas) {
        if (parcelas < 1) {
            System.out.println("Número de parcelas inválido!");
            return false;
        }

        float valorParcela = valor / parcelas;
        if (getSaldo() + valor <= (getLimite() + this.limiteExtra)) {
            for (int i = 0; i < parcelas; i++) {
                registrarTransacao(new Transação("Parcela " + (i + 1) + " - Compra Empresarial", valorParcela));
            }
            setSaldo(getSaldo() + valor);
            return true;
        } else {
            System.out.println("Limite insuficiente para parcelamento!");
            return false;
        }
    }
}
