package controller.servlet;

import bo.BoFactory;
import bo.custom.ItemBO;
import dto.ItemDTO;

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

@WebServlet(urlPatterns = "/item")
public class ItemServlet  extends HttpServlet {
    ItemBO itemBO = (ItemBO) BoFactory.getBoFactory().getBo(BoFactory.BoTypes.ITEM);

    @Resource(name = "java:comp/env/jdbc/pool")
    DataSource ds;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String option = req.getParameter("option");
            String itemCode = req.getParameter("itemCode");
            resp.setContentType("application/json");
            Connection connection = ds.getConnection();
            PrintWriter writer = resp.getWriter();

            switch (option) {
                case "SEARCH":
                    ItemDTO item = itemBO.ifItemExist(itemCode,connection);
                    JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
                    jsonObjectBuilder.add("itemCode", item.getItemCode());
                    jsonObjectBuilder.add("itemName", item.getItemName());
                    jsonObjectBuilder.add("itemQty", item.getItemQty());
                    jsonObjectBuilder.add("itemPrice",item.getItemPrice());

                    writer.print(jsonObjectBuilder.build());
                    break;

                case "GETALL":
                    ArrayList<ItemDTO> allCustomers = itemBO.getAllItems(connection);
                    JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
                    for (ItemDTO itemDTO : allCustomers) {
                        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
                        objectBuilder.add("itemCode",itemDTO.getItemCode());
                        objectBuilder.add("itemName",itemDTO.getItemName());
                        objectBuilder.add("itemQty",itemDTO.getItemQty());
                        objectBuilder.add("itemPrice",itemDTO.getItemPrice());

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
        ItemDTO itemDTO = new ItemDTO(
                req.getParameter("itemCode"),
                req.getParameter("itemName"),
                req.getParameter("itemQty"),
                req.getParameter("itemPrice")
        );

        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json");

        try {
            Connection connection = ds.getConnection();
            if (itemBO.addItem(itemDTO, connection)) {

                JsonObjectBuilder response = Json.createObjectBuilder();
                resp.setStatus(HttpServletResponse.SC_OK);
                response.add("message", "Item Successfully Added.");
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

        ItemDTO itemDTO = new ItemDTO(
                jsonObject.getString("itemCode"),
                jsonObject.getString("itemName"),
                jsonObject.getString("itemQty"),
                jsonObject.getString("itemPrice")
        );

        try {
            Connection connection = ds.getConnection();
            if (itemBO.updateItem(itemDTO, connection)) {

                JsonObjectBuilder response = Json.createObjectBuilder();
                resp.setStatus(HttpServletResponse.SC_OK);
                response.add("message", "Updated Item Successfully..");
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
        String itemCode = req.getParameter("itemCode");
        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json");

        try {
            Connection connection = ds.getConnection();
            if (itemBO.deleteItem(itemCode,connection)) {
                JsonObjectBuilder response = Json.createObjectBuilder();
                resp.setStatus(HttpServletResponse.SC_OK);
                response.add("message","Deleted Item Successfully..");
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
