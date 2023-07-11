/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import dto.Order;
import dto.OrderDetail;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Set;
import utils.DBUtils;

/**
 *
 * @author Admin
 */
public class OrderDAO {

    public static ArrayList<Order> getOrders() {
        ArrayList<Order> list = new ArrayList();
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select OrderID, OrdDate, shipdate, Orders.status as 'status', Accounts.accID as 'accID'\n"
                        + "from Orders, Accounts\n"
                        + "where Accounts.accID = Orders.accID";
                PreparedStatement pst = cn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int orderid = rs.getInt("OrderID");
                        String orderdate = rs.getString("OrdDate");
                        String shipdate = rs.getString("shipdate");
                        int status = rs.getInt("status");
                        int accid = rs.getInt("accID");
                        Order order = new Order(orderid, orderdate, shipdate, status, accid);
                        list.add(order);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static ArrayList<Order> searchOrders(String search) {
        ArrayList<Order> list = new ArrayList();
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select OrderID, OrdDate, shipdate, Orders.status, Orders.accID, email\n"
                        + "from Orders, Accounts\n"
                        + "WHERE Accounts.accID = Orders.accID and Accounts.email like ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, "%" + search + "%");
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int orderid = rs.getInt("OrderID");
                        String orderdate = rs.getString("OrdDate");
                        String shipdate = rs.getString("shipdate");
                        int status = rs.getInt("status");
                        int accid = rs.getInt("AccID");
                        Order order = new Order(orderid, orderdate, shipdate, status, accid);
                        list.add(order);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static ArrayList<Order> getOrders(String email) {
        ArrayList<Order> list = new ArrayList();
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select OrderID, OrdDate, shipdate, Orders.status, Accounts.accID, email\n"
                        + "from Orders, Accounts\n"
                        + "where Orders.AccID=Accounts.accID and email = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, email);
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int orderid = rs.getInt("OrderID");
                        String orderdate = rs.getString("OrdDate");
                        String shipdate = rs.getString("shipdate");
                        int status = rs.getInt("status");
                        int accid = rs.getInt("AccID");
                        Order order = new Order(orderid, orderdate, shipdate, status, accid);
                        list.add(order);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static ArrayList<OrderDetail> getOrderDetail(int orderID) {
        ArrayList<OrderDetail> list = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select DetailId, OrderID, PID, PName, price, imgPath, quantity\n"
                        + "from OrderDetails, Plants\n"
                        + "where OrderID=? and OrderDetails.FID=Plants.PID";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, orderID);
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int detailID = rs.getInt("DetailId");
                        int PlantID = rs.getInt("PID");
                        String PlantName = rs.getString("PName");
                        int price = rs.getInt("price");
                        String imgPath = rs.getString("imgPath");
                        int quantity = rs.getInt("quantity");
                        OrderDetail orderdetail = new OrderDetail(detailID, orderID, PlantID, PlantName, price, imgPath, quantity);
                        list.add(orderdetail);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static boolean inserOrder(String email, HashMap<String, Integer> cart) {
        Connection cn = null;
        boolean result = false;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                int accid = 0, orderid = 0;
                cn.setAutoCommit(false);
                String sql = "select accID\n"
                        + "from Accounts\n"
                        + "where Accounts.email = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, email);
                ResultSet rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    accid = rs.getInt("accID");
                }
                System.out.println("accountid:" + accid);
                Date d = new Date(System.currentTimeMillis());
                System.out.println("order date:" + d);
                sql = "insert Orders(OrdDate, status, AccID) values(?,?,?)";
                pst = cn.prepareStatement(sql);
                pst.setDate(1, d);
                pst.setInt(2, 1);
                pst.setInt(3, accid);
                pst.executeUpdate();
                sql = "select top 1 orderID from Orders order by orderId desc";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    orderid = rs.getInt("orderID");
                    System.out.println("orderid:" + orderid);
                    Set<String> pids = cart.keySet();
                    for (String pid : pids) {
                        sql = "insert OrderDetails values (?,?,?)";
                        pst = cn.prepareStatement(sql);
                        pst.setInt(1, orderid);
                        pst.setInt(2, Integer.parseInt(pid.trim()));
                        pst.setInt(3, cart.get(pid));
                        pst.executeUpdate();
                        cn.commit();
                        cn.setAutoCommit(true);
                    }
                    return true;
                } else {
                    System.out.println("k chen order dc");
                }
            }
        } catch (Exception e) {
            try {
                if (cn != null) {
                    cn.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                result = false;
            }
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static boolean updateOrder(String orderid, int status) {
        Connection cn = null;
        boolean result = false;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                if (status == 3) {
                    java.util.Date datenow = new java.util.Date();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String date = dateFormat.format(datenow);
                    String sql = "update Orders\n"
                            + "set status = 1,\n"
                            + " OrdDate = ?\n"
                            + "where OrderID = ?";
                    PreparedStatement pst = cn.prepareStatement(sql);
                    pst.setString(1, date);
                    pst.setString(2, orderid);
                    pst.executeUpdate();
                    return true;
                } else if (status == 1) {
                    String sql = "update Orders\n"
                            + "set status = 3\n"
                            + "where OrderID = ?";
                    PreparedStatement pst = cn.prepareStatement(sql);
                    pst.setString(1, orderid);
                    pst.executeUpdate();
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static ArrayList<Order> getOrders(String email, int statustosearch) {
        ArrayList<Order> list = new ArrayList();
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select OrderID, OrdDate, shipdate, Orders.status, Accounts.accID, email\n"
                        + "from Orders, Accounts\n"
                        + "where Orders.AccID=Accounts.accID and email = ? and Orders.status = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, email);
                pst.setInt(2, statustosearch);
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int orderid = rs.getInt("OrderID");
                        String orderdate = rs.getString("OrdDate");
                        String shipdate = rs.getString("shipdate");
                        int status = rs.getInt("status");
                        int accid = rs.getInt("AccID");
                        Order order = new Order(orderid, orderdate, shipdate, status, accid);
                        list.add(order);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static ArrayList<Order> getOrdersByDate(String email, String stringfromdate, String stringtodate) {
        ArrayList<Order> list = new ArrayList();
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date fromdate = sdf.parse(stringfromdate);
            java.sql.Date sqlfromdate = new java.sql.Date(fromdate.getTime());
            java.util.Date todate = sdf.parse(stringtodate);
            java.sql.Date sqltodate = new java.sql.Date(todate.getTime());
            if (cn != null) {
                String sql = "select OrderID, OrdDate, shipdate, Orders.status, Accounts.accID, email\n"
                        + "from Orders, Accounts\n"
                        + "where Orders.AccID=Accounts.accID and OrdDate >= ? and OrdDate <= ? and email =?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setDate(1, sqlfromdate);
                pst.setDate(2, sqltodate);
                pst.setString(3, email);
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int orderid = rs.getInt("OrderID");
                        String orderdate = rs.getString("OrdDate");
                        String shipdate = rs.getString("shipdate");
                        int status = rs.getInt("status");
                        int accid = rs.getInt("AccID");
                        Order order = new Order(orderid, orderdate, shipdate, status, accid);
                        list.add(order);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public static ArrayList<Order> getOrdersByDate(String stringfromdate, String stringtodate) {
        ArrayList<Order> list = new ArrayList();
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date fromdate = sdf.parse(stringfromdate);
            java.sql.Date sqlfromdate = new java.sql.Date(fromdate.getTime());
            java.util.Date todate = sdf.parse(stringtodate);
            java.sql.Date sqltodate = new java.sql.Date(todate.getTime());
            if (cn != null) {
                String sql = "select OrderID, OrdDate, shipdate, Orders.status, Accounts.accID, email\n"
                        + "from Orders, Accounts\n"
                        + "where Orders.AccID=Accounts.accID and OrdDate >= ? and OrdDate <= ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setDate(1, sqlfromdate);
                pst.setDate(2, sqltodate);
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int orderid = rs.getInt("OrderID");
                        String orderdate = rs.getString("OrdDate");
                        String shipdate = rs.getString("shipdate");
                        int status = rs.getInt("status");
                        int accid = rs.getInt("AccID");
                        Order order = new Order(orderid, orderdate, shipdate, status, accid);
                        list.add(order);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }    
}
