package controller.servlet;

import bo.BoFactory;
import bo.custom.CustomerBO;
import dto.CustomerDTO;

import javax.annotation.Resource;
import javax.json.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(urlPatterns = "/customer")
public class CustomerServlet extends HttpServlet {
    CustomerBO customerBO = (CustomerBO) BoFactory.getBoFactory().getBo(BoFactory.BoTypes.CUSTOMER);

    @Resource(name = "java:comp/env/jdbc/pool")
    DataSource ds;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String option = req.getParameter("option");
            String customerId = req.getParameter("customerId");
            resp.setContentType("application/json");
            Connection connection = ds.getConnection();
            PrintWriter writer = resp.getWriter();

            switch (option) {
                case "SEARCH":
                    CustomerDTO customer = customerBO.ifCustomerExist(customerId,connection);
                    JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
                    jsonObjectBuilder.add("customerId", customer.getCustomerId());
                    jsonObjectBuilder.add("customerName", customer.getCustomerName());
                    jsonObjectBuilder.add("address", customer.getAddress());
                    jsonObjectBuilder.add("salary", customer.getSalary());

                    writer.print(jsonObjectBuilder.build());
                    break;

                case "GETALL":
                    ArrayList<CustomerDTO> allCustomers = customerBO.getAllCustomer(connection);
                    JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
                    for (CustomerDTO customerDTO : allCustomers) {
                        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
                        objectBuilder.add("customerId",customerDTO.getCustomerId());
                        objectBuilder.add("customerName",customerDTO.getCustomerName());
                        objectBuilder.add("address",customerDTO.getAddress());
                        objectBuilder.add("salary", customerDTO.getSalary());

                        arrayBuilder.add(objectBuilder.build());
                    }

                    JsonObjectBuilder response = Json.createObjectBuilder();
                    response.add("status", 200);
                    response.add("message", "Done");
                    response.add("data", arrayBuilder.build());
                    writer.print(response.build());
                    break;

            }
            connection.close();

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CustomerDTO customerDTO = new CustomerDTO(
                req.getParameter("customerID"),
                req.getParameter("customerName"),
                req.getParameter("address"),
                req.getParameter("salary")
        );

        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json");

        try {
            Connection connection = ds.getConnection();
            if (customerBO.addCustomer(customerDTO, connection)) {

                JsonObjectBuilder response = Json.createObjectBuilder();
                resp.setStatus(HttpServletResponse.SC_OK);
                response.add("message", "Customer Successfully Added.");
                response.add("status", resp.getStatus());
                writer.print(response.build());

            }
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {

            JsonObjectBuilder response = Json.createObjectBuilder();
            response.add("status", 400);
            response.add("message", "Error");
            response.add("data", e.getLocalizedMessage());
            writer.print(response.build());

            resp.setStatus(HttpServletResponse.SC_OK);
            e.printStackTrace();
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonReader reader = Json.createReader(req.getReader());
        JsonObject jsonObject = reader.readObject();
        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json");

        CustomerDTO customerDTO = new CustomerDTO(
                jsonObject.getString("customerId"),
                jsonObject.getString("customerName"),
                jsonObject.getString("address"),
                jsonObject.getString("salary")
        );

        try {
            Connection connection = ds.getConnection();
            if (customerBO.updateCustomer(customerDTO, connection)) {

                JsonObjectBuilder response = Json.createObjectBuilder();
                resp.setStatus(HttpServletResponse.SC_OK);
                response.add("message", "Updated Customer Successfully..");
                response.add("status", resp.getStatus());
                writer.print(response.build());

            }
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {

            JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
            jsonObjectBuilder.add("status", 400);
            jsonObjectBuilder.add("message", "Error");
            jsonObjectBuilder.add("data", e.getLocalizedMessage());
            writer.print(jsonObjectBuilder.build());

            resp.setStatus(HttpServletResponse.SC_OK);
            e.printStackTrace();
        }


    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String customerID = req.getParameter("customerId");
        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json");

        try {
            Connection connection = ds.getConnection();
            if (customerBO.deleteCustomer(customerID,connection)) {
                JsonObjectBuilder response = Json.createObjectBuilder();
                resp.setStatus(HttpServletResponse.SC_OK);
                response.add("message","Deleted Customer Successfully..");
                response.add("status",resp.getStatus());
                writer.print(response.build());

            }else {
                JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
                objectBuilder.add("message","Wrong Id Inserted.");
                objectBuilder.add("status",400);
                writer.print(objectBuilder.build());

            }
            connection.close();

        } catch (SQLException | ClassNotFoundException e) {
            resp.setStatus(HttpServletResponse.SC_OK);

            JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
            objectBuilder.add("data",e.getLocalizedMessage());
            objectBuilder.add("message","Error");
            objectBuilder.add("status",resp.getStatus());
            writer.print(objectBuilder.build());

            e.printStackTrace();
        }
    }

}
