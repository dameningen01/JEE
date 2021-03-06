<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>faculty</title>
</head>
<body>
    
        <h1>faculties Management</h1>
        <h2>
            <a href="/Timetable_App/faculty/new">Add New faculty</a>
            &nbsp;&nbsp;&nbsp;
            <a href="/Timetable_App/faculty">List All faculties</a>
             
        </h2>
 
    <div align="center">
    
        <c:if test="${faculty != null}">
            <form action="update" method="post">
        </c:if>
        
        <c:if test="${faculty == null}">
            <form action="insert" method="post">
        </c:if>
        
        <table border="1" >
            <caption>
              
                 <c:if test="${faculty != null}">
                     Edit faculty
                 </c:if>
                 <c:if test="${faculty == null}">
                     Add New faculty
                 </c:if>
             
            </caption>
                <c:if test="${faculty != null}">
                    <input type="hidden" name="id" value="<c:out value='${faculty.getFacultyId()}' />" />
                </c:if>           
            <tr>
             <tr>
                <th>name : </th>
                <td>
                    <input type="text" name="name" size="45"
                            value="<c:out value='${faculty.getFacultyName()}' />"
                    />
                </td>
            </tr>
            
             <tr>
                <th>abrev : </th>
                <td>
                    <input type="text" name="abrev" size="45"
                            value="<c:out value='${faculty.getFacultyAbrev()}' />"
                        />
                </td>
            </tr>
           
            <tr>
                <th>year : </th>
                <td>
                    <input type="number" name="year" size="45"
                            value="<c:out value='${faculty.getFacultyYear()}' />"
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