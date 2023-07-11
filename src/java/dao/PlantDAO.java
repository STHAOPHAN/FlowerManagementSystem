/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Category;
import dto.Plant;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import utils.DBUtils;

/**
 *
 * @author Admin
 */
public class PlantDAO {

    public static ArrayList<Plant> getPlants() {
        ArrayList<Plant> list = new ArrayList();
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT PID,PName,price,imgPath,description,status,Plants.CateID as 'CateID',CateName\n"
                        + "FROM Plants join Categories on Plants.CateID=Categories.CateID\n"
                        + "where Plants.CateID = Categories.CateID";
                PreparedStatement pst = cn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int id = rs.getInt("PID");
                        String name = rs.getString("PName");
                        int price = rs.getInt("price");
                        String imgpath = rs.getString("imgPath");
                        String description = rs.getString("description");
                        int status = rs.getInt("status");
                        int cateid = rs.getInt("CateID");
                        String catename = rs.getString("CateName");
                        Plant plant = new Plant(id, name, price, imgpath, description, status, cateid, catename);
                        list.add(plant);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static ArrayList<Plant> searchPlants(String search) {
        ArrayList<Plant> list = new ArrayList();
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT PID,PName,price,imgPath,description,status,Plants.CateID as 'CateID',CateName\n"
                        + "FROM Plants join Categories on Plants.CateID=Categories.CateID\n"
                        + "where Plants.CateID = Categories.CateID and PName like ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, "%" + search + "%");
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int id = rs.getInt("PID");
                        String name = rs.getString("PName");
                        int price = rs.getInt("price");
                        String imgpath = rs.getString("imgPath");
                        String description = rs.getString("description");
                        int status = rs.getInt("status");
                        int cateid = rs.getInt("CateID");
                        String catename = rs.getString("CateName");
                        Plant plant = new Plant(id, name, price, imgpath, description, status, cateid, catename);
                        list.add(plant);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static ArrayList<Plant> getPlants(String keyword, String searchby) {
        ArrayList<Plant> list = new ArrayList();
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null && searchby != null) {
                String sql = "SELECT PID,PName,price,imgPath,description,status,Plants.CateID as 'CateID',CateName\n"
                        + "FROM Plants join Categories on Plants.CateID=Categories.CateID\n";
                if (searchby.equalsIgnoreCase("by name")) {
                    sql = sql + "WHERE Plants.PName like ?";
                } else {
                    sql = sql + "WHERE CateName like ?";
                }
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, "%" + keyword + "%");
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int id = rs.getInt("PID");
                        String name = rs.getString("PName");
                        int price = rs.getInt("price");
                        String imgpath = rs.getString("imgPath");
                        String description = rs.getString("description");
                        int status = rs.getInt("status");
                        int cateid = rs.getInt("CateID");
                        String catename = rs.getString("CateName");
                        Plant plant = new Plant(id, name, price, imgpath, description, status, cateid, catename);
                        list.add(plant);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static Plant getPlant(int pid) {
        Plant p = null;
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select PID, PName, price, imgPath, description, status, Plants.CateID as cateID, CateName\n"
                        + "from Plants, Categories\n"
                        + "where Plants.CateID = Categories.CateID and PID=?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, pid);
                ResultSet rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    pid = rs.getInt("PID");
                    String pname = rs.getString("PName");
                    int price = rs.getInt("price");
                    String imgPath = rs.getString("imgPath");
                    String des = rs.getString("description");
                    int status = rs.getInt("status");
                    int cateid = rs.getInt("cateID");
                    String cateName = rs.getString("cateName");
                    p = new Plant(pid, pname, price, imgPath, des, status, cateid, cateName);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return p;
    }

    public static ArrayList<Category> getCategory() {
        ArrayList<Category> list = new ArrayList();
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT CateID, CateName\n"
                        + "FROM Categories";
                PreparedStatement pst = cn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int cateid = rs.getInt("CateID");
                        String catename = rs.getString("CateName");
                        Category category = new Category(cateid, catename);
                        list.add(category);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
        public static Category getCategory(int cateid) {
        Category cate = null;
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select CateID, CateName\n"
                        + "from Categories\n"
                        + "where CateID = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, cateid);
                ResultSet rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    int id = rs.getInt("CateID");
                    String catename = rs.getString("CateName");
                    cate = new Category(id, catename);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return cate;
    }

    public static ArrayList<Category> searchCategory(String search) {
        ArrayList<Category> list = new ArrayList();
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT CateID, CateName\n"
                        + "FROM Categories\n"
                        + "where CateName like ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, "%" + search + "%");
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int cateid = rs.getInt("CateID");
                        String catename = rs.getString("CateName");
                        Category category = new Category(cateid, catename);
                        list.add(category);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static boolean insertPlant(String name, int price, String imgpath, String description, int status, int cateid) {
        boolean result = false;
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "insert into dbo.Plants(PName,price,imgPath,description,status,CateID)\n"
                        + "values (?,?,?,?,?,?)";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, name);
                pst.setInt(2, price);
                pst.setString(3, imgpath);
                pst.setString(4, description);
                pst.setInt(5, status);
                pst.setInt(6, cateid);
                pst.executeUpdate();
                cn.close();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean insertCategory(String catename) {
        boolean result = false;
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "insert into dbo.Categories(CateName)\n"
                        + "values (?)";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, catename);
                pst.executeUpdate();
                cn.close();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
        public static boolean updateCategory(int cateid, String catename) {
        Connection cn = null;
        boolean result = false;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "UPDATE Categories\n"
                        + "SET CateName = ?\n"
                        + "WHERE CateID = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, catename);
                pst.setInt(2, cateid);
                pst.executeUpdate();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean updatePlant(int pid, String newpname, int newprice, String newimgpath, String newdes, int newStatus, int newcateid) {
        Connection cn = null;
        boolean result = false;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "UPDATE Plants\n"
                        + "SET PName = ?, \n"
                        + "    price = ?,\n"
                        + "	imgPath = ?,\n"
                        + "	description = ?,\n"
                        + "	status = ?,\n"
                        + "	CateID = ?\n"
                        + "WHERE PID = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, newpname);
                pst.setInt(2, newprice);
                pst.setString(3, newimgpath);
                pst.setString(4, newdes);
                pst.setInt(5, newStatus);
                pst.setInt(6, newcateid);
                pst.setInt(7, pid);
                pst.executeUpdate();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean deletePlant(int pid) {
        Connection cn = null;
        boolean result = false;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "delete from Plants\n"
                        + "WHERE PID = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, pid);
                pst.executeUpdate();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }   
    
    public static boolean deleteCategory(int cateid) {
        Connection cn = null;
        boolean result = false;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "delete from Categories\n"
                        + "WHERE CateID = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, cateid);
                pst.executeUpdate();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }      
    
}
