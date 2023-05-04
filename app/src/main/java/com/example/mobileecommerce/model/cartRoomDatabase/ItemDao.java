package com.example.mobileecommerce.model.cartRoomDatabase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mobileecommerce.model.cartRoomDatabase.entity.Item;

import java.util.List;

@Dao
public interface ItemDao {
    @Query("SELECT * FROM item")
    List<Item> getAll();

    @Query("SELECT * FROM item WHERE id IN (:itemId)")
    List<Item> loadAllByIds(int[] itemId);

    /*@Query("SELECT * FROM product WHERE id  IN (:id)")
    Item checkUser(int id);
    @Query("SELECT * FROM product WHERE meal  Like '%' || :name || '%'")
    List<Product> searchName(String name);*/
    @Insert
    void insertAll(Item... items);
    @Update
    void update(Item... items);
    @Delete
    void delete(Item items);
}
