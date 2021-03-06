<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>subject</title>
</head>
<body>
    
        <h1>subjects Management</h1>
        <h2>
            <a href="/Timetable_App/subject/new">Add New subject</a>
            &nbsp;&nbsp;&nbsp;
            <a href="/Timetable_App/subject">List All subjects</a>
             
        </h2>
 
    <div align="center">
    
        <c:if test="${subject != null}">
            <form action="update" method="post">
        </c:if>
        
        <c:if test="${subject == null}">
            <form action="insert" method="post">
        </c:if>
        
        <table border="1" >
            <caption>
              
                 <c:if test="${subject != null}">
                     Edit subject
                 </c:if>
                 <c:if test="${subject == null}">
                     Add New subject
                 </c:if>
             
            </caption>
                <c:if test="${subject != null}">
                    <input type="hidden" name="id" value="<c:out value='${subject.getSubjectId()}' />" />
                </c:if>           
            <tr>
            
            
             <tr>
                <th>module : </th>
                <td>
                    <input type="text" name="module" size="45"
                            value="<c:out value='${subject.getSubjectModule()}' />"
                        />
                </td>
            </tr>
            
             <tr>
                <th>submodule : </th>
                <td>
                    <input type="text" name="submodule" size="45"
                            value="<c:out value='${subject.getSubjectSubmodule()}' />"
                    />
                </td>
            </tr>
           
            <tr>
                <th>type : </th>
                <td>
                    <input type="text" name="type" size="45"
                            value="<c:out value='${subject.getSubjectType()}' />"
                    />
                </td>
            </tr>
             <tr>
                <th>abrev : </th>
                <td>
                    <input type="text" name="abrev" size="45"
                            value="<c:out value='${subject.getSubjectAbrev()}' />"
                    />
                </td>
            </tr>
             <tr>
                <th>color : </th>
                <td>
                    <input type="color" name="color" size="5"
                            value="<c:out value='${subject.getSubjectColor()}' />"
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