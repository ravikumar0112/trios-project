<%@page import="java.sql.ResultSet"%>
<%@page import="Connection.DbConnection"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>INNOVATIVE RESIDENCE</title>
</head>
<body>
  <!--/ Nav End /-->

  <!--/ Carousel Star /-->
                     
                            <%
                                          DbConnection db=new DbConnection();
                                        ResultSet ts=db.Select("Select * from student_register");
                                        
                                        %>		
						
                                        <center>
                                            <br><h4>LAND DETAILS</h4><br>
                                        <br><table border="5">
                                            
                                            <tr><th>S_NO</th><th>FIRST NAME</th><th>LAST NAME</th><th>ADDRESS</th><th>MAIL ID</th><th>IMAGE</th></tr>                                       
                                           <%
                                        while(ts.next())
                                        {
                                            %>
                                           <tr><td><%=ts.getInt("s_no")%></td>
                                           <td><%=ts.getString("first_name")%></td>
                                           <td><%=ts.getString("last_name")%></td>
                                           <td><%=ts.getString("address")%></td>
                                           <td><%=ts.getString("Mail_ID")%></td>
                                           <td><img src="servlet_1.jsp?s_no=<%=ts.getInt("s_no")%> " style="width: 250px; height: 120px;"></td> 
                                           </tr>
                                           <%
                                        }
                                        %>
                                        </table>
                                        </center>
</body>
</html>
