import logo from './logo.svg';
import './App.css';
import { Component } from 'react';

class App extends Component {
  state = {
    message: []
  };

  async componentDidMount() {
    const response = await fetch('http://localhost:8080/hangman');
    const body = await response.json();
    this.setState({message: body});
  }

  render() {
    const {message} = this.state;
    return (
        <div className="App">
          <header className="App-header">
            <img src={logo} className="App-logo" alt="logo" />
            <div className="App-intro">
              <h2>Resposta:</h2>
              
                {message.response}
              
            </div>
          </header>
        </div>
    );
  }
}
export default App;