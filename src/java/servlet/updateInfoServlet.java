/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dao.PlantDAO;
import dto.Category;
import dto.Plant;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
public class updateInfoServlet extends HttpServlet {

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
            boolean result;
            HttpSession session = request.getSession();
            if (obj.equals("plants")) {
                int intprice = 0;
                int pid = Integer.parseInt(request.getParameter("pid"));
                String pname = request.getParameter("txtpname");
                String price = request.getParameter("txtprice");
                String imgpath = request.getParameter("txtimgpath");
                String des = request.getParameter("txtdes");
                int status = Integer.parseInt(request.getParameter("txtstatus"));
                int cateid = Integer.parseInt(request.getParameter("txtcateid"));
                Plant plant = (Plant) session.getAttribute("plant");
                if (plant == null) {
                    response.sendRedirect("error.html");
                } else {
                    if (pname == null || pname.trim().equals("") || pname.isEmpty()) {
                        pname = plant.getName();
                    }
                    if (imgpath == null || imgpath.trim().equals("") || imgpath.isEmpty()) {
                        imgpath = plant.getImgpath();
                    }
                    if (des == null || des.trim().equals("") || des.isEmpty()) {
                        des = plant.getDescription();
                    }

                    if (price == null || price.trim().equals("") || price.isEmpty()) {
                        intprice = plant.getPrice();
                    } else {
                        intprice = Integer.parseInt(price);
                    }
                    result = PlantDAO.updatePlant(pid, pname, intprice, imgpath, des, status, cateid);
                    if (result) {
                        request.setAttribute("RESPONE", "Successfully!");
                    } else {
                        request.setAttribute("RESPONE", "update fail!");
                    }

                    request.getRequestDispatcher("updateInfo.jsp").forward(request, response);
                }
            } else if (obj.equals("categories")) {
                int cateid = Integer.parseInt(request.getParameter("cateid"));
                String catename = request.getParameter("txtcatename");
                Category cate = (Category)session.getAttribute("category");
                if (catename == null || catename.trim().equals("") || catename.isEmpty()) {
                    catename = cate.getCatename();
                }
                result = PlantDAO.updateCategory(cateid, catename);
                if (result) {
                    request.setAttribute("RESPONE", "Successfully!");
                } else {
                    request.setAttribute("RESPONE", "update fail!");
                }
                request.getRequestDispatcher("updateInfo.jsp").forward(request, response);
            }
        } catch (Exception e) {
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
