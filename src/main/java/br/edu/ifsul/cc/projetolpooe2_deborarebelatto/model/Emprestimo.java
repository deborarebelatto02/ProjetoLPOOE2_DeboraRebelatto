/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsul.cc.projetolpooe2_deborarebelatto.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author debor
 */
@Entity
@Table(name = "tb_emprestimo")
public class Emprestimo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_emprestimo", nullable = false)
    private java.sql.Date dataEmprestimo;

    @Column(name = "data_devolucao")
    private java.sql.Date dataDevolucao;

    @ManyToOne
    @JoinColumn(name = "livro_id", nullable = false)
    private Livro livro;

    @ManyToOne
    @JoinColumn(name = "leitor_id", nullable = false)
    private Leitor leitor;

    public Emprestimo() {
    }

    // Gera a data de empréstimo como a data atual
    public void gerarDataEmprestimo() {
        this.dataEmprestimo = java.sql.Date.valueOf(LocalDate.now());
    }

    // Gera a data de devolução com base na data de empréstimo e o número de dias para devolução
    public void gerarDataDevolucao(int diasParaDevolucao) {
        LocalDate dataDevolucaoLocal = LocalDate.now().plusDays(diasParaDevolucao);
        this.dataDevolucao = java.sql.Date.valueOf(dataDevolucaoLocal);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public java.sql.Date getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(java.sql.Date dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public java.sql.Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(java.sql.Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Leitor getLeitor() {
        return leitor;
    }

    public void setLeitor(Leitor leitor) {
        this.leitor = leitor;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataEmprestimoStr = (dataEmprestimo != null) ? dataEmprestimo.toString() : "N/A";
        String dataDevolucaoStr = (dataDevolucao != null) ? dataDevolucao.toString() : "N/A";

        return "Emprestimo{"
                + "id=" + id
                + ", dataEmprestimo=" + dataEmprestimoStr
                + ", dataDevolucao=" + dataDevolucaoStr
                + ", livro=" + livro
                + ", leitor=" + leitor
                + '}';
    }
}
