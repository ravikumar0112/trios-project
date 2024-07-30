/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import Connection.DbConnection;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author admin
 */
@WebServlet(urlPatterns = {"/staff_register"})
public class staff_register extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, FileUploadException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session=request.getSession(true);
       
        try {
         
            
            String first_name="",last_name="",Mail_ID="",password="",address="";
            String saveFile="";
            int fileidnum=0,downloadcount=0,vc=0;
           // String contentType = request.getContentType();
            // Create a factory for disk-based file items
            DiskFileItemFactory factory = new DiskFileItemFactory();

// Set factory constraints
            factory.setSizeThreshold(4012);
//factory.setRepository("c:");

// Create a new file upload handler
            ServletFileUpload upload = new ServletFileUpload(factory);

// Set overall request size constraint
            //upload.setSizeMax(10024);

// Parse the request
            List items = null;
            try {
                items = upload.parseRequest(request);
            } catch (FileUploadException e) {
                e.printStackTrace();
            }
            byte[] data = null;
            String fileName = null;
// Process the uploaded items
            Iterator iter = items.iterator();
            while (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();

                if (item.isFormField()) {
                    //processFormField(item);

                    String name = item.getFieldName();
                    String value = item.getString();

                    if (name.equalsIgnoreCase("first_name")) {
                        first_name = value;
                        System.out.println("first_name" + first_name);
                    } 
                    
                  else  if (name.equalsIgnoreCase("last_name")) {
                        last_name = value;
                        System.out.println("last_name" + last_name);
                    } 
                  
                  else  if (name.equalsIgnoreCase("Mail_ID")) {
                        Mail_ID = value;
                        System.out.println("Mail_ID" + Mail_ID);
                    }
                  else  if (name.equalsIgnoreCase("password")) {
                        password = value;
                        System.out.println("password" + password);
                    }
                  else  if (name.equalsIgnoreCase("address")) {
                        address = value;
                        System.out.println("address" + address);
                    }
                    else {
                        System.out.println("ERROR");
                    }
                } else {
                    data = item.get();
                    fileName = item.getName();
                }
            }
            saveFile = fileName;
                    String path1 = request.getSession().getServletContext().getRealPath("/");
              // String patt=path.replace("\\build", "");
               
               String strPath1 = path1+"\\"+saveFile;
            File ff1 = new File(strPath1);
            FileOutputStream fileOut1 = new FileOutputStream(ff1);
            fileOut1.write(data, 0, data.length);
            fileOut1.flush();
            fileOut1.close();
        out.println(saveFile);
        /////////////////////////////////////////////////////////

FileInputStream fis11 = null;
File image1 = null;
//FileInputStream fis11 = null;
File image11 = null;
	Connection con7=null;
	PreparedStatement st7=null;
      
       
PreparedStatement st11=null;

image1 = new File(strPath1);
fis11 = new FileInputStream(image1);
        
       

     Class.forName("com.mysql.jdbc.Driver");
     con7 = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_application","root","admin");
  
 
   
     
         
String query="Select * from staff_register where Mail_ID='"+Mail_ID+"'";
System.out.println(query);
            Statement st=con7.createStatement();
ResultSet rs=st.executeQuery(query);

if(rs.next())
{
    
   session.setAttribute("msg","Already exist Please Check Values");
      response.sendRedirect("sellerhome.jsp");  
}
else
{
        st7 =con7.prepareStatement("insert into staff_register values (?,?,?,?,?,?,?)");

st7.setInt(1,0);
st7.setString(2,first_name);
st7.setString(3,last_name);
st7.setString(4,Mail_ID);
st7.setString(5,password);
st7.setString(6,address);
if(fileName != "")
        st7.setBinaryStream(7, (InputStream)fis11, (int)(image1.length()));
else
    st7.setBinaryStream(7, null);

      int i =st7.executeUpdate();
      session.setAttribute("msg","Successfully Login");
      response.sendRedirect("staf_login.jsp");
}   
      
        }
        catch(Exception e)
        {
       out.println(e);
        }
        finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (FileUploadException ex) {
            Logger.getLogger(register.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(register.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(register.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (FileUploadException ex) {
            Logger.getLogger(register.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(register.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(register.class.getName()).log(Level.SEVERE, null, ex);
        }
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
