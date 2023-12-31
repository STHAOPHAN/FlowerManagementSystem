/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
public class updateCartServlet extends HttpServlet {

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
            String pid = request.getParameter("pid");
            String newquantity = request.getParameter("quantity");
            HttpSession session = request.getSession(true);
            if (session!=null) {
                HashMap<String, Integer> cart = (HashMap) session.getAttribute("cart");
                if (cart != null) {
                    boolean found = cart.containsKey(pid);
                    if (found && !newquantity.equals("0")) {
                        System.out.println(newquantity);
                        cart.put(pid, Integer.parseInt(newquantity));
                        request.setAttribute("RESPONE", "Updated");
                        session.setAttribute("cart", cart);
                        request.getRequestDispatcher("viewCart.jsp").forward(request, response);
                    } else if(newquantity.equals("0")){ //if newquantity = 0, delete item
                        cart.remove(pid);
                        request.setAttribute("RESPONE", "Deleted item (quantity=0)!");
                        session.setAttribute("cart", cart);
                        request.getRequestDispatcher("viewCart.jsp").forward(request, response);                    
                    } else{
                        request.setAttribute("RESPONE", "Update fail");
                        request.getRequestDispatcher("viewCart.jsp").forward(request, response);
                    }
                }
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
