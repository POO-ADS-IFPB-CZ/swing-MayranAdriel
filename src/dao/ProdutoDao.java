package dao;

import model.Produto;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class ProdutoDao {

    private File arquivo;

    public ProdutoDao() {
        arquivo = new File("Produtos.txt");
        if (!arquivo.exists()){
            try {
                arquivo.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public Set<Produto> getProdutos() {
        Set<Produto> produtos = new HashSet<>();
        if (arquivo.length() == 0) {
            return produtos;
        }

        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(arquivo))) {
            produtos = (Set<Produto>) in.readObject();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return produtos;
    }

    private void atualizarArquivo(Set<Produto> produtos) {
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(arquivo))){
            out.writeObject(produtos);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Produto buscarProduto(int id) {
        Set<Produto> produtos = getProdutos();

        for(Produto produtoAtual : produtos) {
            if (produtoAtual.getId() == id) {
                return produtoAtual;
            }
        }

        return null;
    }

    public boolean adicionarProduto(Produto produto) {
        Set<Produto> produtos = getProdutos();

        if (produtos.add(produto)) {
            atualizarArquivo(produtos);
            return true;
        }

        return false;
    }

    public boolean atualizarProduto(int id, Produto produto) {
        Set<Produto> produtos = getProdutos();

        Produto produtoParaRemover = null;

        for (Produto produtoAtual : produtos) {
            if (produtoAtual.getId() == id) {
                produtoParaRemover = produtoAtual;
                break;
            }
        }

        if (produtoParaRemover != null) {
            produtos.remove(produtoParaRemover);
            produtos.add(produto);
            atualizarArquivo(produtos);
            return true;
        }

        return false;
    }

    public boolean deletarProduto(int id) {
        Set<Produto> produtos = getProdutos();

        Produto produtoParaRemover = null;

        for (Produto produtoAtual : produtos) {
            if (produtoAtual.getId() == id) {
                produtoParaRemover = produtoAtual;
                break;
            }
        }

        if (produtoParaRemover != null) {
            produtos.remove(produtoParaRemover);
            atualizarArquivo(produtos);
            return true;
        }
        return false;
    }
}
