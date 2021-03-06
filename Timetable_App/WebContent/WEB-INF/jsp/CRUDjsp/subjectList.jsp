<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<html>
<head>
    <title>subject list</title>
</head>
<body>
	
    <h1>subjects Management</h1>
    <h2>
            <a href="/Timetable_App/subject/new">Add New subject</a>
            &nbsp;&nbsp;&nbsp;
            <a href="/Timetable_App/subject/">List All subjects</a>
             
        </h2>
   
    <div align="center">
        <table border="1" >
            <caption>List All subjects</caption>
            <tr>
            <!-- private Long id;
			private String module;
			private String submodule;
			private String type;
			private String abrev;
			private String color;-->
                <th>id</th>
                <th>module</th>
                <th>submodule</th>
                <th>type</th>
                <th>abrev</th>
                <th>color</th>
                
                <th>Actions</th>
            </tr>
           
            <c:forEach var="subject" items="${listSubject}">
                <tr>
                    <td><c:out value="${subject.getSubjectId()} " /></td>
                    <td><c:out value="${subject.getSubjectModule()}" /></td>
                    <td><c:out value="${subject.getSubjectSubmodule()}" /></td>
                    <td><c:out value="${subject.getSubjectType()}" /></td>
                    <td><c:out value="${subject.getSubjectAbrev()}" /></td>
                    <td><c:out value="${subject.getSubjectColor()}" /></td>
                    
                    <td>
                        <a href="/Timetable_App/subject/edit?id=<c:out value='${subject.getSubjectId()}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="/Timetable_App/subject/delete?id=<c:out value='${subject.getSubjectId()}' />">Delete</a>                     
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>   
</body>
</html>