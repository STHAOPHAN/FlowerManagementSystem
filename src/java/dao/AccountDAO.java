/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Account;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.servlet.http.Cookie;
import utils.DBUtils;

/**
 *
 * @author Admin
 */
public class AccountDAO {

    public static Account getAccount(String email, String password) {
        Connection cn = null;
        Account acc = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT accID,email,password,fullname,phone,status,role,token\n"
                        + "FROM dbo.Accounts\n"
                        + "WHERE status = 1 and email = ? and password = ? COLLATE Latin1_General_CS_AS";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, email);
                pst.setString(2, password);
                ResultSet rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    int AccID = rs.getInt("accID");
                    String Email = rs.getString("email");
                    String Password = rs.getString("password");
                    String FullName = rs.getString("fullname");
                    String Phone = rs.getString("phone");
                    int Status = rs.getInt("status");
                    int Role = rs.getInt("role");
                    String Token = rs.getString("token");
                    acc = new Account(AccID, Email, Password, FullName, Status, Phone, Role, Token);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return acc;
    }

    public static boolean updateAccountStatus(String email, int status) {
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "update Accounts\n"
                        + "set status = ?\n"
                        + "where email = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, status);
                pst.setString(2, email);
                pst.executeUpdate();
                cn.close();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean updateAccount(String email, String fullname, String phone) {
        Connection cn = null;
        boolean result = false;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                if (phone != null) {
                    String sql = "update Accounts\n"
                            + "set phone = ?\n"
                            + "where email =?";
                    PreparedStatement pst = cn.prepareStatement(sql);
                    pst.setString(1, phone);
                    pst.setString(2, email);
                    pst.executeUpdate();
                    return true;
                } else if (fullname != null) {
                    String sql = "update Accounts\n"
                            + "set fullname = ?\n"
                            + "where email =?";
                    PreparedStatement pst = cn.prepareStatement(sql);
                    pst.setString(1, fullname);
                    pst.setString(2, email);
                    pst.executeUpdate();
                    return true;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean updateAccount(String email, String newPassword, String newFullname, String newPhone) {
        Connection cn = null;
        Account acc = null;
        boolean result = false;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "update Accounts\n"
                        + "set password = ?,fullname = ?, phone = ?, \n"
                        + "where email =?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, newPassword);
                pst.setString(2, newFullname);
                pst.setString(3, newPhone);
                pst.setString(4, email);
                pst.executeUpdate();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean insertAccount(String newEmail, String newPassword, String newFullname, String newPhone, int newSatus, int newRole) {
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "insert into dbo.Accounts(email,password,fullname,phone,status,role)\n"
                        + "values (?,?,?,?,?,?)";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, newEmail);
                pst.setString(2, newPassword);
                pst.setString(3, newFullname);
                pst.setString(4, newPhone);
                pst.setInt(5, newSatus);
                pst.setInt(6, newRole);
                pst.executeUpdate();
                cn.close();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean updateToken(String token, String email) {
        Connection cn = null;
        boolean result = false;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "update Accounts\n"
                        + "set token = ?\n"
                        + "where email =?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, token);
                pst.setString(2, email);
                pst.executeUpdate();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static Account getAccount(String token) {
        Connection cn = null;
        Account acc = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT accID,email,password,fullname,phone,status,role,token\n"
                        + "FROM dbo.Accounts\n"
                        + "WHERE token = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, token);
                ResultSet rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    int AccID = rs.getInt("accID");
                    String Email = rs.getString("email");
                    String Password = rs.getString("password");
                    String FullName = rs.getString("fullname");
                    String Phone = rs.getString("phone");
                    int Status = rs.getInt("status");
                    int Role = rs.getInt("role");
                    String Token = rs.getString("token");
                    acc = new Account(AccID, Email, Password, FullName, Status, Phone, Role, Token);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return acc;
    }

    public static ArrayList<Account> getAccounts() {
        ArrayList<Account> list = new ArrayList<>();
        Connection cn = null;
        Account acc = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select accID,email,password,fullname,phone,status,role,token\n"
                        + "from Accounts";
                PreparedStatement pst = cn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int AccID = rs.getInt("accID");
                        String Email = rs.getString("email");
                        String Password = rs.getString("password");
                        String FullName = rs.getString("fullname");
                        String Phone = rs.getString("phone");
                        int Status = rs.getInt("status");
                        int Role = rs.getInt("role");
                        String Token = rs.getString("token");
                        acc = new Account(AccID, Email, Password, FullName, Status, Phone, Role, Token);
                        list.add(acc);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static ArrayList<Account> getAccounts(String search) {
        ArrayList<Account> list = new ArrayList<>();
        Connection cn = null;
        Account acc = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select accID,email,password,fullname,phone,status,role,token\n"
                        + "from Accounts\n"
                        + "WHERE email like ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, "%" + search + "%");
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int AccID = rs.getInt("accID");
                        String Email = rs.getString("email");
                        String Password = rs.getString("password");
                        String FullName = rs.getString("fullname");
                        String Phone = rs.getString("phone");
                        int Status = rs.getInt("status");
                        int Role = rs.getInt("role");
                        String Token = rs.getString("token");
                        acc = new Account(AccID, Email, Password, FullName, Status, Phone, Role, Token);
                        list.add(acc);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public static String getEmail(int accID) {
        Connection cn = null;
        String email = "";
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select email\n"
                        + "from Accounts\n"
                        + "where accID = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, accID);
                ResultSet rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    email = rs.getString("email");
                    return email;
                }
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return email;
    }
}
