<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<html>
<head>
    <title>Class list</title>
</head>
<body>
	
    <h1>classes Management</h1>
    <h2>
            <a href="class/new">Add New class</a>
            &nbsp;&nbsp;&nbsp;
            <a href="class/">List All classes</a>
             
        </h2>
   
    <div align="center">
        <table border="1" >
            <caption>List All classes</caption>
            <tr>
            <!--  private Long id;
			private Long faculty_fk;
			private Integer group;
			private String free_time;
			private String color;-->
                <th>id</th>
                <th>faculty</th>
                <th>group</th>
                <th>free_time</th>
                <th>color</th>
                <th>Actions</th>
            </tr>
           
            <c:forEach var="cl" items="${listClass}">
                <tr>
                    <td><c:out value=" ${cl.getClassId()} " /></td>
                    <td><c:out value="${cl.getClassFacultyFk()}" /></td>
                    <td><c:out value="${cl.getClassGroup()}" /></td>
                    <td><c:out value="${cl.getClassFreeTime()}" /></td>
                    <td><c:out value="${cl.getClassColor()}" /></td>
                    <td>
                        <a href="class/edit?id=<c:out value='${cl.getClassId()}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="class/delete?id=<c:out value='${cl.getClassId()}' />">Delete</a>                     
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>   
</body>
</html>