<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>room</title>
</head>
<body>
    
        <h1>rooms Management</h1>
        <h2>
            <a href="/Timetable_App/room/new">Add New room</a>
            &nbsp;&nbsp;&nbsp;
            <a href="/Timetable_App/room">List All rooms</a>
             
        </h2>
 
    <div align="center">
    
        <c:if test="${room != null}">
            <form action="update" method="post">
        </c:if>
        
        <c:if test="${room == null}">
            <form action="insert" method="post">
        </c:if>
        
        <table border="1" >
            <caption>
              
                 <c:if test="${room != null}">
                     Edit room
                 </c:if>
                 <c:if test="${room == null}">
                     Add New room
                 </c:if>
             
            </caption>
                <c:if test="${room != null}">
                    <input type="hidden" name="id" value="<c:out value='${room.getRoomId()}' />" />
                </c:if>           
            <tr>
            
            
             <tr>
                <th>abrev : </th>
                <td>
                    <input type="text" name="abrev" size="45"
                            value="<c:out value='${room.getRoomAbrev()}' />"
                        />
                </td>
            </tr>
            
             <tr>
                <th>free_time : </th>
                <td>
                    <input type="text" name="free_time" size="45"
                            value="<c:out value='${room.getRoomFreeTime()}' />"
                    />
                </td>
            </tr>
           
            <tr>
                <th>color : </th>
                <td>
                    <input type="text" name="color" size="45"
                            value="<c:out value='${room.getRoomColor()}' />"
                    />
                </td>
            </tr>
            
                <td colspan="2" align="center">
                    <input type="submit" value="Save" />
                </td>
            </tr>
        </table>
        </form>
    </div>   
</body>
</html>