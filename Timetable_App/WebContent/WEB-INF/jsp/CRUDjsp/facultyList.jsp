<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
    <title>faculty list</title>
</head>
<body>
	 <center>
    <h1>faculties Management</h1>
    <h2>
            <a href="faculty/new">Add New faculty</a>
            &nbsp;&nbsp;&nbsp;
            <a href="faculty/list">List All faculties</a>
             
        </h2>
    </center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List All faculties</h2></caption>
            <tr>
            <!-- private Long id;
			private String name;
			private String abrev;
			private int year;-->
                <th>id</th>
                <th>name</th>
                <th>abrev</th>
                <th>year</th>
                
                <th>Actions</th>
            </tr>
            <c:forEach var="faculty" items="${listfaculty}">
                <tr>
                    <td><c:out value="${faculty.id}" /></td>
                    <td><c:out value="${faculty.name}" /></td>
                    <td><c:out value="${faculty.abrev}" /></td>
                    <td><c:out value="${faculty.year}" /></td>
                    
                    <td>
                        <a href="faculty/edit?id=<c:out value='${faculty.id}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="faculty/delete?id=<c:out value='${faculty.id}' />">Delete</a>                     
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>   
</body>
</html>