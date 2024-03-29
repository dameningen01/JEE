<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="http://localhost:8000/static/css/materialize.min.css">
    <link rel="stylesheet" href="http://localhost:8000/static/css/forms.css">
    <link rel="stylesheet" href="http://localhost:8000/static/css/style.css">
    <link href="http://localhost:8000/static/css/material icons.css" rel="stylesheet">
    <link rel="stylesheet" href="http://localhost:8000/static/css/tail.select-light-feather.min.css">
    <link rel="stylesheet" href="http://localhost:8000/static/css/font_awsome.min.css">
    <script src="http://localhost:8000/static/js/tail.select-full.min.js"></script>
    <title>Ensias Timetables</title>
</head>
<body>

		<c:if test="${!empty sessionScope.id }">
		   <%@ include file="/WEB-INF/jsp/nav/student-nav.jsp" %> 
		</c:if>
		
        <c:if test="${sessionScope.usertype == 'admin'}">
      
		   <%@ include file="../nav/admin_nav.jsp" %>
		</c:if>
		<c:if test="${sessionScope.usertype == 'prof'}">
		   <%@ include file="/WEB-INF/jsp/nav/teacher-nav.jsp" %>   
		</c:if>
	
    <h1>classes Management</h1>
    <h2>
            <a href="/Timetable_App/class/new">Add New class</a>
            &nbsp;&nbsp;&nbsp;
            <a href="/Timetable_App/class">List All classes</a>
             
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
                    <td><c:out value="${cl.getClassFacultyName()}" /></td>
                    <td><c:out value="${cl.getClassGroup()}" /></td>
                    <td><c:out value="${cl.getClassFreeTime()}" /></td>
                    <td><c:out value="${cl.getClassColor()}" /></td>
                    <td>
                        <a href="/Timetable_App/class/edit?id=<c:out value='${cl.getClassId()}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="/Timetable_App/class/delete?id=<c:out value='${cl.getClassId()}' />">Delete</a>                     
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>   
</body>
</html>