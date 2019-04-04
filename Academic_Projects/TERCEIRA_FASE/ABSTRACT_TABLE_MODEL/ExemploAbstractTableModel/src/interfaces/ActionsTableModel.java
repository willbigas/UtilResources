/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;

/**
 *
 * @author Alunos
 */
public interface ActionsTableModel<E> {

    public void addObject(E obj);

    public void removeObject(int indice);

    public void updateObject(int indice, E obj);

    public E getObject(int indice);

    public void addListOfObject(List<E> obj);

}
