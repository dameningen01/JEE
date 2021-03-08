<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="http://localhost:8000/static/css/materialize.min.css">
    <link rel="stylesheet" href="http://localhost:8000/static/css/forms.css">
    <link rel="stylesheet" href="http://localhost:8000/static/css/style.css">
    <link href="http://localhost:8000/static/css/material icons.css" rel="stylesheet">
    <link rel="stylesheet" href="http://localhost:8000/static/css/tail.select-light-feather.min.css">
    <link rel="stylesheet" href="http://localhost:8000/static/css/font_awsome.min.css">
    <script src="http://localhost:8000/static/js/tail.select-full.min.js"></script>
    <title>Ensias Timetables</title>
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
    
    <div style="padding-bottom:1rem; ">
				<c:if test="${timetable != null}">
                     <div style="text-align: center"><h2 style="color:#b57ebe;">Edit Timetable</h2></div>
                 </c:if>
                 <c:if test="${timetable == null}">
                      <div style="text-align: center"><h2 style="color:#b57ebe;">Create new Timetable</h2></div>
                 </c:if>
           
            <div class="divider"></div>
        </div>
    <main>
    <div class="container" style="text-align: center;">
        <div class="z-depth-1 grey lighten-4 row" style="display: inline-block; padding: 32px 48px 0px 48px; border: 1px solid #EEE;">
		

    <div class="row">

       <c:if test="${timetable != null}">
		            <form class="col s12" action="update" method="post">
		        </c:if>
		        
		        <c:if test="${timetable == null}">
		            <form class="col s12" action="insert" method="post">
		        </c:if>
		
		<c:if test="${timetable != null}">
           
            <!-- timetable_id -->
            <div class="row number hidden">
                    <div class="input-field col s6">
                      <i class="material-icons prefix">account_circle</i>
                      <input type="hidden" name="id" value="<c:out value='${timetable.getTimetableId()}' />" />
                      <label for="timetable_id">timetable_id</label>
                    </div>
                </div>
            <!-- user_id -->
            <div class="row number hidden">
                    <div class="input-field col s6">
                      <i class="material-icons prefix">account_circle</i>
                       <input type="hidden" name="user_fk" value="<c:out value='${timetable.getTimetableUserFk()}' />" /><!-- value=curent_user-->>
                      <label for="user_id">user_id</label>
                    </div>
                </div>
			         
                </c:if>   
				
            <!--description -->
            <div class="row normal-text">
                    <div class="input-field col s12">
                      <i class="material-icons prefix">account_circle</i>
                       <input id="discription" type="text" name="description" size="45"
                            value="<c:out value='${timetable.getTimetableDescription()}' />"
                    />
                      <label for="description">Description</label>
                    </div>
                  </div>

            <!-- nb_days -->
            <div class="row number hidden">
                    <div class="input-field col s6">
                      <i class="material-icons prefix">account_circle</i>
                     
					   <input  id="nb_days" type="number" name="nb_days" size="45"
                            value="<c:out value= '${timetable.getTimetableNbDays()}' />"
		           					
		        				
                            
                    />
                      <label for="nb_days">nb_days</label>
                    </div>
                </div>
            <!-- nb_periods -->
            <div class="row number hidden">
                    <div class="input-field col s6">
                      <i class="material-icons prefix">account_circle</i>
                     
					   <input  id="nb_periods" type="number" name="nb_periods" size="45"
                            value="<c:out value='${timetable.getTimetableNbPeriods()}' />"
                       />
                      <label for="nb_periods">nb_periods</label>
                    </div>
                </div>
            <!-- hours_per_period -->
            <div class="row number hidden">
                <div class="input-field col s6">
                  <i class="material-icons prefix">account_circle</i>
                  
				  <input id="hours_per_period" type="number" name="hours_per_period" size="45"
                            value="<c:out value='${timetable.getTimetableHoursPerPeriod()}' />"
                    />
                  <label for="hours_per_period">hours_per_period</label>
                </div>
            </div>
            
            <!-- summary -->
            <div class="row normal-text hidden">
                <div class="input-field col s6">
                  <i class="material-icons prefix">account_circle</i>
                  <input name="summary" id="summary" type="text" value="<c:out value='${timetable.getTimetableSummary()}' />"><!-- gets value from DB -->
                  <label for="summary">summary</label>
                </div>
            </div>

            <div class="row">
                    <div class="col s3" style="padding:1rem;">
                        <p >Freetime</p>
                    </div>
                     <div class="col s9">
                        <input type="text" name="free_time" class='hidden' id='FT_input'  value="<c:out value='${timetable.getTimetableFreeTime()}' />">
                        <table id="freetime"  class="col s6"></table>
                     </div>
                      
                     
                  </div>
            <button type="submit" class="btn wave-effect">submit</button>
        </div>
        
        
        </form>
      </div>  
</div></div>
</main>
    
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
    <script>
        document.addEventListener('DOMContentLoaded', function() {
          var sidenavs = document.querySelectorAll('.sidenav')
          for (var i = 0; i < sidenavs.length; i++){
              M.Sidenav.init(sidenavs[i]);
          }
          var collapsibles = document.querySelectorAll('.collapsible')
          for (var i = 0; i < collapsibles.length; i++){
              M.Collapsible.init(collapsibles[i]);
          }});
          /*$(document).ready(function() {
            $('input, textarea').characterCounter();
          });*/
    </script>
    <script src="http://localhost:8000/static/js/forms.js"></script>
</body>
</html>