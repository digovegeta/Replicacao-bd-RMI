/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author digo_
 */
public interface IBD {
    public void createDB(String nameBD);
    public void createTable();
    public void insert(String msg);
    public String getAllData();
    public void dropDB();
}
