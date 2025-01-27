import java.util.ArrayList;

public class Cliente {
    private String nome;
    private String cpf;
    private boolean isEmpresa; 
    private String cnpj; 
    private String endereco; 
    private String telefone; 
    private String email; 
    private ArrayList<CartãoDeCrédito> cartoes;

    
    public Cliente(String nome, String cpf, String endereco, String telefone, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.cartoes = new ArrayList<>();
    }

    
    public Cliente(String nome, String cnpj, String endereco, String telefone, String email, boolean isEmpresa) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.cartoes = new ArrayList<>();
    }

  
    public String getNome() {
        return this.nome;
    }

    public String getCpf() {
        return this.cpf;
    }

    public String getCnpj() {
        return this.cnpj;
    }

    public String getEndereco() {
        return this.endereco;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public String getEmail() {
        return this.email;
    }

    public ArrayList<CartãoDeCrédito> getCartoes() {
        return this.cartoes;
    }

    
    public void adicionarCartao(CartãoDeCrédito cartao) {
        this.cartoes.add(cartao);
    }

    public boolean realizarCompra(int numeroDeIdentificacao, float valor) {
        for (CartãoDeCrédito cartao : cartoes) {
            if (cartao.getNumeroDeIdentificacao() == numeroDeIdentificacao) {
                return cartao.realizarCompra(numeroDeIdentificacao, valor);
            }
        }
        System.out.println("Cartão não encontrado.");
        return false;
    }

    public void listarCartoes() {
        System.out.println("Cartões de " + nome + ":");
        for (CartãoDeCrédito cartao : cartoes) {
            cartao.verificarCartao();
        }
    }

    public boolean removerCartao(int numeroDeIdentificacao) {
        for (CartãoDeCrédito cartao : cartoes) {
            if (cartao.getNumeroDeIdentificacao() == numeroDeIdentificacao) {
                cartoes.remove(cartao);
                System.out.println("Cartão removido com sucesso.");
                return true;
            }
        }
        System.out.println("Cartão não encontrado.");
        return false;
    }

    public void exibirHistoricoTransacoes() {
        System.out.println("Histórico de transações de " + nome + ":");
        for (CartãoDeCrédito cartao : cartoes) {
            System.out.println("Cartão " + cartao.getNumeroDeIdentificacao() + ":");
            for (Transação transacao : cartao.getHistoricoTransacoes()) {
                System.out.println("  - " + transacao);
            }
        }
    }

    @Override
    public String toString() {
        String tipoCliente = (cpf != null) ? "CPF: " + cpf : "CNPJ: " + cnpj;
        return "Cliente: " + nome + " (" + tipoCliente + "), Endereço: " + endereco + ", Telefone: " + telefone + ", Email: " + email;
    }
}



