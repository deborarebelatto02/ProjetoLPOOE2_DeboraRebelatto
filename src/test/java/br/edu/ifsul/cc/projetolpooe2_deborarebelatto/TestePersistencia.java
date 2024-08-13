/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsul.cc.projetolpooe2_deborarebelatto;

import br.edu.ifsul.cc.projetolpooe2_deborarebelatto.dao.PersistenciaJPA;
import org.junit.Test;

/**
 *
 * @author debor
 */
public class TestePersistencia {
    @Test
    public void testeConexao()
    {
        PersistenciaJPA jpa = new PersistenciaJPA();
        
        if (jpa.conexaoAberta())
            System.out.println("\n\nConexão com o banco de dados aberta!\n\n");
        else 
            System.out.println("\n\nFalha ao conectar ao banco de dados!\n\n");
    } 
}
