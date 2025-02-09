package view;

import dao.ProdutoDao;
import model.Produto;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class CaixasDeDialogo {
    public static void main(String[] args) {
        ImageIcon imageIcon = new ImageIcon("src/icons/img_1.png");
        Image image = imageIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon iconeRedimensionado = new ImageIcon(image);
        ProdutoDao produtoDao = new ProdutoDao();

       JOptionPane.showMessageDialog(null, "Bem-vindo(a) ao sistema" +
               " de gerenciamento de produtos!", "Boas-vindas", JOptionPane.INFORMATION_MESSAGE);

        String[] opcoes = {"Adicionar produto", "Buscar produto", "Atualizar produto", "Deletar Produto", "Listar produtos", "Sair"};

        int escolha;
        do {
            escolha = JOptionPane.showOptionDialog(
                    null,
                    "Escolha uma das opções:",
                    "Sistema de gerenciamento de produtos",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    iconeRedimensionado,
                    opcoes,
                    opcoes[0]
            );

            switch (escolha) {
                case 0 -> {
                    int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Informe o id do produto:"));
                    String descricao = JOptionPane.showInputDialog(null, "Informe a descrição do produto:");
                    BigDecimal preco = new BigDecimal(JOptionPane.showInputDialog(null, "Informe o preco do produto:"));
                    LocalDate dataValidade = LocalDate.parse(JOptionPane.showInputDialog(null, "Informe a data de validade do produto:"));

                    Produto produtoParaSerAdicionado = new Produto(id, descricao, preco, dataValidade);

                    produtoDao.adicionarProduto(produtoParaSerAdicionado);
                    JOptionPane.showMessageDialog(null, "Produto" + produtoParaSerAdicionado.getId() + " adicionado com sucesso!");
                }
                case 1 -> {
                    int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Informe o id do produto:"));
                    Produto produtoEncontrado = produtoDao.buscarProduto(id);
                    JOptionPane.showMessageDialog(null, produtoEncontrado);
                }
                case 2 -> {
                    int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Informe o id do produto a ser atualizado:"));
                    String descricao = JOptionPane.showInputDialog(null, "Informe  nova descrição do produto:");
                    BigDecimal preco = new BigDecimal(JOptionPane.showInputDialog(null, "Informe o novo preco do produto:"));
                    LocalDate dataValidade = LocalDate.parse(JOptionPane.showInputDialog(null, "Informe a nova data de validade do produto:"));

                    Produto produtoParaSerAtualizado = new Produto(id, descricao, preco, dataValidade);
                    produtoDao.atualizarProduto(id, produtoParaSerAtualizado);
                }
                case 3 -> {
                    int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Informe o id do produto a ser deletado (Operação permanente):"));
                    produtoDao.deletarProduto(id);
                }
            }

        } while (escolha != 5 && escolha != -1);


    }
}
