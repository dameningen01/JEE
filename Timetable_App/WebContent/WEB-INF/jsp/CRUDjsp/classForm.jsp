<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>class</title>
</head>
<body>
    
        <h1>classes Management</h1>
        <h2>
            <a href="/Timetable_App/class/new">Add New class</a>
            &nbsp;&nbsp;&nbsp;
            <a href="/Timetable_App/class">List All classes</a>
             
        </h2>
 
    <div align="center">
    
        <c:if test="${cl != null}">
            <form action="update" method="post">
        </c:if>
        
        <c:if test="${cl == null}">
            <form action="insert" method="post">
        </c:if>
        
        <table border="1" >
            <caption>
              
                 <c:if test="${cl != null}">
                     Edit class
                 </c:if>
                 <c:if test="${cl == null}">
                     Add New class
                 </c:if>
             
            </caption>
                <c:if test="${cl != null}">
                    <input type="hidden" name="id" value="<c:out value='${cl.getClassId()}' />" />
                </c:if>           
            <tr>
             <tr>
                <th>faculty_fk : </th>
                <td>
                    <input type="text" name="faculty_fk" size="45"
                            value="<c:out value='${cl.getClassFacultyFk()}' />"
                    />
                </td>
            </tr>
            
             <tr>
                <th>Group : </th>
                <td>
                    <input type="number" name="group" size="45"
                            value="<c:out value='${cl.getClassGroup()}' />"
                        />
                </td>
            </tr>
            <tr>
                <th>free_time : </th>
                <td>
                    <input type="text" name="free_time" size="45"
                            value="<c:out value='${cl.getClassFreeTime()}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>color : </th>
                <td>
                    <input type="text" name="color" size="5"
                            value="<c:out value='${cl.getClassColor()}' />"
                    />
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Save" />
                </td>
            </tr>
        </table>
        </form>
    </div>   
</body>
</html>