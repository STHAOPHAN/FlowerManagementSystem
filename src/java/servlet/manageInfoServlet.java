/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dao.AccountDAO;
import dao.OrderDAO;
import dao.PlantDAO;
import dto.Account;
import dto.Category;
import dto.Order;
import dto.Plant;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
public class manageInfoServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String obj = request.getParameter("object");
            if (obj.equals("accounts")) {
                ArrayList<Account> list = AccountDAO.getAccounts();
                request.setAttribute("list", list);
                request.getRequestDispatcher("manageAccounts.jsp").forward(request, response);
            } else if (obj.equals("orders")) {
                ArrayList<Order> list = OrderDAO.getOrders();
                request.setAttribute("list", list);
                request.getRequestDispatcher("manageOrders.jsp").forward(request, response);
            } else if (obj.equals("plants")) {
                ArrayList<Plant> list = PlantDAO.getPlants();
                ArrayList<Category> catelist = PlantDAO.getCategory();
                request.setAttribute("list", list);
                request.setAttribute("catelist", catelist);
                request.getRequestDispatcher("managePlants.jsp").forward(request, response);
            } else if (obj.equals("categories")) {
                ArrayList<Category> list = PlantDAO.getCategory();
                request.setAttribute("list", list);
                request.getRequestDispatcher("manageCategories.jsp").forward(request, response);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
