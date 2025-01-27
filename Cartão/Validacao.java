import java.util.regex.Pattern;

public class Validacao {

    public static boolean validarCpf(String cpf) {
        return cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}");
    }

    public static boolean validarTelefone(String telefone) {
        return telefone.isEmpty() || telefone.matches("\\(\\d{2}\\) \\d{4,5}-\\d{4}");
    }

    public static boolean validarEmail(String email) {
        return email.isEmpty() || Pattern.compile("^[A-Za-z0-9+_.-]+@[a-z0-9.-]+$").matcher(email).matches();
    }
}
