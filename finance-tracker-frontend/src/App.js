import React, { useState } from 'react';
import Login from './components/Login';
import Register from './components/Register';
import Dashboard from './components/Dashboard';

function App() {
    const [loggedIn, setLoggedIn] = useState(!!localStorage.getItem('token'));
    const [showRegister, setShowRegister] = useState(false);

    const handleLogout = () => {
        localStorage.removeItem('token');
        setLoggedIn(false);
    };

    if (loggedIn) {
        return (
            <div>
                <button onClick={handleLogout}>Logout</button>
                <Dashboard />
            </div>
        );
    }

    return (
        <div>
            {showRegister ? (
                <>
                    <Register onRegisterSuccess={() => setShowRegister(false)} />
                    <p>
                        Already have an account?{' '}
                        <button onClick={() => setShowRegister(false)}>Login</button>
                    </p>
                </>
            ) : (
                <>
                    <Login onLoginSuccess={() => setLoggedIn(true)} />
                    <p>
                        Don't have an account?{' '}
                        <button onClick={() => setShowRegister(true)}>Register</button>
                    </p>
                </>
            )}
        </div>
    );
}

export default App;
