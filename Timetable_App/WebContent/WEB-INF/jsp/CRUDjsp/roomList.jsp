<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<html>
<head>
    <title>room list</title>
</head>
<body>
	
    <h1>rooms Management</h1>
    <h2>
            <a href="/Timetable_App/room/new">Add New room</a>
            &nbsp;&nbsp;&nbsp;
            <a href="/Timetable_App/room/">List All rooms</a>
             
        </h2>
   
    <div align="center">
        <table border="1" >
            <caption>List All rooms</caption>
            <tr>
            <!-- private Long id;
			private String abrev;
			private String free_time;
			private String color;-->
                <th>id</th>
                <th>abrev</th>
                <th>free_time</th>
                <th>color</th>
                
                <th>Actions</th>
            </tr>
           
            <c:forEach var="room" items="${listRoom}">
                <tr>
                    <td><c:out value=" ${room.getRoomId()} " /></td>
                    <td><c:out value="${room.getRoomAbrev()}" /></td>
                    <td><c:out value="${room.getRoomFreeTime()}" /></td>
                    <td><c:out value="${room.getRoomColor()}" /></td>
                    
                    <td>
                        <a href="/Timetable_App/room/edit?id=<c:out value='${room.getRoomId()}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="/Timetable_App/room/delete?id=<c:out value='${room.getRoomId()}' />">Delete</a>                     
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>   
</body>
</html>