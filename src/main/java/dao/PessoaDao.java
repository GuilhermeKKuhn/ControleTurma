package dao;

import org.example.Pessoa;
import javax.persistence.EntityManager;
import java.util.List;

public class PessoaDao {
    private EntityManager em;

    public PessoaDao (EntityManager em){
        this.em = em;
    }

    public void Conectar(){
        this.em.getTransaction().begin();
    }

    public void Encerrar(){
        this.em.close();
    }
    public void Salvar(Pessoa pessoa){
        this.em.persist(pessoa);
        this.em.getTransaction().commit();
    }

    public void Deletar(Pessoa pessoa){
        pessoa = em.merge(pessoa);
        em.remove(pessoa);
        this.em.getTransaction().commit();
    }

    public List<Pessoa> consultar(){
        String jpql = "select p from Pessoa p";
        return this.em.createQuery(jpql,Pessoa.class).getResultList();
    }

    public List<Pessoa> consultarNome(String nome){
        String jpql = "SELECT p FROM Pessoa p WHERE p.nome = :nome";
        return this.em.createQuery(jpql,Pessoa.class).setParameter("nome",nome).getResultList();
    }

    public Pessoa consultaId(int id){
        return em.find(Pessoa.class, id);
    }

    public void Atualizar(Pessoa pessoa){
        this.em.merge(pessoa);
        this.em.getTransaction().commit();
    }

}
