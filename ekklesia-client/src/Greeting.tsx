import _React, { useState, useEffect } from 'react';

function Greeting() {
    const [data, setData] = useState({ handle: '', greeting: ''});

    useEffect(() => {
        fetch('http://127.0.0.1:8080/api/greet')
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok')
                }
                return response.json();
            })
            .then(data => {
                setData(data);
            })
            .catch(error => {
                console.error('There was a problem fetching from the greet api:', error)
            });
    }, []);

    return (
        <div>
            <h2>{data.greeting}</h2>
            <p>{data.handle}</p>
        </div>
    )
}

export default Greeting;