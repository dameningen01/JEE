<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<html>
<head>
    <title>faculty list</title>
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
	
    <h1>faculties Management</h1>
    <h2>
            <a href="/Timetable_App/faculty/new">Add New faculty</a>
            &nbsp;&nbsp;&nbsp;
            <a href="/Timetable_App/faculty/">List All faculties</a>
             
        </h2>
   
    <div align="center">
        <table border="1" >
            <caption>List All faculties</caption>
            <tr>
            <!--  private Long id;
			private String name;
			private String abrev;
			private int year;-->
                <th>id</th>
                <th>name</th>
                <th>abrev</th>
                <th>year</th>
                
                <th>Actions</th>
            </tr>
           
            <c:forEach var="faculty" items="${listFaculty}">
                <tr>
                    <td><c:out value=" ${faculty.getFacultyId()} " /></td>
                    <td><c:out value="${faculty.getFacultyName()}" /></td>
                    <td><c:out value="${faculty.getFacultyAbrev()}" /></td>
                    <td><c:out value="${faculty.getFacultyYear()}" /></td>
                    
                    <td>
                        <a href="/Timetable_App/faculty/edit?id=<c:out value='${faculty.getFacultyId()}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="/Timetable_App/faculty/delete?id=<c:out value='${faculty.getFacultyId()}' />">Delete</a>                     
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>   
</body>
</html>