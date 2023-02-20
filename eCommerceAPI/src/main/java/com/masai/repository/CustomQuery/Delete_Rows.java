package com.masai.repository.CustomQuery;

import com.masai.exceptions.CategoryException;
//import org.springframework.stereotype.Component;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.PersistenceContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Delete_Rows {


//    @PersistenceContext
//    private EntityManagerFactory entityManagerFactory;


    public static void delete_rows(Integer row_id) throws CategoryException {


        try (Connection connection=new DBUtil().provideConnection()) {

            PreparedStatement preparedStatement1=connection.prepareStatement("delete from cart_products where carts_card_id=?;");
            PreparedStatement preparedStatement2=connection.prepareStatement("delete from cart where card_id=?;");

            preparedStatement1.setInt(1,row_id);
            preparedStatement2.setInt(1,row_id);

            preparedStatement1.executeUpdate();
            preparedStatement2.executeUpdate();

        } catch (SQLException sqlException) {
            throw new CategoryException("Not Working deleting row.");
        }

    }

//    public void delete_row(Integer row_id){
//
//        EntityManager entityManager=entityManagerFactory.createEntityManager();
//
//        entityManager.createNativeQuery("delete from cart_products where carts_card_id=?")
//                .setParameter(1, row_id)
//                .executeUpdate();
//
//        entityManager.createNativeQuery("delete from cart where card_id=?")
//                .setParameter(1, row_id)
//                .executeUpdate();
//
//
//    }


}
