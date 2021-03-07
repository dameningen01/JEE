function style_undo_button(){
  if (callstack.list.length == 0){
    undo_button.classList.add('disabled');
  }
  else{
    undo_button.classList.remove('disabled');
  }
}
class CallStack{
  constructor(){
    this.list = new Proxy([], {
      get(target, prop) {
          const val = target[prop];
          if (typeof val === 'function') {
              if (['push', 'unshift'].includes(prop)) {
                  return function (el) {
                      // push first then evaluate the length of the list (using style_undo)
                      let x=Array.prototype[prop].apply(target, arguments)
                      style_undo_button();
                      return x;
                  }
              }
              if (['pop'].includes(prop)) {
                  return function () {
                      const el = Array.prototype[prop].apply(target, arguments);
                      style_undo_button();
                      return el;
                  }
              }
              return val.bind(target);
          }
          return val;
      }
    });

  }
  undo(){
    if (this.list.length!=0){
    this.list[this.list.length-1].call_();
    this.list.pop();}
  }
}
const callstack = new CallStack();
class UNDO{
  constructor(instance,class_id=null){
    this.undo = false;// specifies if the undo has been done or not
    this.fct = null;// the function triggred on undo
    this.instance = instance;
    this.class_id=class_id;
  }
  call_(){
    if (this.fct){
      this.fct();
      this.undo = true;
    }
  }
  // domino_call(last_idx){
  //   for (let i=0 ;i<last_idx;i++){
  //     callstack[i].call_();
  //   }
  // }
}
// TODO: REDO with Undo for a good UX
// class REDO{
//   constructor(instance){
    
//   }
// }
// const callstack = new CallStack();
document.addEventListener('keydown', function(event) {
  if (event.ctrlKey && event.key === 'z') {
    try{callstack.undo();}
    catch{}
  }
});

function randomColor() {let x= Math.floor(Math.random()*(2**24-1)).toString(16);return '#'+'0'.repeat(6-x.length)+x}
//window.scrollTo(document.body.scrollLeft + X_cursor+5, document.body.scrollTop + Y_cursor+5);
class ReadOnlyLessonCard{
    constructor(){}
    get_template(){}
}

class EditableLessonCard{
    constructor(bg_color,lesson_id,subject,subject_id,class_,class_id,teacher,teacher_id,room,room_id,leftover=null){
        this.bg_color =bg_color ;//#5d9cec
        this.lesson_id = lesson_id;

        this.subject = subject;
        this.subject_id = subject_id;

        this.class_ = class_;
        this.class_id = class_id;

        this.teacher = teacher;
        this.teacher_id = teacher_id;

        this.room = room;
        this.room_id = room_id;
        this.leftover=leftover;
    }
    get_template(drag_id=null){
      if(this.leftover){
        return `<div class="card draggable" draggable="false" style="--background: ${this.bg_color}; --text: white" lesson_id="${this.lesson_id}">
        <div class="multi-button">
          <button class="fas fa-comment"></button>
          <button class="fas fa-arrows-alt"></button>
          <button class="fas fa-trash"></button>
        </div>
        <div class="container gridd">
            <p s_id="${this.subject_id}">${this.subject}</p>
            <p c_id="${this.class_id}">${this.class_}</p>
            <p t_id="${this.teacher_id}">${this.teacher}</p>
            <p r_id="${this.room_id}">${this.room}</p>
            <p left="${this.leftover}">Left : ${this.leftover}</p>
        </div>
      </div>`
      }
      return `<div class="card draggable" draggable="false" style="--background: ${this.bg_color}; --text: white" lesson_id="${this.lesson_id}">
        <div class="multi-button">
          <button class="fas fa-comment"></button>
          <button class="fas fa-arrows-alt" ${drag_id?'id="drag'+drag_id+'"':""}></button>
          <button class="fas fa-trash"></button>
        </div>
        <div class="container gridd">
            <p s_id="${this.subject_id}">${this.subject}</p>
            <p c_id="${this.class_id}">${this.class_}</p>
            <p t_id="${this.teacher_id}">${this.teacher}</p>
            <p r_id="${this.room_id}">${this.room}</p>
        </div>
      </div>`
    }
}

class TableHandler{
  constructor(timetable,lesson_list){
    this.timetable = timetable
    this.lesson_list = lesson_list//add room_fk;subject_fk ... afterwards
    this.init_occurences(this.lesson_list);
    this.container = document.getElementById('TT_container');
    this.leftovers = document.getElementById('TT_leftovers');
    this.days = [ 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'];
    this.dragged = null;
    this.pretty_classes = this.utils_pertify_class();
  }
  init_occurences(lesson_list){
    for(let i=0;i<lesson_list.length;i++){
      lesson_list[i].lesson_occ = lesson_list[i].total_lessons
    }
  }
  decremLessonOccupation(lesson_id){
    for(let i=0;i<this.lesson_list.length;i++){
      if (this.lesson_list[i].id == lesson_id){
        lesson_list[i].lesson_occ -=1;
        break;
      }
    }
  }
  incremLessonOccupation(lesson_id){
    for(let i=0;i<this.lesson_list.length;i++){
      if (this.lesson_list[i].id == lesson_id){
        lesson_list[i].lesson_occ +=1;
        break;
      }
    }
  }
  utils_get_CSSclass(html_element,class_){
    if(!class_){
      class_ = "card"
    }
    if (html_element==null){return null}
    if (html_element.classList.contains(class_)){return html_element;}
    else{
      return this.utils_get_CSSclass(html_element.parentElement,class_)
    }
  }
  utils_get_Sibling(html_element,class_,TopDown=false){
    if(html_element == null) return null;
    if(html_element.classList.contains(class_))return html_element;
    return TopDown?this.utils_get_Sibling(html_element.nextSibling,class_,TopDown):this.utils_get_Sibling(html_element.previousSibling,class_,TopDown)
  }
  utils_get_Period(divider_tr,day,period){
    if(divider_tr == null) return null;
    let sibling = divider_tr;
    let start = true
    while (!sibling.classList.contains('divide') || start == true){
      if (start == true) start = false;
      let kids = sibling.children;
      for (let i=0; i<kids.length;i++){
        if(kids[i].getAttribute('day') == day && kids[i].getAttribute('period') == period){
          return kids[i];
        }
      }
      sibling = sibling.nextSibling;
    }
    return null;
  }
  constraint_sameClass(obj,class_id=null){
    //object is a td
    if(obj.classList.contains("empty")) return true;
    let tr = obj.parentElement;
    let table_class_id ;
    if (!class_id){
      table_class_id=this.utils_get_Sibling(tr,'divide').getAttribute('class_id');
    }
    else{
      table_class_id = parseInt(class_id,10);
    }
    let card_class_id = obj.querySelector('[c_id]');
    if(card_class_id){
      card_class_id = card_class_id.getAttribute('c_id');
      if (card_class_id == table_class_id){return true};
    }
    return false;

  }
  constraint_teacherFreetime(obj,test=false){
    if(obj.classList.contains("empty")) return true;
    let teacher = obj.querySelector('[t_id]');
    if(teacher){
      let teacher_id = teacher.getAttribute('t_id');
      let day = parseInt(obj.getAttribute('day'),10);
      let period =parseInt(obj.getAttribute('period'),10);
      for (let i=0;i<this.lesson_list.length;i++){
        
        if (this.lesson_list[i].teacher_id == teacher_id){
          let T_freetime = JSON.parse(this.lesson_list[i].teacher_freetime);
          if(T_freetime[day][period]==0)return true;
          break;
        }
      }
    }
    return false;
  }
  constraint_classFreetime(obj,test=false){
    if(obj.classList.contains("empty")) return true;
    let class_ = obj.querySelector('[c_id]');
    if (class_){
      let class_id = class_.getAttribute('c_id');
      let day = parseInt(obj.getAttribute('day'),10);
      let period =parseInt(obj.getAttribute('period'),10);
      let classObject = this.utils_get_pretty_class(class_id,this.pretty_classes);
      if (classObject){
        return JSON.parse(classObject.freetime)[day][period] == 0;
      }
    }
    return false;
  }
  constraint_teacherAvailability(obj,test=false){
    if(obj.classList.contains("empty")) return true;
    let teacher = obj.querySelector('[t_id]');
    if(teacher){
      let teacher_id = teacher.getAttribute('t_id');
      let day_ = obj.attributes.day.value;
      let period_ = obj.attributes.period.value;
      let conflicts = this.container.querySelectorAll(`[day='${day_}'][period='${period_}'] [t_id='${teacher_id}']`)
      // first element is always obj (obj .multi-button p to be exact)
      if (conflicts){
        // test means that the object doesn't exist on the table! aka just a test object
        if (test) return conflicts.length ==0;
        else return (conflicts.length ==1 || conflicts.length ==0);
      }
    }
    return false;
  }
  constraint_classAvailability(obj,test=false){
    // if(obj.classList.contains("empty")) return true;
    // let class_ = obj.querySelector('[c_id]');
    // if(class_){
    //   let class_id = class_.getAttribute('c_id');
    //   let day = parseInt(obj.getAttribute('day'),10);
    //   let period =parseInt(obj.getAttribute('period'),10);
    //   let conflicts = this.container.querySelectorAll(`td[day='${day}'][period='${period}'] [c_id='${class_id}']`)
    //   // first element is always obj (obj .multi-button p to be exact)
    //   if (conflicts){
    //      // test means that the object doesn't exist on the table! aka just a test object
    //      if (test) return conflicts.length == 0;
    //      // else there's at least one object that matches in the table!
    //      else return conflicts.length ==1;
    //   }
    // }
    // return false;
    return true;
  }
  constraint_roomAvailability(obj,test=false){
    if(obj.classList.contains("empty")) return true;
    let room = obj.querySelector('[r_id]');
    if(room){
      let room_id = room.getAttribute('r_id');
      let day = parseInt(obj.getAttribute('day'),10);
      let period =parseInt(obj.getAttribute('period'),10);
      let conflicts = this.container.querySelectorAll(`td[day='${day}'][period='${period}'] [r_id='${room_id}']`)
      // first element is always obj (obj .multi-button p to be exact)
      if (conflicts){
        if (test) return conflicts.length ==0;
        else return conflicts.length ==1;
      }
    }
    return false;
  }
  constraint_lessonSpread(obj,test=false){}
  constraint_teacherSpread(obj,test=false){}
  constraint_timetableFreetime(obj,test=false){
    let day = parseInt(obj.getAttribute('day'),10);
    let period =parseInt(obj.getAttribute('period'),10);
    return JSON.parse(this.timetable.freetime)[day][period] == 0;
  }

  test_constraints(day,period,cell_,class_id){
    let color = cell_.querySelector('.card').style.cssText.split("--background: ")[1].split(';')[0];
    let cell_data={
        bg_color:color,
        lesson_id:cell_.querySelector('.card').getAttribute('lesson_id'),
        subject:cell_.querySelector('[s_id]').innerText,
        subject_id:cell_.querySelector('[s_id]').getAttribute('s_id'),
        class_:cell_.querySelector('[c_id]').innerText,
        class_id:cell_.querySelector('[c_id]').getAttribute('c_id'),
        teacher:cell_.querySelector('[t_id]').innerText,
        teacher_id:cell_.querySelector('[t_id]').getAttribute('t_id'),
        room:cell_.querySelector('[r_id]').innerText,
        room_id:cell_.querySelector('[r_id]').getAttribute('r_id'),
    }
    let cell = document.createElement('td');
    //init html
    cell.className= "cell";
    cell.setAttribute('day',day);
    cell.setAttribute('period',period);
    let card = new EditableLessonCard(...Object.values(cell_data));
    cell.innerHTML = card.get_template();
    //we need to deep copy the obj so it doesn't change
    let cd = this.constraints(cell,true,class_id,true);
    return cd;
  }
  constraints(obj,toast=false,class_id=null,test=false){
      let msg = [
        "Wrong class!","Freetime - Selected Teacher ","Freetime - Selected Class","Teacher already working",
        "Class already studing","Room already occupied","lessons not Spread enough!",
        "Teachers timetable is not homogenous","Freetime - All Classes"
      ]
      let c = [this.constraint_sameClass(obj,class_id),

      this.constraint_teacherFreetime(obj,test),
      this.constraint_classFreetime(obj,test),

      this.constraint_teacherAvailability(obj,test),
      this.constraint_classAvailability(obj,test),
      this.constraint_roomAvailability(obj,test),

      this.constraint_lessonSpread(obj,test),
      this.constraint_teacherSpread(obj,test),
      this.constraint_timetableFreetime(obj,test),
      //this.constraint_cluster();
      ];
      let constraint_met = true;
      for (let i=0;i<c.length;i++){
        if(c[i]==true || c[i]==false){
          
          if (c[i]==false && toast){
            M.toast({html:'Eror: '+msg[i],classes:"rounded blue-grey darken-1"})
          }
          constraint_met = constraint_met && c[i]
        }
       
      }
      return constraint_met;


  }
  swapElements(obj1, obj2,check=true) {
    // save the location of obj2 (not obj2 itself so the DOM doesn't actualy mess with it)
    let parent2 = obj2.parentNode;
    let next2 = obj2.nextSibling;
    // special case for obj1 is the next sibling of obj2
    if (next2 === obj1) {
        // just put obj1 before obj2
        parent2.insertBefore(obj1, obj2);
    } else {
        // insert obj2 right before obj1
        obj1.parentNode.insertBefore(obj2, obj1);
  
        // now insert obj1 where obj2 was
        if (next2) {
            // if there was an element after obj2, then insert obj1 right before that
            parent2.insertBefore(obj1, next2);
        } else {
            // otherwise, just append as last child
            parent2.appendChild(obj1);
        }
    }
    //swap objects data
    let day1 = obj1.getAttribute('day');
    let period1 = obj1.getAttribute('period');
    obj1.setAttribute('day',obj2.getAttribute('day'));
    obj1.setAttribute('period',obj2.getAttribute('period'));
    obj2.setAttribute('day',day1);
    obj2.setAttribute('period',period1);
    // constraint check after the swap
    if (check) return this.constraints(obj1,true) && this.constraints(obj2,true);

  }
  initTable(){
    function makePeriods(nb_periods){
      //let h = this.timetable.hours_per_period
      let data = "";
      for (let i =1;i<=nb_periods;i++){
        data +=`<th>P${i}</th>`;
      }
      return data
      
    }
    this.container.innerHTML +=`
      <thead>
        <tr style="
        background-color: #fff;
        opacity: 0.9;">
        <th></th>
        <th></th>
        ${makePeriods(this.timetable.nb_periods)}
        </tr>
      </thead>
      <tbody id="TT_body"></tbody>
    `;
  }
  utils_pertify_class(){
    
    let classObject_list = [];
    function add_class(lesson,list){
      let class_id = lesson.class_id;
      let class_color = lesson.class_color;
      let class_freetime = lesson.class_freetime;
      for (let i=0;i<list.length;i++){
        if (list[i].id == class_id){
          list[i].lessons.push(lesson);
          return null;
        }
      }
      list.push({id:class_id,color:class_color,freetime:class_freetime,name:lesson.class,lessons:[lesson]});
    }

    for (let i=0;i<this.lesson_list.length;i++){
      add_class(this.lesson_list[i],classObject_list);
    }
    return classObject_list;
  }
  utils_get_pretty_class(class_id,classObject_list){
    for (let i =0 ; i<classObject_list.length;i++){
      if(classObject_list[i].id==class_id) return classObject_list[i];
    }
  }
  populateEditableTable(){
    this.initTable();
    function getLesson(lesson_id,lesson_table){
      for (let i =0 ; i<lesson_table.length;i++){
        if (lesson_id==lesson_table[i].id) return lesson_table[i];
      }
    }

    let body = document.getElementById("TT_body");

    let classObject_list = this.pretty_classes
    
    let total_days = this.timetable.nb_days%7;
    let days = this.days.slice(0,total_days);

    let total_periods = this.timetable.nb_periods;

    let summary = JSON.parse(this.timetable.summary);
    let freetime = JSON.parse(this.timetable.freetime);
    let drag_id = 0;
    let html = "";
    for (let class_id in summary){
        let classObject = this.utils_get_pretty_class(class_id,classObject_list);
        if (classObject){
          html+=`<tr class='divide' class_id="${class_id}">`
          html += `<td class="classe" rowspan="${total_days}" style="--class_bg:${classObject.color};">${classObject.name}</td>`;
          for (let day in summary[class_id]){
            html += `<td class="day">${days[day]}</td>`;
            for (let period in summary[class_id][day]){
              if(freetime[day][period] == 1){
                html +=`<td class="cell sep" day="${day}" period="${period}"></td>`
              }
              else if(summary[class_id][day][period] == 0) {
                html += `<td class="cell empty" day="${day}" period="${period}"></td>`
              }
              else{
                let lesson = getLesson(summary[class_id][day][period],classObject.lessons);
                this.decremLessonOccupation(summary[class_id][day][period]);
                let card = new EditableLessonCard(lesson.color,lesson.id,
                  lesson.subject,lesson.subject_id,lesson.class,lesson.class_id,
                  lesson.teacher,lesson.teacher_id,lesson.room,lesson.room_id);
                html += `<td class="cell" day="${day}" period="${period}">${card.get_template(++drag_id)}</td>`;
              }
            }
            html+="</tr>"
          }
        }
        else{
          alert("Corrupted timetable! Please delete it");
        }
    }
    body.innerHTML += html;
    
    this.initTableEvents(drag_id);
    this.populateLeftoversContainer();
  }
  populateLeftover(td,events = true){
    this.leftovers.appendChild(td);
    if(events){
      let btn = td.querySelector('.fa-arrows-alt');
      let container = td.querySelector('.card');
      let trash = td.querySelector('.fa-trash');
      
      container.addEventListener('dragstart',(e)=>{
        container.classList.add("dragging");
        this.dragged = td;
      })
      container.addEventListener('dragend',()=>{
        container.classList.remove("dragging");
      })
      //these events are added so the card can work properly once droped in the table
      container.addEventListener('dragenter',(event)=>{
        td.classList.add("dropzone");
      })
      container.addEventListener('dragleave',(event)=>{
        td.classList.remove("dropzone");
      })
      // enable draging once the button clicked
      btn.addEventListener('click',()=>{
      if(container.getAttribute("draggable")=="true"){
        container.setAttribute("draggable","false");
        btn.className = "fas fa-arrows-alt";
      }
      else{
        container.setAttribute("draggable","true");
        btn.className = "fas fa-times-circle";
      }
      })
      trash.addEventListener('click',(event)=>{
        let td = this.utils_get_CSSclass(event.target,'cell');
        let class_id = this.utils_get_Sibling(td.parentElement,'divide').getAttribute('class_id');
        let td_copy = td.cloneNode(true);

        if(this.utils_get_CSSclass(td,'timetable')){
          // DOM resets so beware
          let leftover = this.deleteLesson(td);
          let lesson_id = leftover.querySelector('[lesson_id]').getAttribute('lesson_id');
          console.log(lesson_id);
          let undo_obj = new UNDO(this);
          undo_obj.fct = ()=>{
            if(!undo_obj.undo){
              //treatement
              let day = td_copy.getAttribute('day');
              let period = td_copy.getAttribute('period');
              let left_card = undo_obj.instance.leftovers.querySelector(`[lesson_id='${lesson_id}']`);
              let left = undo_obj.instance.utils_get_CSSclass(left_card,'leftover')
              undo_obj.instance.create_element(day,period,td_copy);
              undo_obj.instance.pop_leftover(left);
              undo_obj.undo=true;
              M.toast({html:"Changes reverted!",classes:"rounded deep-orange lighten-1"})
            }
          }
          callstack.list.push(undo_obj);
          let toastHTML = `<span>Modification done</span>
          <button class="btn-flat toast-action" 
          onclick="callstack.undo()">Undo</button>`;
          M.toast({html: toastHTML,classes:"rounded"});
        }
      })
    }
  }
  deleteLesson(td){
    let elt = this.add_leftover(td);
    td.innerHTML = "";
    td.classList.add('empty');
    return elt;
  }
  populateLeftoversContainer(){
    for(let i=0;i<this.lesson_list.length;i++){
      if(this.lesson_list[i].lesson_occ !=0){
        let td = document.createElement('td');
        td.className = "cell leftover"
        let card = new EditableLessonCard(
          this.lesson_list[i].color,this.lesson_list[i].id,
          this.lesson_list[i].subject,this.lesson_list[i].subject_id,
          this.lesson_list[i].class,this.lesson_list[i].class_id,
          this.lesson_list[i].teacher,this.lesson_list[i].teacher_id,
          this.lesson_list[i].room,this.lesson_list[i].room_id,
          this.lesson_list[i].lesson_occ);

        td.innerHTML = card.get_template();
        this.populateLeftover(td);

    // need to add undowable function that deletes an empty cell
        }
      }
  }
  pop_leftover(cell){
    if(cell){
      if (cell.classList.contains('leftover')){
        //bg_color,lesson_id,subject,subject_id,class_,class_id,teacher,teacher_id,room,room_id
        let left_elt = cell.querySelector('[left]');
        if (left_elt){
          let color = cell.querySelector('.card').style.cssText.split("--background: ")[1].split(';')[0];
          let cell_data={
            bg_color:color,//--background-color:#...;
            lesson_id:cell.querySelector('.card').getAttribute('lesson_id'),
            subject:cell.querySelector('[s_id]').innerText,
            subject_id:cell.querySelector('[s_id]').getAttribute('s_id'),
            class_:cell.querySelector('[c_id]').innerText,
            class_id:cell.querySelector('[c_id]').getAttribute('c_id'),
            teacher:cell.querySelector('[t_id]').innerText,
            teacher_id:cell.querySelector('[t_id]').getAttribute('t_id'),
            room:cell.querySelector('[r_id]').innerText,
            room_id:cell.querySelector('[r_id]').getAttribute('r_id'),
          }
          let left = parseInt(left_elt.getAttribute('left'),10);
          let n = left-1;
          this.decremLessonOccupation(cell_data.lesson_id);
          if (n>0){
            left_elt.setAttribute('left',n);
            left_elt.innerText = `Left : ${n}`;
            return left_elt;
          }
          else{
            cell.remove();
          }
        }
      }
    }
  }
  add_leftover(cell){
    if(cell){
      if (cell.hasAttribute('day')){
        //bg_color,lesson_id,subject,subject_id,class_,class_id,teacher,teacher_id,room,room_id
        let color = cell.querySelector('.card').style.cssText.split("--background: ")[1].split(';')[0];
        let cell_data={
          bg_color:color,
          lesson_id:cell.querySelector('.card').getAttribute('lesson_id'),
          subject:cell.querySelector('[s_id]').innerText,
          subject_id:cell.querySelector('[s_id]').getAttribute('s_id'),
          class_:cell.querySelector('[c_id]').innerText,
          class_id:cell.querySelector('[c_id]').getAttribute('c_id'),
          teacher:cell.querySelector('[t_id]').innerText,
          teacher_id:cell.querySelector('[t_id]').getAttribute('t_id'),
          room:cell.querySelector('[r_id]').innerText,
          room_id:cell.querySelector('[r_id]').getAttribute('r_id')
        }
        let left_card = this.leftovers.querySelector(`.leftover [lesson_id='${cell_data.lesson_id}']`);
        if (left_card){
          let left_elt = left_card.querySelector('[left]');
          if (left_elt){
            let left = parseInt(left_elt.getAttribute('left'),10);
            this.incremLessonOccupation(cell_data.lesson_id);
            left_elt.setAttribute('left',left+1);
            left_elt.innerText = `Left : ${left+1}`;
            return left_elt;
            }
        }
        else{
          //we didn't find a pre-existing leftover card so we need to make one
          let td = document.createElement('td');
          td.className = "cell leftover";
          cell_data['occ'] = 1;
          let card = new EditableLessonCard(...Object.values(cell_data));
          td.innerHTML = card.get_template();
          this.populateLeftover(td);
          return td;
        }
      }
    }
  }
  create_element(day,period,cell_,id=null){
    if (cell_){
      let container;
      let class_id;
      let cell;
      if (cell_.classList.contains('empty') || cell_.classList.contains('sep')){
        cell = document.createElement('td');
        //init html
        cell.className= cell_.className;
        cell.setAttribute('day',day);
        cell.setAttribute('period',period);
        container = cell;
      }
      else{
        let color = cell_.querySelector('.card').style.cssText.split("--background: ")[1].split(';')[0];
        let cell_data={
          bg_color:color,
          lesson_id:cell_.querySelector('.card').getAttribute('lesson_id'),
          subject:cell_.querySelector('[s_id]').innerText,
          subject_id:cell_.querySelector('[s_id]').getAttribute('s_id'),
          class_:cell_.querySelector('[c_id]').innerText,
          class_id:cell_.querySelector('[c_id]').getAttribute('c_id'),
          teacher:cell_.querySelector('[t_id]').innerText,
          teacher_id:cell_.querySelector('[t_id]').getAttribute('t_id'),
          room:cell_.querySelector('[r_id]').innerText,
          room_id:cell_.querySelector('[r_id]').getAttribute('r_id'),
        }
        class_id = cell_data.class_id;
        cell = document.createElement('td');
        //init html
        cell.className= "cell";
        cell.setAttribute('day',day);
        cell.setAttribute('period',period);
        let card = new EditableLessonCard(...Object.values(cell_data));
        cell.innerHTML = card.get_template();

      //init eventListeners
      let btn = cell.querySelector('.fa-arrows-alt');
      container = cell;
      let trash = cell.querySelector('.fa-trash');
      btn.addEventListener('click',()=>{
        if(container.getAttribute("draggable")=="true"){
          container.setAttribute("draggable","false");
          btn.className = "fas fa-arrows-alt";
        }
        else{
          container.setAttribute("draggable","true");
          btn.className = "fas fa-times-circle";
        }
      })
      trash.addEventListener('click',(event)=>{
        let td = this.utils_get_CSSclass(event.target,'cell');
        let class_id = this.utils_get_Sibling(td.parentElement,'divide').getAttribute('class_id');
        let td_copy = td.cloneNode(true);

        if(this.utils_get_CSSclass(td,'timetable')){
          // DOM resets so beware
          let leftover = this.deleteLesson(td);
          let lesson_id = leftover.querySelector('[lesson_id]').getAttribute('lesson_id');
          console.log(lesson_id);
          let undo_obj = new UNDO(this);
          undo_obj.fct = ()=>{
            if(!undo_obj.undo){
              //treatement
              let day = td_copy.getAttribute('day');
              let period = td_copy.getAttribute('period');
              let left_card = undo_obj.instance.leftovers.querySelector(`[lesson_id='${lesson_id}']`);
              let left = undo_obj.instance.utils_get_CSSclass(left_card,'leftover')
              undo_obj.instance.create_element(day,period,td_copy);
              undo_obj.instance.pop_leftover(left);
              undo_obj.undo=true;
              M.toast({html:"Changes reverted!",classes:"rounded deep-orange lighten-1"})
            }
          }
          callstack.list.push(undo_obj);
          let toastHTML = `<span>Modification done</span>
          <button class="btn-flat toast-action" 
          onclick="callstack.undo()">Undo</button>`;
          M.toast({html: toastHTML,classes:"rounded"});
        }
      })
      }

      container.addEventListener('dragstart',(e)=>{
        container.classList.add("dragging");
        this.dragged = cell;
      })
      container.addEventListener('dragend',()=>{
        container.classList.remove("dragging");
      })
      container.addEventListener('dragenter',(event)=>{
        cell.classList.add("dropzone");
      })
      container.addEventListener('dragleave',(event)=>{
        cell.classList.remove("dropzone");
      })

      // add the cell content
      if(!class_id){
        class_id = id;
      }
      let tr = this.container.querySelector(`[class_id='${class_id}']`);
      let td = this.utils_get_Period(tr,day,period);
      td.replaceWith(cell);
      return cell;
      }
  }

  initTableEvents(max_dragables){
    let body = document.getElementById("TT_body");
    for (let i=1;i<=max_dragables;i++){
      let btn = document.getElementById(`drag${i}`);
      let container = this.utils_get_CSSclass(btn,'card');
      let cell = container.parentElement;
      let trash = cell.querySelector('.fa-trash');
      container.addEventListener('dragstart',(e)=>{
        container.classList.add("dragging");
        this.dragged = cell;
      })
      container.addEventListener('dragend',()=>{
        container.classList.remove("dragging");
      })

      container.addEventListener('dragenter',(event)=>{
        cell.classList.add("dropzone");
      })
      container.addEventListener('dragleave',(event)=>{
        cell.classList.remove("dropzone");
      })

      // enable draging once the button clicked
      btn.addEventListener('click',()=>{
        if(container.getAttribute("draggable")=="true"){
          container.setAttribute("draggable","false");
          btn.className = "fas fa-arrows-alt";
        }
        else{
          container.setAttribute("draggable","true");
          btn.className = "fas fa-times-circle";
        }
      })
      trash.addEventListener('click',(event)=>{
        let td = this.utils_get_CSSclass(event.target,'cell');
        let class_id = this.utils_get_Sibling(td.parentElement,'divide').getAttribute('class_id');
        let td_copy = td.cloneNode(true);

        if(this.utils_get_CSSclass(td,'timetable')){
          // DOM resets so beware
          let leftover = this.deleteLesson(td);
          let lesson_id = leftover.querySelector('[lesson_id]').getAttribute('lesson_id');
          console.log(lesson_id);
          let undo_obj = new UNDO(this);
          undo_obj.fct = ()=>{
            if(!undo_obj.undo){
              //treatement
              let day = td_copy.getAttribute('day');
              let period = td_copy.getAttribute('period');
              let left_card = undo_obj.instance.leftovers.querySelector(`[lesson_id='${lesson_id}']`);
              let left = undo_obj.instance.utils_get_CSSclass(left_card,'leftover')
              undo_obj.instance.create_element(day,period,td_copy);
              undo_obj.instance.pop_leftover(left);
              undo_obj.undo=true;
              M.toast({html:"Changes reverted!",classes:"rounded deep-orange lighten-1"})
            }
          }
          callstack.list.push(undo_obj);
          let toastHTML = `<span>Modification done</span>
          <button class="btn-flat toast-action" 
          onclick="callstack.undo()">Undo</button>`;
          M.toast({html: toastHTML,classes:"rounded"});
        }
      })
    }
    let selected;
    body.addEventListener('dragover',(event)=>{
      event.preventDefault();//this enables the drop
      let cell = this.utils_get_CSSclass(event.target,'cell');
      if(selected && cell){
        if(selected != cell){
          cell.classList.add("dropzone");
          selected.classList.remove("dropzone");
          selected = cell;
        }
      }
      if(!selected && cell){
        selected = cell;
        selected.classList.add("dropzone");
      }
    })
    body.addEventListener('drop',(event)=>{
      event.preventDefault();
      // drop case: (table->table) or (left->table)
      let drop_cell = this.utils_get_CSSclass(event.target,"cell");
      if (drop_cell){
        let day = drop_cell.getAttribute('day');
        let period = drop_cell.getAttribute('period');

        if (this.dragged.classList.contains('leftover')){
          if (drop_cell.classList.contains('cell')){
            let class_id = this.utils_get_Sibling(drop_cell.parentElement,'divide').getAttribute('class_id');
            if(drop_cell.classList.contains('empty')){
              let cd = this.test_constraints(day,period,this.dragged,class_id);
              if (cd){
                let elt = this.create_element(day,period,this.dragged);
                this.pop_leftover(this.dragged);
                this.dragged = elt;
                let undo_obj = new UNDO(this);
                undo_obj.fct = ()=>{
                  if(!undo_obj.undo){
                    undo_obj.instance.add_leftover(elt);
                    undo_obj.instance.create_element(day,period,drop_cell,class_id);//?
                    undo_obj.undo=true;
                    M.toast({html:"Changes reverted!",classes:"rounded deep-orange lighten-1"})
                  }
                }
                callstack.list.push(undo_obj);
                let toastHTML = `<span>Modification done</span>
                <button class="btn-flat toast-action" 
                onclick="callstack.undo()">Undo</button>`;
                M.toast({html: toastHTML,classes:"rounded"});
              }
             
            }
            else{
              let cd = this.test_constraints(day,period,this.dragged,class_id);
              if(cd){
                let l = this.add_leftover(drop_cell);
                let elt = this.create_element(day,period,this.dragged);
                this.pop_leftover(this.dragged);
                // used to simplify swap table <> table
                this.dragged = elt;
                let undo_obj = new UNDO(this);
                undo_obj.fct = ()=>{
                  if(!undo_obj.undo){
                    undo_obj.instance.add_leftover(elt);
                    undo_obj.instance.create_element(day,period,drop_cell,class_id);
                    undo_obj.instance.pop_leftover(l);
                    undo_obj.undo=true;
                    M.toast({html:"Changes reverted!",classes:"rounded deep-orange lighten-1"})
                  }
                }
                callstack.list.push(undo_obj);
                let toastHTML = `<span>Modification done</span>
                <button class="btn-flat toast-action" 
                onclick="callstack.undo()">Undo</button>`;
                M.toast({html: toastHTML,classes:"rounded"});
              }
            }
          }
          
        }
        else{
          let cd = this.swapElements(this.dragged,drop_cell);
          if(cd==false){
            this.swapElements(this.dragged,drop_cell,false);//undo the operation
          }
          else{
            let class_id = this.utils_get_Sibling(drop_cell.parentElement,'divide').getAttribute('class_id');
            let undo_obj = new UNDO(this,class_id);
            // there's a gimic here where : if the DOM changes ( swaping 2 elements from table <->leftovers)
            // and that element had being used in a table <-> table action the function call won't happen
            // example: in table= T. / in Leftovers = L
            // swap(T.l1,T.l2); swap(L.L3,T.L1)(DOM CHANGES!!); no operation on T.l1 can happen!
            // to fix that let's not update the dom each time this type of undo is done!
            // we need this.dragged to be = to the old cell so we can do the selection and the class id
            let dd = this.dragged;
            undo_obj.fct = ()=>{
              if(!undo_obj.undo){
                let day = dd.getAttribute('day');
                let period = dd.getAttribute('period');
                let tr = undo_obj.instance.container.querySelector(`[class_id='${undo_obj.class_id}']`);
                let dynamicObj = undo_obj.instance.utils_get_Period(tr,day,period);

                undo_obj.instance.swapElements(dynamicObj,drop_cell,false);
                undo_obj.undo=true;
                M.toast({html:"Changes reverted!",classes:"rounded deep-orange lighten-1"})
              }
            }
            
            callstack.list.push(undo_obj);
            let toastHTML = `<span>Modification done</span>
            <button class="btn-flat toast-action" 
            onclick="callstack.undo()">Undo</button>`;
            M.toast({html: toastHTML,classes:"rounded"});
        }
        }
        drop_cell.classList.remove("dropzone");
      }  
    })
  }
  save(){
    let summary = {};
    // results of querySelectorAll are ordered
    let classes = this.container.querySelectorAll('[class_id]');
    let x =this.container.querySelectorAll('.cell');
    let days = this.timetable.nb_days;
    let periods = this.timetable.nb_periods;
    for (let i=0;i<classes.length;i++){
      summary[classes[i].getAttribute('class_id')] = []
      for (let j =0;j<days;j++){
        summary[classes[i].getAttribute('class_id')].push([])
        for(let k=0;k<periods;k++){
          let lesson_id = x[j*periods+k].querySelector('[lesson_id]');
          if (lesson_id){lesson_id = lesson_id.getAttribute('lesson_id');}
          else{lesson_id = 0;}
          summary[classes[i].getAttribute('class_id')][j].push(lesson_id);
        }
      }
      
    }
    console.log(summary);
    return summary;
  }
  reset(){}
  check(){}
  complete(){}
  generate(){}
}

let timetable = {id:1,description:"text",nb_days:5,nb_periods:4,hours_per_period:2,
  freetime:"[[0,0,0,0],[0,0,0,0],[0,0,1,1],[0,0,0,0],[0,0,0,0]]",
  summary:`{
    "10":[[1,4,5,6],[7,1,8,8],[4,5,0,0],[7,7,8,0],[9,10,0,0]],
    "2":[[11,14,15,16],[17,11,18,18],[14,15,0,0],[17,17,18,0],[19,110,0,0]]
  }`}
let c1 = randomColor();
let c2 = randomColor();
let lesson_list = [
  {id:1,class:"2GL1",class_freetime:"[[0,0,0,0],[0,0,0,0],[0,0,1,1],[0,0,0,0],[0,0,1,1]]",room:"S29",subject:"JAVA",teacher:"Sabo",
  teacher_freetime:"[[0,0,0,0],[0,0,0,0],[0,0,1,1],[0,0,0,0],[0,0,0,0]]",
total_lessons:4,lesson_occ:"",lesson_link:"#",class_id:10,subject_id:1,teacher_id:1,
room_id:13,color:randomColor(),class_color:c1},
  {id:4,class:"2GL1",room:"L26",subject:"C++",teacher:"onizuka",
total_lessons:3,lesson_occ:"",lesson_link:"#",class_id:10,subject_id:2,teacher_id:2,
teacher_freetime:"[[0,0,0,0],[0,0,0,0],[0,0,1,1],[0,0,0,0],[0,0,0,0]]",
class_freetime:"[[0,0,0,0],[0,0,0,0],[0,0,1,1],[0,0,0,0],[0,0,1,1]]",
room_id:2,color:randomColor(),class_color:c1},
  {id:5,class:"2GL1",room:"L27",subject:"C",teacher:"dug dimadom",
total_lessons:2,lesson_occ:"",lesson_link:"#",class_id:10,subject_id:3,teacher_id:3,
teacher_freetime:"[[0,0,0,0],[0,0,0,0],[0,0,1,1],[0,0,0,0],[0,0,1,1]]",
class_freetime:"[[0,0,0,0],[0,0,0,0],[0,0,1,1],[0,0,0,0],[0,0,1,1]]",
room_id:3,color:randomColor(),class_color:c1},
  {id:6,class:"2GL1",room:"L28",subject:"C5",teacher:"danny",
teacher_freetime:"[[0,0,0,0],[0,0,0,0],[0,0,1,1],[0,0,0,0],[0,0,0,0]]",
class_freetime:"[[0,0,0,0],[0,0,0,0],[0,0,1,1],[0,0,0,0],[0,0,1,1]]",
total_lessons:2,lesson_occ:"",lesson_link:"#",class_id:10,subject_id:4,teacher_id:4,
room_id:4,color:randomColor(),class_color:c1},
  {id:7,class:"2GL1",room:"L29",subject:"C6",teacher:"danny1",
teacher_freetime:"[[0,0,0,0],[0,0,0,0],[0,0,1,1],[0,0,0,0],[0,0,0,0]]",
class_freetime:"[[0,0,0,0],[0,0,0,0],[0,0,1,1],[0,0,0,0],[0,0,1,1]]",
total_lessons:3,lesson_occ:"",lesson_link:"#",class_id:10,subject_id:5,teacher_id:5,
room_id:5,color:randomColor(),class_color:c1},
  {id:8,class:"2GL1",room:"L30",subject:"C7",teacher:"danny2",
teacher_freetime:"[[0,0,0,0],[0,0,0,0],[0,0,1,1],[0,0,0,0],[0,0,0,0]]",
class_freetime:"[[0,0,0,0],[0,0,0,0],[0,0,1,1],[0,0,0,0],[0,0,1,1]]",
total_lessons:3,lesson_occ:"",lesson_link:"#",class_id:10,subject_id:6,teacher_id:6,
room_id:6,color:randomColor(),class_color:c1},
  {id:9,class:"2GL1",room:"L31",subject:"C8",teacher:"danny3",
teacher_freetime:"[[0,0,0,0],[0,0,0,0],[0,0,1,1],[0,0,0,0],[0,0,0,0]]",
class_freetime:"[[0,0,0,0],[0,0,0,0],[0,0,1,1],[0,0,0,0],[0,0,1,1]]",
total_lessons:3,lesson_occ:"",lesson_link:"#",class_id:10,subject_id:7,teacher_id:7,
room_id:7,color:randomColor(),class_color:c1},
  {id:10,class:"2GL1",room:"L32",subject:"C9",teacher:"danny4",
teacher_freetime:"[[0,0,0,0],[0,0,0,0],[0,0,1,1],[0,0,0,0],[0,0,0,0]]",
class_freetime:"[[0,0,0,0],[0,0,0,0],[0,0,1,1],[0,0,0,0],[0,0,1,1]]",
total_lessons:1,lesson_occ:"",lesson_link:"#",class_id:10,subject_id:8,teacher_id:8,
room_id:8,color:randomColor(),class_color:c1},

  {id:11,class:"2GL2",room:"S25",subject:"JAVA",teacher:"Sabo",
teacher_freetime:"[[0,0,0,0],[0,0,0,0],[0,0,1,1],[0,0,0,0],[0,0,0,0]]",
class_freetime:"[[0,0,0,0],[0,0,0,0],[0,0,1,1],[0,0,0,0],[0,0,0,0]]",
total_lessons:2,lesson_occ:"",lesson_link:"#",class_id:2,subject_id:1,teacher_id:1,
room_id:9,color:randomColor(),class_color:c2},
  {id:14,class:"2GL2",room:"S26",subject:"C++",teacher:"onizuka",
teacher_freetime:"[[0,0,0,0],[0,0,0,0],[0,0,1,1],[0,0,0,0],[0,0,0,0]]",
class_freetime:"[[0,0,0,0],[0,0,0,0],[0,0,1,1],[0,0,0,0],[0,0,0,0]]",
total_lessons:2,lesson_occ:"",lesson_link:"#",class_id:2,subject_id:2,teacher_id:2,
room_id:10,color:randomColor(),class_color:c2},
  {id:15,class:"2GL2",room:"S27",subject:"C",teacher:"dug dimadom2",
teacher_freetime:"[[0,0,0,0],[0,0,0,0],[0,0,1,1],[0,0,0,0],[0,0,0,0]]",
class_freetime:"[[0,0,0,0],[0,0,0,0],[0,0,1,1],[0,0,0,0],[0,0,0,0]]",
total_lessons:2,lesson_occ:"",lesson_link:"#",class_id:2,subject_id:3,teacher_id:9,
room_id:11,color:randomColor(),class_color:c2},
  {id:16,class:"2GL2",room:"S28",subject:"C5",teacher:"danny phantom2",
teacher_freetime:"[[0,0,0,0],[0,0,0,0],[0,0,1,1],[0,0,0,0],[0,0,0,0]]",
class_freetime:"[[0,0,0,0],[0,0,0,0],[0,0,1,1],[0,0,0,0],[0,0,0,0]]",
total_lessons:1,lesson_occ:"",lesson_link:"#",class_id:2,subject_id:4,teacher_id:10,
room_id:12,color:randomColor(),class_color:c2},
  {id:17,class:"2GL2",room:"S29",subject:"C6",teacher:"danny12",
teacher_freetime:"[[0,0,0,0],[0,0,0,0],[0,0,1,1],[0,0,0,0],[0,0,0,0]]",
class_freetime:"[[0,0,0,0],[0,0,0,0],[0,0,1,1],[0,0,0,0],[0,0,0,0]]",
total_lessons:3,lesson_occ:"",lesson_link:"#",class_id:2,subject_id:5,teacher_id:11,
room_id:13,color:randomColor(),class_color:c1},
  {id:18,class:"2GL2",room:"S30",subject:"C7",teacher:"danny22",
teacher_freetime:"[[0,0,0,0],[0,0,0,0],[0,0,1,1],[0,0,0,0],[0,0,0,0]]",
class_freetime:"[[0,0,0,0],[0,0,0,0],[0,0,1,1],[0,0,0,0],[0,0,0,0]]",
total_lessons:3,lesson_occ:"",lesson_link:"#",class_id:2,subject_id:6,teacher_id:12,
room_id:14,color:randomColor(),class_color:c2},
  {id:19,class:"2GL2",room:"S31",subject:"C8",teacher:"danny32",
teacher_freetime:"[[0,0,0,0],[0,0,0,0],[0,0,1,1],[0,0,0,0],[0,0,0,0]]",
class_freetime:"[[0,0,0,0],[0,0,0,0],[0,0,1,1],[0,0,0,0],[0,0,0,0]]",
total_lessons:1,lesson_occ:"",lesson_link:"#",class_id:2,subject_id:7,teacher_id:13,
room_id:15,color:randomColor(),class_color:c2},
  {id:110,class:"2GL2",room:"S32",subject:"C9",teacher:"danny42",
teacher_freetime:"[[0,0,0,0],[0,0,0,0],[0,0,1,1],[0,0,0,0],[0,0,0,0]]",
class_freetime:"[[0,0,0,0],[0,0,0,0],[0,0,1,1],[0,0,0,0],[0,0,0,0]]",
total_lessons:1,lesson_occ:"",lesson_link:"#",class_id:2,subject_id:8,teacher_id:14,
room_id:16,color:randomColor(),class_color:c2}
]

let handler = new TableHandler(timetable,lesson_list);
handler.populateEditableTable();

let undo_button = document.getElementById('undo_button');
let save_button = document.getElementById('save_button');
//listen to changes on the callstackobject

undo_button.addEventListener('click',()=>callstack.undo());
save_button.addEventListener('click',()=>handler.save());

// select lesson.id , 
//   teacher_fk , teacher_data,
//   class_fk , class_data,
//   room_fk , subject_data,
//   subject_fk , subject_data,
//   total_lessons
//   timetable_fk,
//   lesson_occ,
//   lesson_link, color
//   from lesson , teacher,room,room,subject,faculty