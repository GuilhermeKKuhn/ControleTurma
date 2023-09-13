package dao;

import org.example.Profissao;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import java.util.List;

public class ProfissaoDao {

    private EntityManager em;

    public ProfissaoDao(EntityManager em){
        this.em = em;
    }

    public void conectar(){
        this.em.getTransaction().begin();
    }

    public void encerrar(){
        this.em.close();
    }

    public void salvar(Profissao profissao){
        this.em.persist(profissao);
        this.em.getTransaction().commit();
    }

    public void deletar(Profissao profissao){
        profissao = em.merge(profissao);
        em.remove(profissao);
        this.em.getTransaction().commit();
    }

    public List<Profissao> mostrarProfissao(){
        String jpql = "SELECT p from Profissao p";
        return this.em.createQuery(jpql, Profissao.class).getResultList();
    }

    public List<Profissao> consultaProfissao(String profissao){
        String jpql = "SELECT p FROM Profissao p WHERE p.profissao = :profissao";
        return this.em.createQuery(jpql, Profissao.class).setParameter("profissao", profissao).getResultList();
    }

    public Profissao consultaID(int id){
        return em.find(Profissao.class, id);
    }

    public void atualizar(Profissao profissao){
        this.em.merge(profissao);
        this.em.getTransaction().commit();
    }
}
