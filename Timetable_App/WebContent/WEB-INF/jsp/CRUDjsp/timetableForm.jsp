<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Timetable</title>
</head>
<body>
    
        <h1>Timetables Management</h1>
        <h2>
            <a href="/Timetable_App/timetable/new">Add New Timetable</a>
            &nbsp;&nbsp;&nbsp;
            <a href="/Timetable_App/timetable">List All Timetables</a>
             
        </h2>
 
    <div align="center">
    
        <c:if test="${timetable != null}">
            <form action="update" method="post">
        </c:if>
        
        <c:if test="${timetable == null}">
            <form action="insert" method="post">
        </c:if>
        
        <table border="1" >
            <caption>
              
                 <c:if test="${timetable != null}">
                     Edit Timetable
                 </c:if>
                 <c:if test="${timetable == null}">
                     Add New Timetable
                 </c:if>
             
            </caption>
                <c:if test="${timetable != null}">
                    <input type="hidden" name="id" value="<c:out value='${timetable.getTimetableId()}' />" />
                    <input type="hidden" name="user_fk" value="<c:out value='${timetable.getTimetableUserFk()}' />" />
                </c:if>           
            <tr>
            
             <tr>
                <th>nb_days : </th>
                <td>
                    <input type="number" name="nb_days" size="45"
                            value="<c:out value='${timetable.getTimetableNbDays()}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>nb_periods : </th>
                <td>
                    <input type="number" name="nb_periods" size="45"
                            value="<c:out value='${timetable.getTimetableNbPeriods()}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>hours_per_period : </th>
                <td>
                    <input type="number" name="hours_per_period" size="45"
                            value="<c:out value='${timetable.getTimetableHoursPerPeriod()}' />"
                    />
                </td>
            </tr>
           
            <tr>
                <th>description : </th>
                <td>
                    <input type="text" name="description" size="45"
                            value="<c:out value='${timetable.getTimetableDescription()}' />"
                    />
                </td>
            </tr>
            
            <tr>
                <th>free_time : </th>
                <td>
                    <input type="text" name="free_time" size="45"
                            value="<c:out value='${timetable.getTimetableFreeTime()}' />"
                    />
                </td>
            </tr>
           <!--   <tr>
                <th>summary : </th>
                <td>
                    <input type="text" name="summary" size="45"
                            value="<c:out value='${Timetable.getTimetableSummary()}' />"
                    />
                </td>
            </tr>-->
                <td colspan="2" align="center">
                    <input type="submit" value="Save" />
                </td>
            </tr>
        </table>
        </form>
    </div>   
</body>
</html>