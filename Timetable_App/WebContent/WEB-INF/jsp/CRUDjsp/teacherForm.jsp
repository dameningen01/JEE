<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>teacher</title>
</head>
<body>
    
        <h1>teachers Management</h1>
        <h2>
            <a href="/Timetable_App/teacher/new">Add New teacher</a>
            &nbsp;&nbsp;&nbsp;
            <a href="/Timetable_App/teacher">List All teachers</a>
             
        </h2>
 
    <div align="center">
    
        <c:if test="${teacher != null}">
            <form action="update" method="post">
        </c:if>
        
        <c:if test="${teacher == null}">
            <form action="insert" method="post">
        </c:if>
        
        <table border="1" >
            <caption>
              
                 <c:if test="${teacher != null}">
                     Edit teacher
                 </c:if>
                 <c:if test="${teacher == null}">
                     Add New teacher
                 </c:if>
             
            </caption>
                <c:if test="${teacher != null}">
                    <input type="hidden" name="id" value="<c:out value='${teacher.getTeacherId()}' />" />
                    <input type="hidden" name="user_fk" value="<c:out value='${teacher.getTeacherUserFk()}' />" />
                </c:if>           
            <tr>
            
             <tr>
                <th>name : </th>
                <td>
                    <input type="text" name="name" size="45"
                            value="<c:out value='${teacher.getTeacherName()}' />"
                    />
                </td>
            </tr>
           
            <tr>
                <th>free_time : </th>
                <td>
                    <input type="text" name="free_time" size="45"
                            value="<c:out value='${teacher.getTeacherFreeTime()}' />"
                    />
                </td>
            </tr>
            
            <tr>
                <th>color : </th>
                <td>
                    <input type="text" name="color" size="45"
                            value="<c:out value='${teacher.getTeacherColor()}' />"
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