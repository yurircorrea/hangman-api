import './App.css';
import Word from './components/Word/Word';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <h1 className="display-1">The Hangman Game</h1>
        <div>
          <Word />
        </div>
      </header>
    </div>
  );
}

export default App;
