<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<html>
<head>
    <title>lesson list</title>
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
	
    <h1>lessons Management</h1>
    <h2>
            <a href="/Timetable_App/lesson/new">Add New lesson</a>
            &nbsp;&nbsp;&nbsp;
            <a href="/Timetable_App/lesson/">List All lessons</a>
             
        </h2>
   
    <div align="center">
        <table border="1" >
            <caption>List All lessons</caption>
            <tr>
            <!-- private Long id;
			private Long teacher_fk;
			private Long class_fk;
			private Long room_fk ;
			private Long  subject_fk;
			private Long timetable_fk;
			private int total_lessons;
			private String lesson_occ;
			private String lesson_link;
			private String color;
			
			//Details select fields
			private String teacher_name;
			private String info;
			private String room_abrev;
			private String subject_abrev;
			private String class_freetime;
			private String teacher_freetime;
			private String class_color;-->
			
                <th>id</th>
                <th>teacher_name</th>
                <th>info</th>
                <th>room_abrev</th>
                <th>subject_abrev</th>
                <th>class_freetime</th>
                <th>teacher_freetime</th>
                <th>class_color</th>
                
                <th>total_lessons</th>
                <th>lesson_occ</th>
                <th>lesson_link</th>
                <th>color</th>
                
                <th>Actions</th>
            </tr>
           
            <c:forEach var="lesson" items="${listlesson}">
                <tr>
                    <td><c:out value=" ${lesson.getLessonId()} " /></td>
                    <td><c:out value=" ${lesson.getLessonTeacherName()} " /></td>
                    <td><c:out value=" ${lesson.getLessonInfo()} " /></td>
                    <td><c:out value=" ${lesson.getLessonRoomAbrev()} " /></td>
                    <td><c:out value=" ${lesson.getLessonSubjectAbrev()} " /></td>
                    <td><c:out value=" ${lesson.getLessonClassFreetime()} " /></td>
                    <td><c:out value=" ${lesson.getLessonTeacherFreetime()} " /></td>
                    <td><c:out value=" ${lesson.getLessonClassColor()} " /></td>
                    
                    <td><c:out value=" ${lesson.getTotalLessons()} " /></td>
                    <td><c:out value=" ${lesson.getLessonOcc()} " /></td>
                    <td><c:out value=" ${lesson.getLessonLink()} " /></td>
                    <td><c:out value=" ${lesson.getLessonColor()} " /></td>
                    
                    
                    
                    <td>
                        <a href="/Timetable_App/lesson/edit?id=<c:out value='${lesson.getLessonId()}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="/Timetable_App/lesson/delete?id=<c:out value='${lesson.getLessonId()}' />">Delete</a>                     
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>   
</body>
</html>