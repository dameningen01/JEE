<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>lesson</title>
</head>
<body>
    
        <h1>lessons Management</h1>
        <h2>
            <a href="/Timetable_App/lesson/new">Add New lesson</a>
            &nbsp;&nbsp;&nbsp;
            <a href="/Timetable_App/lesson">List All lessons</a>
             
        </h2>
 
    <div align="center">
    
        <c:if test="${lesson != null}">
            <form action="update" method="post">
        </c:if>
        
        <c:if test="${lesson == null}">
            <form action="insert" method="post">
        </c:if>
        
        <table border="1" >
            <caption>
              
                 <c:if test="${lesson != null}">
                     Edit lesson
                 </c:if>
                 <c:if test="${lesson == null}">
                     Add New lesson
                 </c:if>
                  <!-- private Long id;
			private Long teacher_fk;
			private Long class_fk;
			private Long room_fk ;
			private Long  subject_fk;
			private Long timetable_fk;
			
			private int total_lessons;
			private String lesson_occ;
			private String lesson_link;
			private String color;-->
             
            </caption>
                <c:if test="${lesson != null}">
                    <input type="hidden" name="id" value="<c:out value='${lesson.getLessonId()}' />" />
                </c:if>           
            <tr>
                <th>teacher : </th>
                <td>
            		<select id="teacher_fk" name="teacher_fk">
                        <c:forEach var="teacher" items="${listTeacher }">
                            <option value="${teacher.getTeacherId()}">${teacher.getTeacherName()}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            
            <tr>
                <th>room : </th>
                <td>
            		<select id="room_fk" name="room_fk">
                        <c:forEach var="room" items="${listRoom }">
                            <option value="${room.getRoomId()}">${room.getRoomAbrev()}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
             
            <tr>
                <th>class : </th>
                <td>
            		<select id="class_fk" name="class_fk">
                        <c:forEach var="cl" items="${listClass }">
                            <option value="${cl.getClassId()}">${cl.getClassGroup()}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <th>subject: </th>
                <td>
            		<select id="subject_fk" name="subject_fk">
                        <c:forEach var="subject" items="${listSubject }">
                            <option value="${subject.getSubjectId()}">${subject.getSubjectAbrev()}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <th>timetable: </th>
                <td>
            		<select id="timetable_fk" name="timetable_fk">
                        <c:forEach var="timetable" items="${listTimetable }">
                            <option value="${timetable.getTimetableId()}">${timetable.getTimetableId()}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
             <tr>
                <th>total_lessons : </th>
                <td>
                    <input type="number" name="total_lessons" size="45"
                            value="<c:out value='${lesson.getTotalLessons()}' />"
                        />
                </td>
            </tr>
            
             <tr>
                <th>lesson_occ : </th>
                <td>
                    <input type="text" name="lesson_occ" size="45"
                            value="<c:out value='${lesson.getLessonOcc()}' />"
                    />
                </td>
            </tr>
            
           <tr>
                <th>lesson_link : </th>
                <td>
                    <input type="text" name="lesson_link" size="45"
                            value="<c:out value='${lesson.getLessonLink()}' />"
                    />
                </td>
            </tr>
           
                <tr>
                <th>color : </th>
                <td>
                    <input type="color" name="color" size="5"
                            value="<c:out value='${lesson.getLessonColor()}' />"
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