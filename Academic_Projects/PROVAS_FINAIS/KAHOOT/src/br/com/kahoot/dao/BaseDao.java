package br.com.kahoot.dao;

import java.util.List;

/**
 *
 * @author 
 */
public interface BaseDao {

    public boolean inserir(Object objeto) throws Exception;

    public boolean update(Object objeto) throws Exception;

    public Object pesquisar(Integer id) throws Exception;

    public List<?> pesquisarTodos() throws Exception;

    public boolean excluir(Integer id) throws Exception;

}
