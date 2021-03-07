//override the event listner class for our own convenience
(function() {
  let target = EventTarget.prototype;
  let functionName = 'addEventListener';
  let func = target[functionName];

  let symbolHidden = Symbol('hidden');

  function hidden(instance) {
      if (instance[symbolHidden] === undefined) {
          let area = {};
          instance[symbolHidden] = area;
          return area;
      }

      return instance[symbolHidden];
  }

  function listenersFrom(instance) {
      let area = hidden(instance);
      if (!area.listeners) { area.listeners = []; }
      return area.listeners;
  }

  target[functionName] = function(type, listener) {
      let listeners = listenersFrom(this);

      listeners.push({ type, listener });

      func.apply(this, [type, listener]);
  };
  //!
  target['removeEventListeners'] = function(targetType, excludedElement = null) {
      let self = this;
      let listeners = listenersFrom(this);
      let removed = [];
      if (excludedElement !== self || excludedElement === null) {

          listeners.forEach(item => {
              let type = item.type;
              let listener = item.listener;
              if (type == targetType) {
                  self.removeEventListener(type, listener);
              }
          });
      }
  };
})(); //instant call

class Card{
  constructor(id,title,description,link,data){
    this.id = id
    this.title = title;
    this.description = description;
    this.link = link;
    this.data = data;
  }
  D_choice_template(){return `<div class="col s12 m4 l4 xl3">
    <div class="card animate fadeUp" style="max-width: 250px;" id="card${this.id}" data="${this.data}">
    <div class="card-image waves-effect waves-block waves-light" style="max-height: 400px;">
      <img class="activator" src="https://source.unsplash.com/random">
    </div>
    <div class="card-content">
      <span class="card-title activator grey-text text-darken-4">
      ${this.title}
      ${this.link?"<a href="+this.link+'\">':""}
        <p style="color: #f2a4a8;font-size:1rem;">Go here!</p>
      ${this.link?"</a>":""}

    </div>
    <div class="card-reveal">
  <span class="card-title grey-text text-darken-4">${this.title}<i class="material-icons right">close</i></span>
      <p>${this.description}</p>
    </div>
    </div>
    </div>`}
  noD_choice_template(){return `<div class="col s12 m4 l4 xl3">
    <div class="card animate fadeUp" style="max-width: 250px;" id="card${this.id}" data="${this.data}">
    <div class="card-image waves-effect waves-block waves-light" style="max-height: 400px;">
      <img src="https://source.unsplash.com/random">
    </div>
    <div class="card-content">
      <span class="card-title grey-text text-darken-4">
      ${this.title}
      ${this.link?"<a href="+this.link+'\">':""}
        <p style="color: #f2a4a8;font-size:1rem;">Go here!</p>
      ${this.link?"</a>":""}
    </div>
    </div>
    </div>`}
}

class CardsSetter{
  constructor(){
    this.container = document.getElementById("cards_container");
    this.getYears = "k"
    this.getFacultyByYear = "k"
    this.getClassByFaculty = "k"
    this.getTimetableByClass = "k"
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
  utils_clean_container(){
    this.container.innerHTML = "";
  }
  initEventListener(instance,func,...args){
    if (func && instance){
      instance.container.removeEventListeners('click');
      instance.container.addEventListener('click',(elt)=>{
        if (!(elt.target.classList.contains("activator"))){
          if (!(elt.innerText == "close")){
            if (!instance.utils_get_CSSclass(elt.target,"card-reveal")){
              let card = instance.utils_get_CSSclass(elt.target);
              if (card!=null){
                let data = card.getAttribute("data").split(",");
                func(...data,...args,instance);
              }
             
            }
          }
        }})
    }
  }
  async createYearCards(){
    let has_description = false
    let apiURL = this.getYears
    if (apiURL){
      //let response = await fetch(apiURL);
      //let years = await JSON.parse(response.json());//[{"year":1},{"year":2},{"year":3}]
      let years = JSON.parse('[{"year":1},{"year":2},{"year":3}]');
      years.forEach((elt,index)=>{
        let card = new Card(index,elt.year+" Year","","",elt.year);
        this.container.innerHTML += has_description?card.D_choice_template():card.noD_choice_template()
      })
      this.initEventListener(this,this.createFacultyCards,false);
    }
  }
  async createFacultyCards(year ,has_description,instance){
    instance.utils_clean_container();
    let apiURL = instance.getFacultyByYear
    if (apiURL){
      //let response = await fetch(apiURL);
      //let faculties = await JSON.parse(response.json());//[{"id":4,"abrv":"GL"},{"id":2,"abrv":"IWIM"},{"id":6,"abrv":"ISEM"}]
      let faculties = JSON.parse('[{"id":4,"abrv":"GL"},{"id":2,"abrv":"IWIM"},{"id":6,"abrv":"ISEM"},{"id":8,"abrv":"Iel"},{"id":13,"abrv":"AI"}]')
      faculties.forEach((elt,index)=>{
        let card = new Card(index,year+" " + elt.abrv,"","",elt.id+","+elt.abrv);
        instance.container.innerHTML += has_description?card.D_choice_template():card.noD_choice_template()
      })
      instance.initEventListener(instance,instance.createClassCards,year,false);
    }
  }
  async createClassCards(faculty_id,faculty_abrv,year,has_description,instance){
    instance.utils_clean_container();
    let apiURL = instance.getClassByFaculty
    if (apiURL){
      //let response = await fetch(apiURL);
      //let classes = await JSON.parse(response.json());//[{"id":1,"group":"1"},{"id":2,"abrv":"2"},{"id":6,"abrv":"3"},{"id":8,"abrv":"4"}]
      let classes = JSON.parse('[{"id":1,"group":"1"},{"id":2,"group":"2"},{"id":6,"group":"3"},{"id":8,"group":"4"}]')
      classes.forEach((elt,index)=>{
        let card = new Card(index,year+" "+faculty_abrv+" "+ elt.group,"","",elt.id);
        instance.container.innerHTML += has_description?card.D_choice_template():card.noD_choice_template()
      })
      instance.initEventListener(instance,instance.createTimeTableCards,true);
    }
  }
  async createTimeTableCards(class_id,has_description,instance){
    instance.utils_clean_container();
    let apiURL = instance.getTimetableByClass
    if (apiURL){
      //let response = await fetch(apiURL);
      //let timetables = await JSON.parse(response.json());
      let t= "Lorem ipsum dolor sit amet consectetur adipisicing elit. Laboriosam optio eos fuga molestias enim laudantium."
      let timetables = JSON.parse(`[{"id":1,"description":"${t}"},{"id":2,"description":"${t}"},{"id":6,"description":"${t}"},
      {"id":8,"description":"${t}"},{"id":9,"description":"${t}"},{"id":10,"description":"${t}"},{"id":11,"description":"${t}"}]`)
      timetables.forEach((elt,index)=>{
        let card = new Card(index,`Timetable ${index+1}`,elt.description,`/timetables/${elt.id}`,"");
        instance.container.innerHTML += has_description?card.D_choice_template():card.noD_choice_template()
      })
      instance.initEventListener(null,null);
    }
    }
}


let k = new CardsSetter();
k.createYearCards();

// let div = document.getElementById("cards_container");
// console.log(div)
// for (i=0 ;i<10;i++){
//   card = new Card("title "+i,"description "+i,"#")
//   div.innerHTML += card.D_choice_template();
// }