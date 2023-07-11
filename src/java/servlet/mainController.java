/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class mainController extends HttpServlet {
    private String url="errorpage.html";
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
            String action = request.getParameter("action");
            if (action == null || action.equals("") || action.equals("search")) {
                url="index.jsp";
            } else if (action.equals("login")){
                url="LoginServlet";
            } else if (action.equals("register")){
                url="registerServlet";
            } else if (action.equals("logout")){
                url="logoutServlet";
            } else if (action.equals("addtocart")){
                url="addToCartServlet";
            } else if (action.equals("viewcart")){
                url="viewCart.jsp";
            } else if (action.equals("update")){ //update quantity of item in cart
                url="updateCartServlet";
            } else if (action.equals("delete")){ //update item in cart
                url="deleteCartServlet";
            } else if (action.equals("Save Order")){
                url="saveShoppingCartServlet";
            } else if (action.equals("manageinfo")){ //manage accounts, orders, plants and categories
                url="manageInfoServlet";
            } else if (action.equals("updateStatusAccount")){
                url="updateStatusAccountServlet";
            } else if (action.equals("viewcartbystatus")){
                url="viewOrderByStatus.jsp";
            } else if (action.equals("changestatus")){// change status of order from processing to canceled and vice versa.
                url="reorderServlet";
            } else if (action.equals("Change")){ //Change fullname or phone of user
                url="changeProfileUserServlet";
            } else if (action.equals("Search")){ //search info accounts, orders, plants and categories
                url="searchInfoServlet";
            }  else if (action.equals("Create")){ //Create plants and categories
                url="createObjServlet";
            }  else if (action.equals("Update Info")){ //update plants and categories
                url="updateInfoServlet";
            }  else if (action.equals("deleteobj")){ //delete plants and caterogies
                url="deleteObjServlet";
            } 
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
