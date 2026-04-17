package com.example;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.*;

public class CartServlet extends HttpServlet {//this line describe httprequest()

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)//it is used for when a user submit a form
            throws ServletException, IOException {

        String item = request.getParameter("item");//it help to read the item names from the input

        HttpSession session = request.getSession();//this stores the data per user

      
        List<String> cart = (List<String>) session.getAttribute("cart");//that help you a cart from the session and here the typecasting are required we have the object which changed to the string

        if (cart == null) {//it means 1st time user cart doest not exists
            cart = new ArrayList<>();
        }

       
        if (item != null && !item.trim().isEmpty()) {//it prevents empty or null items
            cart.add(item);
        }

     
        session.setAttribute("cart", cart);//that line help you to update the session data


        response.sendRedirect("cart");//it redirect to the /cart.jsp, it also trigger the doGet method
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)//it is called when a user visits
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        List<String> cart = (List<String>) session.getAttribute("cart");
        request.setAttribute("cartItems", cart);//pass data to the cart data to the jsp pages


        RequestDispatcher rd = request.getRequestDispatcher("/cart.jsp");//it helps to sends the request to the cart.jsp and the jsp will display the cart items
        rd.forward(request, response);
    }
}