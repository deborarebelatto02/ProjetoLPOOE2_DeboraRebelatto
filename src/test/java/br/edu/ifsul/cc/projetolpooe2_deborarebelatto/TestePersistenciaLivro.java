/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsul.cc.projetolpooe2_deborarebelatto;

import br.edu.ifsul.cc.projetolpooe2_deborarebelatto.dao.PersistenciaJPA;
import br.edu.ifsul.cc.projetolpooe2_deborarebelatto.model.Livro;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author debor
 */
public class TestePersistenciaLivro {

    PersistenciaJPA jpa = new PersistenciaJPA();

    public TestePersistenciaLivro() {
    }

    @Before
    public void setUp() {
        jpa.conexaoAberta();
    }

    @After
    public void tearDown() {
        jpa.fecharConexao();
    }
    
    @Test
    public void testePersistenciaLivro() throws Exception {
        Livro livro1 = new Livro();
        livro1.setTitulo("Java para Iniciantes");
        livro1.setAutor("Fulano de Tal");
        livro1.setIsbn("1234567890123");
        
        Livro livro2 = new Livro();
        livro2.setTitulo("C++ para Iniciantes");
        livro2.setAutor("Ciclano");
        livro2.setIsbn("9876543210987");
        
        Livro livro3 = new Livro();
        livro3.setTitulo("Java para Iniciantes");
        livro3.setAutor("Fulano de Tal");
        livro3.setIsbn("1357924680111");
        
        jpa.persist(livro1);
        jpa.persist(livro2);
        jpa.persist(livro3);
    }
}
