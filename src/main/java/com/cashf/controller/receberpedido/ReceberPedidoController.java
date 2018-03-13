/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cashf.controller.receberpedido;

import com.cashf.dao.fornecedor.FornecedorDAO;
import com.cashf.dao.produto.ProdutoDAO;
import com.cashf.model.contasPagar.ContaPagar;
import com.cashf.model.fornecedor.Fornecedor;
import com.cashf.model.notafiscal.NotaFiscal;
import com.cashf.model.notafiscal.ProdutoNotaFiscal;
import com.cashf.model.produto.Produto;
import com.cashf.model.produto.UnidadeMedida;
import controller.GenericController;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author joao
 */
public class ReceberPedidoController implements GenericController<NotaFiscal> {

    private final ProdutoDAO produtoDAO;
    private final FornecedorDAO fornecedorDAO;
    private Fornecedor fornecedor;
    private Produto produtoAtual;
    private NotaFiscal notaFiscal;
    private ContaPagar contaPagar;
    private UnidadeMedida unidadeMedida;
    private BigDecimal valTotalNota;
    private BigDecimal valTotalProd;
    private BigDecimal valTotalIPI;
    private BigDecimal valTotalIcms;
    private BigDecimal valTotalIcmsSubst;
    private ObservableList<ProdutoNotaFiscal> listaProdutosNota;

    public ReceberPedidoController() {
        this.produtoDAO = new ProdutoDAO(Produto.class);
        this.fornecedorDAO = new FornecedorDAO(Fornecedor.class);
        this.fornecedor = new Fornecedor();
        this.produtoAtual = new Produto();
        this.listaProdutosNota = FXCollections.observableList(new ArrayList<>());
        this.contaPagar = new ContaPagar();
        this.notaFiscal = new NotaFiscal();
        this.notaFiscal.setIdNota(0l);
        this.produtoAtual.setIdProduto(0l);
        this.fornecedor.setIdFornecedor(0l);

    }

    public ContaPagar getContaPagar() {
        return contaPagar;
    }

    public void setContaPagar(ContaPagar contaPagar) {
        this.contaPagar = contaPagar;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Produto getProdutoAtual() {
        return produtoAtual;
    }

    public void setProdutoAtual(Produto produtoAtual) {
        this.produtoAtual = produtoAtual;
    }

    public ObservableList<Fornecedor> loadComboFornecedor() {
        return FXCollections.observableList(fornecedorDAO.listAll());
    }

    public ObservableList<Produto> loadComboProduto() {
        return FXCollections.observableList(produtoDAO.listAll());
    }

    public List<UnidadeMedida> loadComboUnidadeMedida() {
        return Arrays.asList(UnidadeMedida.values());
    }

    @Override
    public void insert() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void flushObject() {
        this.fornecedor = new Fornecedor();
        this.produtoAtual = new Produto();
        this.listaProdutosNota = FXCollections.observableList(new ArrayList<>());
        this.notaFiscal = new NotaFiscal();
        this.notaFiscal.setIdNota(0l);
        this.produtoAtual.setIdProduto(0l);
        this.fornecedor.setIdFornecedor(0l);
    }

    @Override
    public ObservableList<NotaFiscal> getLista() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setLista(ObservableList<NotaFiscal> lista) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setItenLista(NotaFiscal obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public NotaFiscal getNotaFiscal() {
        return notaFiscal;
    }

    public void setNotaFiscal(NotaFiscal notaFiscal) {
        this.notaFiscal = notaFiscal;
    }

    public void setNotaFiscal(long idNota, String numero_nota, LocalDate dataNota, LocalDate dataRecebimento, BigDecimal base_calc_icms, BigDecimal valor_icms, BigDecimal base_icms_subst, BigDecimal valor_icms_subst, BigDecimal outrasDespesas, BigDecimal desconto, BigDecimal valorTotalProdutos, BigDecimal valorTotalNota, String observacao) {
        this.notaFiscal = new NotaFiscal(idNota, numero_nota, fornecedor, contaPagar, dataNota, dataRecebimento, base_calc_icms, valor_icms, base_icms_subst, valor_icms_subst, outrasDespesas, desconto, valorTotalProdutos, valorTotalNota, observacao, listaProdutosNota);
    }

    public ObservableList<ProdutoNotaFiscal> getListaProdutosNota() {
        return listaProdutosNota;
    }

    public void setListaProdutosNota(ObservableList<ProdutoNotaFiscal> listaProdutosNota) {
        this.listaProdutosNota = listaProdutosNota;
    }

    public void setListaProdutosNota(Long idProdutoNotaFiscal, Integer qtdeProduto, BigDecimal valorIpi, BigDecimal valorIcmsSubst, BigDecimal valoruUnitario, BigDecimal despesas, BigDecimal descontos) {
        this.listaProdutosNota.add(new ProdutoNotaFiscal(idProdutoNotaFiscal, notaFiscal, produtoAtual, qtdeProduto, valorIpi, valorIcmsSubst, valoruUnitario, despesas, descontos, valoruUnitario.multiply(BigDecimal.valueOf(qtdeProduto.doubleValue())).subtract(descontos).add(despesas)));
    }

    public UnidadeMedida getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(UnidadeMedida unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    public BigDecimal getValTotalNota() {
        return valTotalNota;
    }

    public BigDecimal getValTotalProd() {
        return valTotalProd;
    }

    public BigDecimal getValTotalIcms() {
        return valTotalIcms;
    }

    public BigDecimal getValTotalIcmsSubst() {
        return valTotalIcmsSubst;
    }

    /**
     * Retorna o valor total do IPI para os produtos de uma Nota Fiscal Calcula
     * o valor od IPI utilizando o valor informado na Nota Fiscal do produto
     * efetuando sua soma através de um acumulador que é retornado pela função.
     *
     * @return BigDecimal contendo o valot total do IPI dos produtos.
     */
    public BigDecimal getValTotalIPI() {
        valTotalIPI = BigDecimal.ZERO;
        listaProdutosNota.forEach((prod) -> {
            valTotalIPI = valTotalIPI.add((prod.getValorIpi()));
        });
        return valTotalIPI;
    }

    /**
     * Calcula o valor total do Icms para os produtos de uma Nota Fiscal Calcula
     * o valor od ICMS utilizando a aliquota informada no cadastro do produto ou
     * utiliza o valor do ICMS Subst se informado.
     *
     */
    private void calcValTotalIcmsProd() {
        valTotalIcms = BigDecimal.ZERO;
        valTotalIcmsSubst = BigDecimal.ZERO;
        listaProdutosNota.forEach((prod) -> {
            if (prod.getValorIcmsSubst().compareTo(BigDecimal.ZERO) == 0) {
                valTotalIcms = valTotalIcms.add(((prod.getProduto().getAliquotasProduto().getAliquotaIcms().divide(BigDecimal.valueOf(100))).multiply((prod.getValorUnitario().subtract(prod.getDescontos()))).add(prod.getDespesas())).multiply(BigDecimal.valueOf(prod.getQtdeProduto())));
            } else {
                valTotalIcmsSubst = valTotalIcmsSubst.add(prod.getValorIcmsSubst());
            }
        });
    }

    private void calcValTotalProd() {
        valTotalProd = BigDecimal.ZERO;
        listaProdutosNota.forEach((prod) -> {
            valTotalProd = valTotalProd.add(prod.getValorUnitario().multiply(BigDecimal.valueOf(prod.getQtdeProduto().doubleValue())).add(prod.getDespesas()).subtract(prod.getDescontos()));
        });
    }

    public void calcValTotalNota() {
        valTotalNota = BigDecimal.ZERO;
        this.valTotalIPI = getValTotalIPI();
        calcValTotalIcmsProd();
        calcValTotalProd();
        valTotalNota=valTotalNota.add(valTotalIPI);
        valTotalNota=valTotalNota.add(valTotalIcms);
        valTotalNota=valTotalNota.add(valTotalIcmsSubst);
        valTotalNota=valTotalNota.add(valTotalProd);
    }

}
