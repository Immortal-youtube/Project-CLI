
import './App.css';
import Home from './components/home';
import { Routes, Route} from 'react-router-dom';

function App() {
  return (
    <div className="App">
      <Routes>
        <Route path="/bozo" element={<Home />} />
        
      </Routes>
    </div>
  );
}

export default App;
