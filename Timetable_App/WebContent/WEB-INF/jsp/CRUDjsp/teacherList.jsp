<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<html>
<head>
    <title>teachers list</title>
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
	
    <h1>teachers Management</h1>
    <h2>
            <a href="/Timetable_App/teacher/new">Add New teacher</a>
            &nbsp;&nbsp;&nbsp;
            <a href="/Timetable_App/teacher/">List All teachers</a>
             
        </h2>
   
    <div align="center">
        <table border="1" >
            <caption>List All teachers</caption>
            <tr>
            <!-- private Long id;
				private Long user_fk;
				private String name;
				private String free_time;
				private String color;-->
                <th>id</th>
                <th> user_fk</th>
                <th>name</th>
                <th>free_time</th>
                <th>color</th>
                
                <th>Actions</th>
            </tr>
           
            <c:forEach var="teacher" items="${listTeacher}">
                <tr>
                    <td><c:out value="${teacher.getTeacherId()} " /></td>
                    <td><c:out value="${teacher.getTeacherUserFk()}" /></td>
                    <td><c:out value="${teacher.getTeacherName()}" /></td>
                    <td><c:out value="${teacher.getTeacherFreeTime()}" /></td>
                    <td><c:out value="${teacher.getTeacherColor()}" /></td>
                    
                    <td>
                        <a href="/Timetable_App/teacher/edit?id=<c:out value='${teacher.getTeacherId()}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="/Timetable_App/teacher/delete?id=<c:out value='${teacher.getTeacherId()}' />">Delete</a>                     
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>   
</body>
</html>