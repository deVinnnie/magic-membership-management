class Person{
    constructor(firstName, lastName, id, type){
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.type = type;
    }
}


class Main extends React.Component {

    loadPersons () {
        var self = this;
        $.ajax({
          url: "/api/persons"
        }).then(function (data) {
            console.log(data);
            var tr = data.map((p) => {
                return { "value": new Person(p.voornaam, p.achternaam, p.id, "participant"), "staged": false}
                }
             )
            self.setState({ "persons" : tr , searchTerm: self.state.searchTerm});
        });
    }

    constructor(){
      super();
      this.state = { persons : [], searchTerm : "" };
      /*{
        persons: [
          { value: new Person("Harry", "Potter", 1, "participant"), staged: false },
          { value: new Person("Hermione", "Granger", 2, "participant"), staged: false },
          { value: new Person("Ron", "Weasley", 3, "external"), staged: false }
      ]};*/
    }

    componentDidMount(){
        this.loadPersons();
    }

    render(){
      return <div>
        <Controls
          onClick={() => this.submit()}
        />
        <StagingList
          items={this.state.persons.filter(i => i.staged).map(i => i.value)}
          onClick={(person) => this.unstage(person)}
          onTypeChange={(e, person) => this.changeType(e, person)}
        />
        <SearchList
          searchTerm={this.state.searchTerm}
          items={this.state.persons.filter(i => i.staged == false).map(i => i.value)}
          onClick={(person) => this.stage(person)}
          onTypeChange={(e, person) => this.changeType(e, person)}
          onSearch={(searchEvent) => this.search(searchEvent.target.value)}
        />
      </div>;
    }

    stage(person){
      console.log("Staging " + person.firstName);
      const persons = this.state.persons.map(
        p => {
          if(p.value.id == person.id){
            p.staged = true;
          }
          return p;
        }
      );
      this.setState({persons: persons, searchTerm: this.state.searchTerm});
    }

    unstage(person){
      console.log("Unstaging " + person.firstName);
      const persons = this.state.persons.map(
        p => {
          if(p.value.id == person.id){
            p.staged = false;
          }
          return p;
        }
      );
      this.setState({persons: persons, searchTerm: this.state.searchTerm});
    }

    changeType(e, person){
      console.log("Changing type of  " + person.firstName);

      let newType = e.target.value;

      const persons = this.state.persons.map(
        p => {
          if(p.value.id == person.id){
            p.value.type = newType;
          }
          return p;
        }
      );
      this.setState({persons: persons, searchTerm: this.state.searchTerm});
    }

    search(searchTerm){
        console.log(searchTerm);
        this.setState({persons: this.state.persons, searchTerm: searchTerm});
    }

    submit() {
      console.log("Submitting");
      this.state.persons
        .filter(p => p.staged)
        .map( p => {return { "id" : p.value.id, "type" : p.value.type };} )
        .forEach(
          p => {
            console.log(JSON.stringify(p));
            $.ajax({
                type: "POST",
                url: "/api/events/" + eventId + "/partakings",
                data: JSON.stringify(p),
                contentType: "application/json",
                dataType: "json"
              }).done(() => {
                console.log("I'm done here");
              });
          }
        );
    }
}

function Controls(props){
    return <div id="controls">
      <button onClick={props.onClick} class="pure-button pure-button-primary">
        Opslaan
      </button>
    </div>;
}

function StagingList(props){
    const items = props.items;
    return (
      <div>
      <h1>Staging</h1>
      <ul>
      { items.map(i => <Item
        key={i.id}
        value={i}
        onClick={() => props.onClick(i)}
        onTypeChange={props.onTypeChange}
        action="Verwijderen"
      />)}
      </ul>
      </div>
    );
}


function SearchBox(props){
   return <input
        type="text"
        value={props.value}
        onChange={props.onSearch}
      />
}

function SearchList(props){
    const persons = props.items;
    console.log(props.searchTerm);

    return (
        <div>
        <h1>Search Results</h1>
        <SearchBox
           searchTerm={props.searchTerm}
           onSearch={props.onSearch}
        />
        <ul>
          {persons
            .filter(
                p => (p.firstName + " " + p.lastName).toLowerCase().includes(props.searchTerm.toLowerCase())
            )
            .map(
            p => <Item
              key={p.id}
              value={p}
              onClick={() => props.onClick(p)}
              onTypeChange={props.onTypeChange}
              action="Toevoegen"
            />
          )}
        </ul>
        </div>
    );
}


function Item(props) {
    let person = props.value;
    return (
      <div className="pure-g result">
          <div class="pure-u-1-3">
            <p class={props.value.type}>{props.value.firstName} {props.value.lastName}</p>
          </div>

          <div class="pure-u-1-3">
            <select onChange={(e) => props.onTypeChange(e, props.value)} value={props.value.type}>
              <option value="participant">Participant</option>
              <option value="assistant">Assistant</option>
              <option value="external">External</option>
           </select>
          </div>

          <div class="pure-u-1-3">
            <button onClick={props.onClick} class="pure-button">
              {props.action}
            </button>
          </div>
      </div>
    );
}

ReactDOM.render(
    <Main/>,
    document.getElementById('root')
);
