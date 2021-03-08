<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

    <style>
        .hamburger{
            display: block !important;
        }
      </style>
    <header>
        <div id="hamburger_container">
          <button class="hamburger hamburger--minus" type="button">
              <a href="#" data-target="slide-out" class="sidenav-trigger">
              <span class="hamburger-box">
                <span class="hamburger-inner"></span>
              </span>
            </a>
            </button>
          </div>
    
        <div id="sidenav">
        <ul id="slide-out" class="sidenav sidenav" style="max-width:200px !important;">
          <li><div class="user-view">
              <div class="background">
                <img src="http://localhost:8000/static/img/office.jpg" style="max-height: 200px;">
              </div>
              <div class="animate fadeRight">
              <a href="#user"><img class="circle animate fadeRight" src="http://localhost:8000/static/img/yuna.jpg"></a>
              <a href="#name"><span class="white-text name">${sessionScope.username}</span></a>
             
              </div>
            </div>
          </li>
            <li><a href="/Timetable_App/"><i class="material-icons">home</i>Home</a></li>
            <li><a href="/Timetable_App/logout"><i class="material-icons">home</i>Log out</a></li>
            <li><div class="divider"></div></li>
            <li class="no-padding">
                <ul class="collapsible collapsible-accordion">
                  <li>
                    <a class="collapsible-header waves-effect">Timetables<i class="material-icons">arrow_drop_down</i></a>
                    <div class="collapsible-body">
                      <ul class="animate fadeUp">
                        <li class="collapsible-element"><a class="waves-effect" href="/Timetable_App/timetable/">Display</a></li>
                        <li class="collapsible-element"><a class="waves-effect" href="/Timetable_App/timetable/new">Create</a></li>
                      </ul>
                    </div>
                  </li>
                </ul>
              </li>
              <li class="no-padding">
                  <ul class="collapsible collapsible-accordion">
                    <li>
                      <a class="collapsible-header waves-effect">Teachers<i class="material-icons">arrow_drop_down</i></a>
                      <div class="collapsible-body">
                        <ul class="animate fadeUp">
                          <li class="collapsible-element"><a class="waves-effect" href="/Timetable_App/teacher/">Display</a></li>
                          <li class="collapsible-element"><a class="waves-effect" href="/Timetable_App/teacher/new">Create</a></li>
      
                        </ul>
                      </div>
                    </li>
                  </ul>
                </li>
                <li class="no-padding">
                    <ul class="collapsible collapsible-accordion">
                      <li>
                        <a class="collapsible-header waves-effect">Classes<i class="material-icons">arrow_drop_down</i></a>
                        <div class="collapsible-body">
                          <ul class="animate fadeUp">
                            <li class="collapsible-element"><a class="waves-effect" href="/Timetable_App/class/">Display</a></li>
                            <li class="collapsible-element"><a class="waves-effect" href="/Timetable_App/class/new">Create</a></li>
                          </ul>
                        </div>
                      </li>
                    </ul>
                  </li>
            <li class="no-padding">
                <ul class="collapsible collapsible-accordion">
                  <li>
                    <a class="collapsible-header waves-effect">Rooms<i class="material-icons">arrow_drop_down</i></a>
                    <div class="collapsible-body">
                      <ul class="animate fadeUp">
                        <li class="collapsible-element"><a class="waves-effect" href="/Timetable_App/room/">Display</a></li>
                        <li class="collapsible-element"><a class="waves-effect" href="/Timetable_App/room/new">Create</a></li>
                      </ul>
                    </div>
                  </li>
                </ul>
              </li>
              <li class="no-padding">
                  <ul class="collapsible collapsible-accordion">
                    <li>
                      <a class="collapsible-header waves-effect">Subjects<i class="material-icons">arrow_drop_down</i></a>
                      <div class="collapsible-body">
                        <ul class="animate fadeUp">
                          <li class="collapsible-element"><a class="waves-effect" href="/Timetable_App/subject/">Display</a></li>
                          <li class="collapsible-element"><a class="waves-effect" href="/Timetable_App/subject/new">Create</a></li>
                        </ul>
                      </div>
                    </li>
                  </ul>
                </li>
                <li class="no-padding">
                    <ul class="collapsible collapsible-accordion">
                      <li>
                        <a class="collapsible-header waves-effect">Faculties<i class="material-icons">arrow_drop_down</i></a>
                        <div class="collapsible-body">
                          <ul class="animate fadeUp">
                            <li class="collapsible-element"><a class="waves-effect" href="/Timetable_App/faculty/">Display</a></li>
                            <li class="collapsible-element"><a class="waves-effect" href="/Timetable_App/faculty/new">Create</a></li>
                          </ul>
                        </div>
                      </li>
                    </ul>
                  </li>
            <li><a class="sidenav-close" href="#!" style="color:crimson">Close Sidenav</a></li>
       
        </ul>
        </div>
    </header>
    