import { Component } from 'react';

class Home extends Component {
  state = {
    game: []
  };

  async componentDidMount() {
    const response = await fetch('http://localhost:8080/hangman/');
    const body = await response.json();
    this.setState({game: body});
  }

  render() {
    const {game} = this.state;
    
    return(
      <div className="Home">
        <header className="App-header">
          <div className="App-intro">
            <h1 className="display-1">The Hangman Game</h1>
          </div>
          <div className="App-body">
            <div>Vidas Restantes: {game.lifes_left}</div>
            <div>Palavra:</div>
          </div>
        </header>
      </div>
    );
  } 
}

export default Home;
