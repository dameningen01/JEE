<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<html>
<head>
    <title>timetables list</title>
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
	
    <h1>timetables Management</h1>
    <h2>
            <a href="/Timetable_App/timetable/new">Add New timetable</a>
            &nbsp;&nbsp;&nbsp;
            <a href="/Timetable_App/timetable/">List All timetables</a>
             
        </h2>
   
    <div align="center">
        <table border="1" >
            <caption>List All timetables</caption>
            <tr>
            <!-- 	private Long id;
			private Long user_fk;
			private int nb_days;
			private int nb_periods ;
			private int  hours_per_period;
			private String description;
			private String free_time;
			private String summary;
			-->
                <th>id</th>
                <th> user_fk</th>
                <th>nb_days</th>
                <th>nb_periods</th>
                <th>hours_per_period</th>
                <th>description</th>
                <th>free_time</th>
                <th>summary</th>
                
                <th>Actions</th>
            </tr>
           
            <c:forEach var="timetable" items="${listTimetable}">
                <tr>
                    <td><c:out value="${timetable.getTimetableId()} " /></td>
                    <td><c:out value="${timetable.getTimetableUserFk()}" /></td>
                    <td><c:out value="${timetable.getTimetableNbDays()}" /></td>
                    <td><c:out value="${timetable.getTimetableNbPeriods()}" /></td>
                    <td><c:out value="${timetable.getTimetableHoursPerPeriod()}" /></td>
                    <td><c:out value="${timetable.getTimetableDescription()}" /></td>
                    <td><c:out value="${timetable.getTimetableFreeTime()}" /></td>
                    <td><c:out value="${timetable.getTimetableSummary()}" /></td>
                    
                    <td>
                        <a href="/Timetable_App/timetable/edit?id=<c:out value='${timetable.getTimetableId()}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="/Timetable_App/timetable/delete?id=<c:out value='${timetable.getTimetableId()}' />">Delete</a>                     
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>   
</body>
</html>