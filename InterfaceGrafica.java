import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class InterfaceGrafica extends Application {

    private ArrayList<Cliente> clientes = new ArrayList<>();
    private ListView<Cliente> listaClientes = new ListView<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));

        TextField campoNome = new TextField();
        campoNome.setPromptText("Nome completo");

        TextField campoCpf = new TextField();
        campoCpf.setPromptText("CPF (ex: 123.456.789-00)");

        TextField campoEndereco = new TextField();
        campoEndereco.setPromptText("Endereço");

        TextField campoTelefone = new TextField();
        campoTelefone.setPromptText("Telefone (ex: (11) 98765-4321)");

        TextField campoEmail = new TextField();
        campoEmail.setPromptText("E-mail");

        Button botaoSalvar = new Button("Salvar");
        Button botaoExcluir = new Button("Excluir");

        listaClientes.setPrefHeight(200);

        botaoSalvar.setOnAction(e -> salvarCliente(campoNome, campoCpf, campoEndereco, campoTelefone, campoEmail));
        botaoExcluir.setOnAction(e -> excluirCliente());

        layout.getChildren().addAll(
                campoNome, campoCpf, campoEndereco, campoTelefone, campoEmail,
                botaoSalvar, botaoExcluir, new Label("Clientes cadastrados:"), listaClientes
        );

        Scene scene = new Scene(layout, 400, 500);
        primaryStage.setTitle("Cadastro de Clientes");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void salvarCliente(TextField campoNome, TextField campoCpf, TextField campoEndereco,
                              TextField campoTelefone, TextField campoEmail) {
        String nome = campoNome.getText();
        String cpf = campoCpf.getText();
        String endereco = campoEndereco.getText();
        String telefone = campoTelefone.getText();
        String email = campoEmail.getText();

        if (nome.isEmpty() || cpf.isEmpty() || endereco.isEmpty()) {
            exibirAlerta("Erro", "Nome, CPF e Endereço são obrigatórios.", Alert.AlertType.ERROR);
            return;
        }

        if (!Validacao.validarCpf(cpf)) {
            exibirAlerta("Erro", "CPF inválido.", Alert.AlertType.ERROR);
            return;
        }

        if (!Validacao.validarTelefone(telefone)) {
            exibirAlerta("Erro", "Telefone inválido.", Alert.AlertType.ERROR);
            return;
        }

        if (!Validacao.validarEmail(email)) {
            exibirAlerta("Erro", "E-mail inválido.", Alert.AlertType.ERROR);
            return;
        }

        clientes.add(new Cliente(nome, cpf, endereco, telefone, email));
        listaClientes.getItems().setAll(clientes);
        exibirAlerta("Sucesso", "Cliente cadastrado com sucesso!", Alert.AlertType.INFORMATION);

        campoNome.clear();
        campoCpf.clear();
        campoEndereco.clear();
        campoTelefone.clear();
        campoEmail.clear();
    }

    private void excluirCliente() {
        Cliente selecionado = listaClientes.getSelectionModel().getSelectedItem();
        if (selecionado != null) {
            clientes.remove(selecionado);
            listaClientes.getItems().setAll(clientes);
            exibirAlerta("Sucesso", "Cliente excluído com sucesso!", Alert.AlertType.INFORMATION);
        } else {
            exibirAlerta("Erro", "Nenhum cliente selecionado.", Alert.AlertType.ERROR);
        }
    }

    private void exibirAlerta(String titulo, String mensagem, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }
}



